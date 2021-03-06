package com.qx.timeworker;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import qxmobile.protobuf.ErrorMessageProtos.ErrorMessage;

import com.manu.network.PD;
import com.manu.network.msg.ProtobufMsg;

public class FunctionID {
	public static Logger log = LoggerFactory.getLogger(FunctionID.class.getSimpleName());
	//国家-上缴
	public static int guoJia = 212;
	public static int ShangJiao4Gongjin=500020;
	public static int guojiaAward = 500022;
	// 签到
	public static int Qiandao=140;
	public static int LiJiQiandao=150;
	public static int LingQuShuangBei=160;
	// 首冲
	public static int Shouchong=142;
	
	public static int tieJiang = 12; // 铁匠
	public static int XiLian=1210; //铁匠-洗练
	public static int QiangHua = 1212; //铁匠-强化
	public static int XiangQian = 1213;//铁匠-镶嵌
	
	
	public static int shiLian = 250; //试练
	public static int youXia = 300;	// 试练-游侠
	public static int youXiaStatus = 305;// 试练-游侠-可攻打
	
	public static int level_reward = 1430;//运营活动，等级奖励
	
	// 百战
	public static int baizhan = 300100;
	public static int baizhanCount = 300103;
	public static int baiZhanRecord = 300105;
	public static int baiLingJiang = 300108;
	

	public static int junZhu = 200; // 角色
	public static int JiNengJinJie = 500007; //角色-技能进阶
	public static int jinJie = 1211; //角色-装备进阶
	public static int FuWen = 500010; //符文系统；开启条件有配置
	
	public static int mibao = 6; //秘宝
	public static int miBaoShengJi = 600; //秘宝升级
	public static int miBaoJiNeng = 610; // 秘宝技能激活
	public static int MiBaoShengXing = 602; //秘宝升星
	public static int MiBaoHeCheng = 605; //秘宝合成

	public static int MiBaoNEW = 701; //新秘宝

	// 荒野商店
	public static int huangYe_shop = 903;
	// 联盟商店
	public static int lianMeng_shop = 600700;
	// 联盟战商店
	public static int lianmeng_battle_shop = 902;
	// 百战商店
	public static int baizhan_shop=  300107;
//	// 普通商店
//	public static int common_shop = 5;
	// 神秘商店
	public static int mysterious_shop = 901;
	
	
	public static int PVE_PASS_ZHANGJIE_GET_AWARD = 300001; // 过关斩将 - 通章奖励领取
	
	/*联盟
	 * 只需要对应功能的最后一级变量
	 */
	public static int LianMeng = 104; // 一级
	public static int LianMengShenQing = 410000;//二级-联盟申请
	
	public static int LianMengKeZhan = 600500; //二级-联盟客栈
	public static int LianMengKeZhanDongTai = 600505; //三级-联盟客栈-联盟动态
	
	// 联盟商铺和书院不需要红点提示
	public static int LianMengShuYuanKiJi =  600600; // 二级联盟书院（科技）
//	public static int LianMengShangPu; // 二级-联盟商铺
//	public static int JiBai = 400018; // 二级-联盟-祭拜（不使用）

	public static int LianMengXiaoWu = 600750; // 二级-联盟-小屋
	public static int LianMengHouse = 600800; // 三级-联盟-小屋-领经验
	public static int LianMengHuanKa = 500050; // 三级-联盟-小屋-领经验

	public static int LianMengZongMiao = 600850; // 二级- 联盟宗庙
	public static int LianMengJiBai = 600900; // 三级- 联盟宗庙-联盟祭拜（抽奖，使用中）
	public static int YiJianJiBai = 600905; // 三级- 联盟宗庙-一键祭拜

	public static int HuangYeQiuSheng = 300200; // 二级-联盟-荒野求生

	public static int LianMengTuTeng = 300300; // 二级-联盟-图腾
	public static int QianChengZhi = 300900; // 三级-联盟-图腾-虔诚值
	public static int FengShan = 400017; // 三级-联盟-图腾-封禅
	public static int MoBai  = 400000;// // 三级-联盟-图腾-膜拜

