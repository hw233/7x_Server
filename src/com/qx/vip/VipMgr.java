package com.qx.vip;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import qxmobile.protobuf.VIP.ChongTimes;
import qxmobile.protobuf.VIP.RechargeReq;
import qxmobile.protobuf.VIP.RechargeResp;
import qxmobile.protobuf.VIP.VipInfoResp;
import qxmobile.protobuf.ZhangHao.RegRet;

import com.google.protobuf.MessageLite.Builder;
import com.manu.dynasty.base.TempletService;
import com.manu.dynasty.template.CanShu;
import com.manu.dynasty.template.ChongZhi;
import com.manu.dynasty.template.VIP;
import com.manu.dynasty.template.VipFuncOpen;
import com.manu.dynasty.util.DateUtils;
import com.manu.network.PD;
import com.qx.activity.ShouchongInfo;
import com.qx.activity.ShouchongMgr;
import com.qx.junzhu.JunZhu;
import com.qx.junzhu.JunZhuMgr;
import com.qx.persistent.HibernateUtil;
import com.qx.task.DailyTaskMgr;
import com.qx.yuanbao.YBType;
import com.qx.yuanbao.YuanBaoMgr;

public class VipMgr {

	public static VipMgr INSTANCE;
	/** VIP配置文件信息，<vip等级，vip信息> **/
	public static Map<Integer, VIP> vipTemp;
	public static Map<Integer, ChongZhi> chongZhiTemp;
	public static Map<Integer, VipFuncOpen> vipFuncOpenTemp;
	public static Logger log = LoggerFactory.getLogger(VipMgr.class);

	public static ChongZhi yueka;
	public static int yuekaid = 0;
	public static int maxVip = 1;
	/** 限购type **/
	public static int limit_type = 1;
	public static int buy_yueka_limit_days = 5;

	public static ThreadLocal<VipRechargeRecord> yueKaRecord = new ThreadLocal<VipRechargeRecord>();

	public VipMgr() {
		INSTANCE = this;
		initData();
	}

	public void initData() {
		// 加载VIP配置文件信息
		@SuppressWarnings("unchecked")
		List<VIP> vipList = TempletService.listAll(VIP.class.getSimpleName());
		Map<Integer, VIP> temp = new HashMap<Integer, VIP>();
		for (VIP vip : vipList) {
			temp.put(vip.lv, vip);
			if (vip.lv > maxVip) {
				maxVip = vip.lv;
			}
		}
		vipTemp = temp;
		// 加载ChongZhi配置文件信息
		setChongZhiTemp();
		// 加载VipFuncOpen配置文件信息
		setVipFuncOpenTemp();
	}

	@SuppressWarnings("unchecked")
	public void setChongZhiTemp() {
		List<ChongZhi> list = TempletService.listAll(ChongZhi.class
				.getSimpleName());
		Map<Integer, ChongZhi> temp = new HashMap<Integer, ChongZhi>();
		for (ChongZhi cz : list) {
			temp.put(cz.id, cz);
			if (cz.type == yuekaid) {
				yueka = cz;
			}
		}
		chongZhiTemp = temp;
	}

	@SuppressWarnings("unchecked")
	public void setVipFuncOpenTemp() {
		List<VipFuncOpen> list = TempletService.listAll(VipFuncOpen.class
				.getSimpleName());
		Map<Integer, VipFuncOpen> temp = new HashMap<Integer, VipFuncOpen>();
		for (VipFuncOpen func : list) {
			temp.put(func.key, func);
		}
		vipFuncOpenTemp = temp;
	}

	public void sendError(IoSession session, int cmd, String string) {
		qxmobile.protobuf.ZhangHao.RegRet.Builder ret = RegRet.newBuilder();
		ret.setUid(cmd);
		ret.setName(string);
		session.write(ret.build());
	}

