package com.manu.dynasty.core.servlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import log.DBHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.manu.dynasty.base.service.CommonService;
import com.manu.dynasty.boot.GameServer;
import com.manu.dynasty.chat.ChatMgr;
import com.manu.dynasty.store.MemcachedCRUD;
import com.manu.dynasty.store.Redis;
import com.manu.network.BigSwitch;
import com.manu.network.SessionManager;
import com.manu.network.TXSocketMgr;
import com.qx.event.EventMgr;
import com.qx.guojia.GuoJiaMgr;
import com.qx.http.EndServ;
import com.qx.persistent.HibernateUtil;
import com.qx.pvp.LveDuoMgr;
import com.qx.quartz.SchedulerMgr;
import com.qx.util.TableIDCreator;

/**
 * 服务器启动、停止时须执行的东西
 */
public class InitServlet implements Servlet{
	public static Logger log = LoggerFactory.getLogger(InitServlet.class);

	public void init(ServletConfig config) throws ServletException {
		log.info("============server start begin");
		CommonService.getInstance().init();
		log.info("初始化国家数据");
		GuoJiaMgr.inst.initGuoJiaBeanInfo();
		log.info("初始化国家数据完成");
		new DBHelper();//读取配置
		log.info("============server start success...");
	}
	
	public void destroy() {
		// 通知登陆服：关服
		TXSocketMgr.getInst().acceptor.unbind();
		SessionManager.inst.closeAll();
		TXSocketMgr.getInst().acceptor.dispose();
		EndServ ser = new EndServ();
		ser.start();
		log.info("================game server begin to shutdown================");
		EventMgr.inst.work = false;
		EventMgr.shutdown();
		BigSwitch.inst.houseMgr.shutdown();
		BigSwitch.inst.ybMgr.shutdown();
		BigSwitch.inst.gjMgr.shutdown();
		BigSwitch.inst.heroMgr.shutdown();
		BigSwitch.inst.cardMgr.shutdown();
		BigSwitch.inst.scMgr.shutdown();
		BigSwitch.inst.accMgr.shutdown();
		BigSwitch.inst.pvpMgr.shutdown();
		ChatMgr.getInst().shutdown();
		LveDuoMgr.inst.shutdown();
		SchedulerMgr.inst.stop();
		GameServer.shutdown();
		MemcachedCRUD.sockIoPool.shutDown();
		TableIDCreator.sockIoPool.shutDown();
		Redis.destroy(); 
		HibernateUtil.getSessionFactory().close();
		log.info("================game server shutdown ok================");
	}

	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}


	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	
	
	public void initAllServerData(){
//		((EntityDataService)ServiceLocator.getSpringBean("entityDataService")).init();
	}
    
	
}