	public static int MianFeiMoBai  = 400010;// // 四级-联盟-图腾-膜拜-免费膜拜
	public static int YuanBaoMoBai  = 400012;// // 四级-联盟-图腾-膜拜-元宝膜拜
	public static int YuJueMoBai  = 400015;// // 四级-联盟-图腾-膜拜-玉决膜拜
	
	public static int FengShanDaDian = 400100; // 四级-联盟-图腾-封禅-封禅大典
	public static int FengShanShengDian = 400110; // 四级-联盟-图腾-封禅-封禅盛典（99贡献值）

	
//	public static int lianmengJunQing = 410010; 
	public static int lianmengJunQingLveDuo = 410012; 
	public static int lianmengJunQingYabiao = 410015; 

	// 联盟事件
//	public static final int ALLIANCE_EVENT = 1000001;
//	 联盟新申请
//	public static final int ALLIANCE_NEW_APPLYER = 1000002;
	
//	public static final int LianMengZongMiao = 600900; //联盟祭拜（抽奖，使用中）---联盟宗庙
//	public static int LianMengHouse=600800; //联盟小屋（包含领经验和换卡）
//	public static final int fengShan = 400017; //联盟-封禅
//	public static int MianFeiMoBai = 400010; //联盟-免费膜拜
	// 联盟 end
	
	public static int tanBao = 11;  // 探宝
	public static int tanBao_free_yuanbao = 1102;// 矿井（元宝）抽奖
	public static int tanBao_free_tongbi = 1101; // 矿洞抽奖
	
	/*
	 * 战争
	 */
	public static final int chuZheng_zhanDou = 8; //战争
	public static int lveDuo = 211; //战争-掠夺
	public static int LueDuoJiLu = 220;		//战争-掠夺-历史记录
	public static int LueDuoCiShu = 215; //战争-掠夺-剩余次数
	public static int yabiao = 310; //战争-押镖
	public static int yabiao_history = 313;		//押镖-历史记录
	public static int yabiao_enemy = 315;//押镖-仇人
	public static int yabiao_ciShu = 312; //战争-行镖-剩余次数
	// 战争end
	

	public static int youxiang_system = 41;	// 邮箱-系统
	public static int youxiang_person = 10; // 邮箱 -私信
	public static int FU_WEN_EQUIP = 500014; // 符文红点（可穿戴）
	public static int FU_WEN_UPGRADE = 500016; // 符文红点（可升级）
	public static int FU_WEN_TIHUAN = 500017; // 符文红点（可替换）

	//福利
	public static final int yuekafuli=1393;//月卡福利
	public static final int tilifuli=1391;//体力福利
	public static final int fengcehongbao=1390;//封测红包福利
	
	//活动
	public static final int activity_shouchong = 1422; //首冲
	public static final int activity_yueka = 1393; //月卡
	public static final int activity_chengzhangjijin = 1394; //成长基金
	public static final int activity_tili = 1391; // 体力
	public static final int activity_levelAward = 600200; //等级奖励
 	public static final int activity_chengjiu = 144; //成就
 	
 	//称号
 	public static final int chenghao_junchengzhan = 510015;
 	public static final int chenghao_baizhan = 520015;
 	
 	//郡城战
 	public static final int city_war_can_enter = 300500; //有战场可进入
 	public static final int city_war_lianmeng_award = 310410; //联盟奖励
 	public static final int city_war_personal_award = 310420; //个人奖励
 	
	//推送某功能红点可以出现 和前段约定code为负数时红点消失
	public static void pushCanShowRed(long jzId,IoSession session,int Code){
//		log.info("向君主{}推送--<{}>可以出现提示红点",jzId,Code);
		ErrorMessage.Builder resp=ErrorMessage.newBuilder();
		resp.setErrorCode(Code);
		ProtobufMsg pm = new ProtobufMsg();
		pm.id = PD.RED_NOTICE;
		pm.builder = resp;
		session.write(pm);
	}
}