	/**
	 * 获取vip信息
	 */
	public void getVipInfo(int cmd, IoSession session, Builder builder) {
		JunZhu jz = JunZhuMgr.inst.getJunZhu(session);
		if (jz == null) {
			log.error("玩家不存在");
			return;
		}
		long jId = jz.id;
		VipInfoResp.Builder resp = VipInfoResp.newBuilder();
		PlayerVipInfo info = HibernateUtil.find(PlayerVipInfo.class, jId);
		int vipLevel = info == null ? 0 : info.level;
		int vipExp = info == null ? 0 : info.vipExp;
		int needVipExp = 0;
		if (vipLevel == maxVip) {
			resp.setIsMax(true);
		} else {
			VIP v = vipTemp.get(vipLevel + 1);
			if (v == null) {
				log.error("根据lv:{},获取VIP配置数据出错:", vipLevel + 1);
			}
			needVipExp = v == null ? -1 : v.needNum;
			resp.setIsMax(false);
		}
		resp.setVipLevel(vipLevel);
		resp.setNeedYb(needVipExp);
		resp.setHasYb(vipExp);
		long time = System.currentTimeMillis();
		fillVIPinfo(resp, jId);
		log.info("获取玩家充值数据时间：{}", System.currentTimeMillis() - time);
		session.write(resp.build());
	}

	public void fillVIPinfo(VipInfoResp.Builder resp, long jid) {
		ChongTimes.Builder chongInfo = null;
		String sql = "select type, count(1) as cnt from VipRechargeRecord where accId =" + jid + " group by type";
		List<Map<String, Object>> list = HibernateUtil.querySql(sql);
		Map<Integer, Integer> cntMap = new HashMap<Integer, Integer>(list.size());
		for(Map<String, Object> db: list){
			cntMap.put((Integer)db.get("type"), ((BigInteger)db.get("cnt")).intValue());
		}
		for (Integer id : chongZhiTemp.keySet()) {
			chongInfo = ChongTimes.newBuilder();
			chongInfo.setId(id);
			Integer cnt = cntMap.get(id);
			chongInfo.setTimes(cnt == null ? 0 : cnt);
			resp.addInfos(chongInfo);
		}
	}

