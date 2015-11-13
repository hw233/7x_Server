package com.qx.account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.manu.dynasty.base.TempletService;
import com.manu.dynasty.store.MemcachedCRUD;
import com.manu.dynasty.template.FunctionOpen;
import com.manu.dynasty.template.ZhuXian;
import com.qx.task.GameTaskMgr;
import com.qx.task.WorkTaskBean;

/**
 * @author 康建虎
 *
 */
public class FunctionOpenMgr {
	public static String REN_WU_OVER_ID = "RenWuOverId#";
	public static String awardRenWuOverIdKey = "AwardRenWuOverId#";
	public static FunctionOpenMgr inst = new FunctionOpenMgr();
	public static Logger log = LoggerFactory.getLogger(FunctionOpenMgr.class);
	public static List<Integer> initIds; 
	public static FunctionOpen[] others; 
	public static List<FunctionOpen> list;
	public static Map<Integer, FunctionOpen> openMap = new HashMap<Integer, FunctionOpen>();
	public static int HUANG_YE_QIU_SHENG = 300200;
	public static final int TYPE_ALLIANCE = 104;			//联盟类型

	public  void init(){
		list = TempletService.listAll(FunctionOpen.class.getSimpleName());
		initIds = new ArrayList<Integer>();
		ArrayList<FunctionOpen> othersL = new ArrayList<FunctionOpen>();
		for(FunctionOpen o : list){
			if(o.lv<=0 && o.RenWuID<=0 && o.RenWuIDAward <=0){
				initIds.add(o.id);
			}else{
				othersL.add(o);
				if(o.RenWuID>0){
					ZhuXian rwConf = GameTaskMgr.inst.zhuxianTaskMap.get(o.RenWuID);
					if(rwConf != null){
						o.renWuOrderIdx = rwConf.orderIdx;
					}
				}
				if(o.RenWuIDAward>0){
					ZhuXian rwConf = GameTaskMgr.inst.zhuxianTaskMap.get(o.RenWuIDAward);
					if(rwConf != null){
						o.awardRenWuOrderIdx = rwConf.orderIdx;
					}
				}
			}
			openMap.put(o.id, o);
		}
		others = new FunctionOpen[othersL.size()];
		othersL.toArray(others);
	}
	public  void fillOther(qxmobile.protobuf.ZhangHao.LoginRet.Builder ret, int level, long pid) {
		int len = others.length;
		int maxRenWuId = getMaxRenWuOrderIdx(pid);//注意！这一行和下面一行的顺序不能变！
		int maxAwardRenWuId = getMaxAwardRenWuOrderIdx(pid);
		if(maxRenWuId>0 && maxAwardRenWuId==0){//memcached清除后，修正maxAwardRenWuId
			maxAwardRenWuId = maxRenWuId-1;
		}
		for(int i=0; i<len; i++){
			//
			FunctionOpen o = others[i];
			//等级和任务只有一个限制
			if(o.lv >0){
				if(o.lv>level){//要求等级大于当前等级，不开
					continue;
				}
			}else if(o.RenWuID > 0){
				if(o.renWuOrderIdx>maxRenWuId){//要求任务大于当前最大的完成了的任务的序号，不开。
					continue;
				}
			}else if(o.RenWuIDAward > 0){
				if(o.awardRenWuOrderIdx>maxAwardRenWuId){
					continue;
				}
			}
			ret.addOpenFunctionID(o.id);
		}
	}
	public  int getMaxAwardRenWuOrderIdx(long pid){
		Object mcV = MemcachedCRUD.getMemCachedClient().get(awardRenWuOverIdKey+pid); 
		int maxAwardRenWuId = 0;// 领奖的最大任务id
		if(mcV != null){
			maxAwardRenWuId = Integer.valueOf(mcV.toString());
		}else{
			List<WorkTaskBean> rwlist = GameTaskMgr.inst.getTaskList(pid);
			// 找到数据库中存储的主线任务的最大id
			for(WorkTaskBean b : rwlist){
				ZhuXian conf = GameTaskMgr.inst.zhuxianTaskMap.get(b.tid);
				if(conf != null && conf.type == GameTaskMgr.zhuXianType){
					maxAwardRenWuId = Math.max(maxAwardRenWuId, b.tid);
				}
			}
			// maxAwardRenWuId(!=0)的触发任务一定是领了奖励的主线任务
			ZhuXian conf = GameTaskMgr.inst.zhuxianTaskMap.get(maxAwardRenWuId);
			if(conf != null){
				// 第一条任务
				if(maxAwardRenWuId != 100000){
					maxAwardRenWuId = Integer.parseInt(conf.getTriggerCond());
				}
				MemcachedCRUD.getMemCachedClient().set(awardRenWuOverIdKey+pid, maxAwardRenWuId);
			}
		}
		ZhuXian conf = GameTaskMgr.inst.zhuxianTaskMap.get(maxAwardRenWuId);
		if(conf == null){
			log.error("没有找到任务配置{} of pid {}",maxAwardRenWuId, pid);
			maxAwardRenWuId = 0;
		}else{
			maxAwardRenWuId = conf.orderIdx;
		}
		return maxAwardRenWuId;
	}

	public  int getMaxRenWuOrderIdx(long pid) {
		Object mcV = MemcachedCRUD.getMemCachedClient().get(REN_WU_OVER_ID+pid); 
		int maxRenWuId = 0;//已完成的最大任务ID。
		if(mcV != null){
			maxRenWuId = Integer.valueOf(mcV.toString());
		}else{
			List<WorkTaskBean> rwlist = GameTaskMgr.inst.getTaskList(pid);
			for(WorkTaskBean b : rwlist){
				maxRenWuId = Math.max(maxRenWuId, b.tid);
			}
			MemcachedCRUD.getMemCachedClient().set(REN_WU_OVER_ID+pid, maxRenWuId);
		}
		ZhuXian maxRenWuConf = GameTaskMgr.inst.zhuxianTaskMap.get(maxRenWuId);
		if(maxRenWuConf == null){
			log.error("没有找到任务配置{} of pid {}",maxRenWuId, pid);
			maxRenWuId = 0;
		}else{
			maxRenWuId = maxRenWuConf.orderIdx;
		}
		return maxRenWuId;
	}

	/**
	 * @Title: isFunctionOpen 
	 * @Description:
	 * @param key : FunctionOpen.xml的id
	 * @param junZhuId
	 * @param level 当是以任务为开启功能的方式是，<level>参数可以为任何值
	 * @return
	 */
	public  boolean isFunctionOpen(int key, long junZhuId, int level){
		FunctionOpen o = openMap.get(key);
		if(o == null){
			return false;
		}
		if(o.lv == 0){
			return true;
		}
		if(o.lv > 0){
			if(level >= o.lv){//要求等级大于当前等级，不开
				return true;
			}
			return false;
		}
		if(o.RenWuID > 0){
			int maxRenWuId = getMaxRenWuOrderIdx(junZhuId);
			if(maxRenWuId >= o.renWuOrderIdx){//要求任务大于当前最大的完成了的任务的序号，不开。
				return true;
			}
			return false;
		}
		if(o.RenWuIDAward > 0){
			int max = getMaxAwardRenWuOrderIdx(junZhuId);
			if(max >= o.awardRenWuOrderIdx){//要求任务大于当前最大的完成了的任务的序号，不开。
				return true;
			}
			return false;
		}
		return false;
	}
}