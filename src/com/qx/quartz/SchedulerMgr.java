package com.qx.quartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.manu.dynasty.template.CanShu;
import com.qx.quartz.job.AllianceResouceOutputJob;
import com.qx.quartz.job.AllianceRewardStoreJob;
import com.qx.quartz.job.AllianceVoteJob;
import com.qx.quartz.job.BaiZhanDailyAwardJob;
import com.qx.quartz.job.BigHouseWorthReduceJob;
import com.qx.quartz.job.BroadcastJob;
import com.qx.quartz.job.CheckHouseDealJob;
import com.qx.quartz.job.DailyTaskJob;
import com.qx.quartz.job.FenBigHouseJob;
import com.qx.quartz.job.GuojiaChouhenJieSuanJob;
import com.qx.quartz.job.GuojiaDayRankResetJob;
import com.qx.quartz.job.GuojiaSetDiDuiGuoJob;
import com.qx.quartz.job.GuojiaWeekRankResetJob;
import com.qx.quartz.job.LianMengBySWDayRankResetJob;
import com.qx.quartz.job.LianMengBySWWeekRankResetJob;
import com.qx.quartz.job.LogPerHourJob;
import com.qx.quartz.job.LogPerMinuteJob;
import com.qx.quartz.job.YaBiaoManageJob;


public class SchedulerMgr {
	private static Scheduler scheduler;
	public static SchedulerMgr inst;
	private static Logger log = LoggerFactory.getLogger(SchedulerMgr.class);

	public SchedulerMgr(){
		inst = this;
		init();
	}
	public void init(){
		SchedulerFactory  sfa = new StdSchedulerFactory();
		try {
			scheduler = sfa.getScheduler();
			scheduler.start();
			log.info("获取scheduler成功");
		}catch (SchedulerException e) {
			log.error("获取scheduler失败");
			e.printStackTrace();
		}
	}

	/**
	 * 添加任务
	 * @Title: start 
	 * @Description:
	 * TODO 没有做测试，稍后几天，进行性能测试：(20141209)
	 */
	public void doSchedule(){
	//	addScheduler(TestJob.class, "0/10 * * * * ?");
		// 每天晚上21点百战日奖励添加
		addScheduler(BaiZhanDailyAwardJob.class, "0 0 21 * * ?");
		//每周一到周六晚上22点衰减高级房屋价值
		StringBuffer shuaijianTime=new StringBuffer();
		shuaijianTime.append("0 0 ").append(String.valueOf(CanShu.REFRESHTIME_GAOJIFANGWU)).append(" ? * 2-7");
		addScheduler(BigHouseWorthReduceJob.class, shuaijianTime.toString());//"0 0 22 ? * 2-7"
		//每周日22点分配大房子 1.0无大房子
//		StringBuffer fenpeiTime=new StringBuffer();
//		fenpeiTime.append("0 0 ").append(String.valueOf(CanShu.REFRESHTIME_GAOJIFANGWU)).append(" ? *  1");
//		addScheduler(FenBigHouseJob.class, fenpeiTime.toString());//"0 0 22 ? *  1"
		//每天0点检查未完成的房屋交易
		addScheduler(CheckHouseDealJob.class, "0 0 0 * * ?");
		//每天0点检查/更新联盟状态   TODO 0.99不上
//		addScheduler(AllianceVoteJob.class, "0 0 0 * * ?");
		// 每天0点刷新国家日榜
		addScheduler(GuojiaDayRankResetJob.class, "0 0 0 * * ?");
		// 每天0点刷新联盟昨日声望榜
		addScheduler(LianMengBySWDayRankResetJob.class, "0 0 0 * * ?");
		// 每周一0点刷新国家周榜
		addScheduler(GuojiaWeekRankResetJob.class, "0 0 0 ? * 2");
		// 每周一0点刷新联盟声望周榜
		addScheduler(LianMengBySWWeekRankResetJob.class, "0 0 0 ? * 2");
		/*
		 * 每日任务 固定时间更新每日任务列表
		 */
		addScheduler(DailyTaskJob.class, "0 0 12,14,18,20,21,0 * * ?");
		
		//开启押镖活动
		StringBuffer openYBTime=new StringBuffer();
		String[] openYB = CanShu.OPENTIME_YUNBIAO.split(":");
		int openH = Integer.parseInt(openYB[0]);
		int openM = Integer.parseInt(openYB[1]);
		openYBTime.append("0 ").append(openM).append(" ").append(openH).append(" * * ?");
		addScheduler(YaBiaoManageJob.class, openYBTime.toString());//"0 0 8 * * ?"
		//关闭押镖活动
		StringBuffer closeYBTime=new StringBuffer();
		String[] closeYB = CanShu.CLOSETIME_YUNBIAO.split(":");
		int closeH = Integer.parseInt(closeYB[0]);
		int closeM = Integer.parseInt(closeYB[1]);
		closeYBTime.append("0 ").append(closeM).append(" ").append(closeH).append(" * * ?");
		addScheduler(YaBiaoManageJob.class, closeYBTime.toString());//0 0 11 * * ?
		
		String time = CanShu.HUANGYEPVP_AWARDTIME;
		String[] timeArray = time.split(":");
		StringBuilder resOutputJobTime = new StringBuilder();
		resOutputJobTime.append("0 ").append(timeArray[1]).append(" ").append(timeArray[0]).append(" ").append(" * * ?");
		addScheduler(AllianceResouceOutputJob.class, resOutputJobTime.toString());
		addScheduler(AllianceRewardStoreJob.class, "0 0 * * * ?");
		//
		addScheduler(LogPerMinuteJob.class,"1 * * * * ?");
		addScheduler(LogPerHourJob.class,"0 30 * * * ?");
		addScheduler(BroadcastJob.class,"1 * * * * ?");//定时广播，没分钟检查
		// 每周一0点，向前窜着记录上一期仇恨值
		addScheduler(GuojiaChouhenJieSuanJob.class,"0 0 0 ? * 2");
		// 每天00:05，刷新国家敌对国
		addScheduler(GuojiaSetDiDuiGuoJob.class, "0 5 0 * * ?");
	}

	/**
	 * 任务列表
	 * @Title: addScheduler 
	 * @Description:
	 * @param jobClass
	 * @param time 时间通配符
	 */
	private void addScheduler(Class<? extends Job> jobClass, String time){
		JobDetail job = JobBuilder.newJob(jobClass).build();
		CronTrigger trigger =
				TriggerBuilder.newTrigger()
				.withSchedule(CronScheduleBuilder.cronSchedule(time)) 
				.build(); 
		try {
			scheduler.scheduleJob(job, trigger);
			log.info("添加job：{}到定时任务列表中成功", jobClass);
		} catch (SchedulerException e) {
			e.printStackTrace();
			log.error("添加job：{}到定时任务列表中失败", jobClass);
		}
	}

	/**
	 * 定时任务关闭，暂时没有被调用
	 * @Title: stop 
	 * @Description:
	 */
	public void stop(){
		try {
			scheduler.shutdown(true);
		} catch (SchedulerException e){
			e.printStackTrace();
		}
	}
}