	public void recharge(int cmd, IoSession session, Builder builder) {
		JunZhu jz = JunZhuMgr.inst.getJunZhu(session);
		if (jz == null) {
			log.error("玩家不存在");
			return;
		}
		long jid = jz.id;
		RechargeReq.Builder req = (RechargeReq.Builder) builder;
		int chongZhiId = req.getType();
		log.info("服务器收到的君主id是:{}, 本次的充值类型是:{}", jid, chongZhiId);
		RechargeResp.Builder resp = RechargeResp.newBuilder();
		PlayerVipInfo vipInfo = HibernateUtil.find(PlayerVipInfo.class, jid);
		if (vipInfo == null) {
			vipInfo = new PlayerVipInfo();
			vipInfo.accId = jid;
			vipInfo.sumAmount = 0;
			vipInfo.level = 0;
			vipInfo.vipExp = 0;
		}
		Date now = new Date();
		int yueKaValid = 0;
		if (chongZhiId == yuekaid) {
			// 月卡充值进行不一样的处理
			VipRechargeRecord r = getLatestYuaKaRecord(jid);
			if (r != null) {
				int leftDay = r.yueKaValid - DateUtils.daysBetween(r.time, now);
				if (leftDay > buy_yueka_limit_days) {
					resp.setSumAoumnt(0);
					resp.setVipLevel(vipInfo.level);
					resp.setYueKaLeftDays(leftDay);
					resp.setIsSuccess(false);
					resp.setMsg("月卡充值还有余额，对玩家进行确认");
					session.write(resp.build());
					YuanBaoMgr.inst.diff(jz, 0, req.getAmount(), 0,
							YBType.YB_VIP_CHONGZHI, "充值失败，月卡还有余额");
					return;
				}
				yueKaValid = CanShu.YUEKA_TIME + leftDay;
			} else {
				yueKaValid = CanShu.YUEKA_TIME;
			}
		}
		ChongZhi data = chongZhiTemp.get(chongZhiId);
		if (data == null) {
			log.error("ChongZhi配置中未找到相关数据条目:{}", chongZhiId);
			YuanBaoMgr.inst.diff(jz, 0, req.getAmount(), 0,
					YBType.YB_VIP_CHONGZHI, "充值失败，ChongZhi配置中未找到相关数据条目:"+chongZhiId+"");
			return;
		}
		int amount = req.getAmount();
		if (amount <= 0) {
			log.error("充值金额不能为0或者小于0");
			sendError(session, cmd, "充值金额不能为0或者小于0");
			YuanBaoMgr.inst.diff(jz, 0, req.getAmount(), 0,
					YBType.YB_VIP_CHONGZHI, "充值失败，充值金额不能为0或者小于0");
			return;
		}
		int addYB = 0;
		// 对于限购特殊处理
		if (data.type == limit_type && getBuyCount(data.id, jid) == 0) {
			addYB = getAddyuanbao(data, true);
			log.info("玩家：{}， 限购类型:{}, 首次进行本类型充值，共增加元宝：{}", jid, data.id, addYB);
		} else {
			addYB = getAddyuanbao(data, false);
		}
		int nowYB = jz.yuanBao + addYB;
		log.info("玩家：{}，充值之前的元宝：{}， 充值之后的元宝：{}", jid, jz.yuanBao, nowYB);
		// jz.yuanBao = nowYB;
		if (chongZhiId == yuekaid) {
			YuanBaoMgr.inst.diff(jz, addYB, req.getAmount(), 0,
					YBType.YB_VIP_CHONGZHI, "购买月卡");
		} else {
			YuanBaoMgr.inst.diff(jz, addYB, req.getAmount(), 0,
					YBType.YB_VIP_CHONGZHI, "vip充值");
		}
		int vipExp = vipInfo.vipExp + data.addVipExp;
		log.info("玩家：{}，充值之前的vipExp：{}， 充值之后的vipExp：{}", jid, vipInfo.vipExp,
				vipExp);
		int vip = getVipLevel(vipInfo.level, vipExp);
		log.info("玩家：{}，充值之前的等级：{}， 充值之后的等级：{}", jid, jz.vipLevel, vip);
		jz.vipLevel = vip;
		HibernateUtil.save(jz);
		// 刷新首页玩家信息
		JunZhuMgr.inst.sendMainInfo(session);

		/*
		 * 说明： PlayerVipInfo,VipRechargeRecord,JunZhu 三张表中的vipLeve的值都表示vip等级 ,
		 * 三者保持时时同步。
		 */
		int sumRmb = vipInfo.sumAmount + amount;
		log.info("玩家：{}充值RMB，before：{}， after：{}", vipInfo.sumAmount, sumRmb);
		vipInfo.sumAmount = sumRmb;
		vipInfo.level = vip;
		vipInfo.vipExp = vipExp;
		HibernateUtil.save(vipInfo);

		VipRechargeRecord r = new VipRechargeRecord(jid, amount, new Date(),
				sumRmb, vip, chongZhiId, addYB, yueKaValid);
		HibernateUtil.save(r);
		log.info("玩家:{},充值人民币:{},成功，一次性增加了元宝:{}，现在玩家的元宝数:{}", jid, amount,
				addYB, nowYB);
		if (chongZhiId != yuekaid) {// 月卡不算首冲
			// 充值成功，判断首冲
			ShouchongInfo info = HibernateUtil.find(ShouchongInfo.class,
					"where junzhuId=" + jid + "");
			if (ShouchongMgr.instance.getShouChongState(info) == 0) {// 未完成首冲
				ShouchongMgr.instance.finishShouchong(jid);
			}
		}
		resp.setIsSuccess(true);
		resp.setSumAoumnt(addYB);
		resp.setVipLevel(vip);
		session.write(resp.build());
		// 每日任务列表中添加可以领取月卡奖励的条目
		if (chongZhiId == yuekaid) {
			DailyTaskMgr.INSTANCE.taskListRequest(PD.C_DAILY_TASK_LIST_REQ,
					session);
		}
	}

	public int getAddyuanbao(ChongZhi data, boolean isFirstRecharge) {
		if (isFirstRecharge) {
			return data.addNum + data.extraFirst;
		}
		return data.addNum + data.extraYuanbao;
	}

	public int getVipLevel(int vipLevel, int vipExp) {
		if (maxVip == vipLevel) {
			log.info("已经是最高vip等级");
			return vipLevel;
		}
		int temp = vipLevel;
		int i = 0;
		while (i++ < 10) {
			VIP vold = vipTemp.get(temp);
			VIP vnew = vipTemp.get(temp + 1);
			if (vold == null || vnew == null) {
				log.error("根据lv:{}或者{},获取VIP配置数据出错:", temp, temp + 1);
				return temp;
			}
			if (vipExp < vnew.needNum && vipExp >= vold.needNum) {
				return temp;
			}
			temp++;
		}
		return vipLevel;
	}

	public VipRechargeRecord getLatestYuaKaRecord(long jid) {
		String where = " WHERE accId = " + jid + " AND type = " + yuekaid;
		List<VipRechargeRecord> datas = HibernateUtil.list(
				VipRechargeRecord.class, where);
		if (datas == null || datas.size() == 0) {
			return null;
		}
		long time = 0;
		VipRechargeRecord record = null;
		for (VipRechargeRecord r : datas) {
			if (r == null) {
				continue;
			}
			if (r.time == null) {
				continue;
			}
			long rTime = r.time.getTime();
			if (rTime > time) {
				time = rTime;
				record = r;
			}
		}
		return record;
	}

	public int getBuyCount(int type, long jid) {
		String hql = "select count(record.type) from VipRechargeRecord  record  where record.type=" + type
    			+ " and record.accId =" + jid;
		int count = HibernateUtil.getCount(hql);
		return count;
	}

	/**
	 * 君主的vip等级是满足当前操作 例如：虔诚膜拜是否vip等级满足
	 * 
	 * @Title: isVipPermit
	 * @Description:
	 * @param key
	 *            : 例如: VipData.qianCheng_moBai
	 * @param junVipLevel
	 *            ： junzhu.vipLevel
	 * @return: true: vip等级够， false： vip等级不够
	 */
	public boolean isVipPermit(int key, int junVipLevel) {
		VipFuncOpen data = vipFuncOpenTemp.get(key);
		if (data == null) {
			log.error("key={}的VipFuncOpen配置数据不存在", key);
			return false;
		}
		int level = data.needlv;
		if (junVipLevel >= level) {
			return true;
		}
		return false;
	}

	public VIP getVIPByVipLevel(int vipLevel) {
		VIP vip = vipTemp.get(vipLevel);
		if (vip == null) {
			log.error("vipLevel={}的VIP配置数据不存在", vipLevel);
		}
		return vip;
	}

	/**
	 * 根据玩家的vip等级，获取，不同vip等级下，某些操作所允许的最大次数或者数值 例如：购买铜币次数
	 * 
	 * @Title: getValueByVipLevel
	 * @Description:
	 * @param vipLevel
	 *            : junzhu.vipLevel
	 * @param typeInfo
	 *            : 例如：VipData.bugMoneyTime
	 * @return 当前玩家vip等级所对应操作的允许值
	 */
	public int getValueByVipLevel(int vipLevel, int typeInfo) {
		VIP vip = getVIPByVipLevel(vipLevel);
		if (vip == null) {
			return 0;
		}
		switch (typeInfo) {
		case 1:
			return vip.bugMoneyTime;
		case 2:
			return vip.bugTiliTime;
		case 3:
			return vip.bugBaizhanTime;
		case 4:
			return vip.yujueDuihuan;
		case 5:
			return vip.saodangFree;
		case 6:
			return vip.xilianLimit;
		case 7:
			return vip.legendPveRefresh;
		case 8:
			return vip.YBxilianLimit;
		case 9:
			return vip.dangpuRefreshLimit;
		case 11:
			return vip.FangWubuildNum;
		case 12:
			return vip.mibaoCountLimit;
		case 13:
			return vip.youxiaTimes;
		case 14:
			return vip.YunbiaoTimes;
		case 15:
			return vip.JiebiaoTimes;
		case 16:
			return vip.InviteAssistTimes;
		case 17:
			return vip.LveduoTimes;
		case 18:
			return vip.HuangyeTimes;
		}
		return 0;
	}

	public double getDoubleValueByVipLevel(int vipLevel, int typeInfo) {
		VIP vip = getVIPByVipLevel(vipLevel);
		if (vip == null) {
			return 0;
		}
		switch (typeInfo) {
		case 10:
			return vip.baizhanPara;
		}
		return 0;
	}

	/**
	 * GM工具充值接口
	 * 
	 * @Title: gmAddRMB
	 * @Description:
	 * @param rmb
	 * @return
	 */
	public boolean gmAddRMB(int rmb, JunZhu jz) {
		if (jz == null)
			return false;
		long jid = jz.id;
		PlayerVipInfo vipInfo = HibernateUtil.find(PlayerVipInfo.class, jid);
		if (vipInfo == null) {
			vipInfo = new PlayerVipInfo();
			vipInfo.accId = jid;
			vipInfo.sumAmount = 0;
			vipInfo.level = 0;
			vipInfo.vipExp = 0;
		}
		int addYB = rmb * 10;
		if (addYB <= 0) {
			log.error("充值金额不能为0或者小于0");
			return false;
		}
		int nowYB = jz.yuanBao + addYB;
		YuanBaoMgr.inst
				.diff(jz, addYB, rmb, 0, YBType.YB_VIP_CHONGZHI, "vip充值");
		int vipExp = vipInfo.vipExp + addYB;
		log.info("GM:玩家：{}，充值之前的元宝：{}， 充值之后的元宝：{}", jid, jz.yuanBao, nowYB);
		log.info("GM:玩家：{}，充值之前的vipExp：{}， 充值之后的vipExp：{}", jid,
				vipInfo.vipExp, vipExp);

		int vip = getVipLevel(vipInfo.level, vipExp);
		log.info("GM:玩家：{}，充值之前的等级：{}， 充值之后的等级：{}", jid, jz.vipLevel, vip);
		jz.vipLevel = vip;
		HibernateUtil.save(jz);
		/*
		 * 说明： PlayerVipInfo,VipRechargeRecord,JunZhu 三张表中的vipLeve的值都表示vip等级 ,
		 * 三者保持时时同步。
		 */
		int sumRmb = vipInfo.sumAmount + rmb;
		log.info("GM:玩家：{}充值RMB，before：{}， after：{}", jid, vipInfo.sumAmount,
				sumRmb);
		vipInfo.sumAmount = sumRmb;
		vipInfo.level = vip;
		vipInfo.vipExp = vipExp;
		HibernateUtil.save(vipInfo);

		// GM工具充钱是-1
		VipRechargeRecord r = new VipRechargeRecord(jid, rmb, new Date(),
				sumRmb, vip, -1, addYB, 0);
		HibernateUtil.save(r);
		log.info("GM:玩家:{},充值人民币:{},成功，一次性增加了元宝:{}，现在玩家的元宝数:{}", jid, rmb,
				addYB, jz.yuanBao);
		return true;
	}

	public boolean hasYueKaAward(long jid) {
		VipRechargeRecord r = getLatestYuaKaRecord(jid);
		if (r == null) {
			return false;
		}
		int leftDay = r.yueKaValid - DateUtils.daysBetween(r.time, new Date());
		if (leftDay > 0) {
			return true;
		}
		return false;
	}
}