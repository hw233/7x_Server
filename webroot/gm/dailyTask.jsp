<%@page import="java.util.HashMap"%>
<%@page import="qxmobile.protobuf.DailyTaskProtos.DailyTaskInfo"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="com.manu.network.SessionManager"%>
<%@page import="com.qx.activity.ActivityMgr"%>
<%@page import="com.manu.dynasty.template.DescId"%>
<%@page import="java.util.Map"%>
<%@page import="com.manu.dynasty.template.RenWu"%>
<%@page import="javax.swing.text.Document"%>
<%@page import="com.qx.event.EventMgr"%>
<%@page import="com.qx.task.DailyTaskConstants"%>
<%@page import="com.manu.dynasty.boot.GameServer"%>
<%@page import="com.qx.task.DailyTaskCondition"%>
<%@page import="com.qx.achievement.AchievementCondition"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.qx.junzhu.JunZhuMgr"%>
<%@page import="com.manu.dynasty.base.TempletService"%>
<%@page import="com.manu.dynasty.template.ExpTemp"%>
<%@page import="com.qx.junzhu.JunZhu"%>
<%@page import="com.qx.persistent.HibernateUtil"%>
<%@page import="com.qx.task.DailyTaskMgr"%>
<%@page import="com.qx.task.DailyTaskBean"%>
<%@page import="com.qx.event.Event"%>
<%@page import="com.qx.event.ED"%>
<%@page import="com.qx.account.Account"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@include file="/myFuns.jsp"%>
<%@page import="com.manu.dynasty.hero.service.HeroService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>query or delete email</title>
<script type="text/javascript">
function go(act){
	location.href = '?action='+act;
}
function ss(){
	alert("增加成功");
}
function ff(){
	alert("增加失败");
}

</script>
 
</head>
<body>
	<%
		String name = request.getParameter("account");
		name = name == null ? "" : name;
		if (session.getAttribute("name") != null && name.length() == 0) {
			  name = (String) session.getAttribute("name");
			}
	%>
	<form action="dailyTask.jsp">
		账号<input type="text" name="account" value="<%=name%>"><br>
		<button type="submit">查询</button>
	<%
	Account account = null;
	if(name != null && name.length()>0){
		account = HibernateUtil.getAccount(name);
	}
	if(account == null){
	    %>没有找到<%
    }else{
        session.setAttribute("name", name);
        %><br>注册账号：<%=account.accountName%>
        <%
         long junZhuId = account.accountId * 1000 + GameServer.serverId;
         JunZhu junzhu = HibernateUtil.find(JunZhu.class, junZhuId);
         if(junzhu == null){
            out("没有君主");
         }else{
	        %>
	        <br>
	        	君主id是：<%=junzhu.id%>
	        <br>
	        	君主姓名是：<%=junzhu.name%>
	        <%
	        String action = request.getParameter("action");
	        if(action != null){
	        	if("done".equals(action)){
	        		String taskIdStr = request.getParameter("taskId");
	        		if(taskIdStr != null){
	        			int taskId = Integer.parseInt(taskIdStr);
	        			RenWu renwu = DailyTaskMgr.renWuMap.get(taskId);
	        			DailyTaskBean bean = DailyTaskMgr.INSTANCE.getTaskByTaskId(junzhu.id, taskId);
	        			bean.jundu = renwu == null ? 1 :renwu.condition;
	        			bean.isFinish = true;
	        			bean.time = new Date();
	        			HibernateUtil.update(bean);
	        			IoSession su = SessionManager.inst.getIoSession(junZhuId);
	        			if(su != null ){
	        				DailyTaskMgr.INSTANCE.taskListRequest(1, su);
	        			}
	        		}
	        	}else if("reset".equals(action)){
	        		
	        		String taskIdStr = request.getParameter("taskId");
	        		if(taskIdStr != null){
	        			int taskId = Integer.parseInt(taskIdStr);
	        			RenWu renwu = DailyTaskMgr.renWuMap.get(taskId);
	        			DailyTaskBean bean = DailyTaskMgr.INSTANCE.getTaskByTaskId(junzhu.id, taskId);
	        			bean.isFinish = false;
	        			bean.isGetReward = false;
	        			bean.jundu = 0;
	        			bean.time = new Date();
	        			HibernateUtil.update(bean);
	        			IoSession su = SessionManager.inst.getIoSession(junZhuId);
	        			if(su != null ){
	        				DailyTaskMgr.INSTANCE.taskListRequest(1, su);
	        			}
	        		}
	        	}
	        	
	        }
	        long start = junZhuId * DailyTaskMgr.space ;
	        long end = start + DailyTaskMgr.space - 1;
	        List<DailyTaskBean> taskList = DailyTaskMgr.INSTANCE.getDailyTasks(junZhuId);
	        Map<Integer , DailyTaskBean>taskMap= new HashMap<Integer , DailyTaskBean>();
	        for(DailyTaskBean bean : taskList){
	        	taskMap.put((int)bean.dbId%100, bean);
	        }
	        List<DailyTaskInfo> taskInfoList = DailyTaskMgr.INSTANCE.fillTaskInfo(taskMap, junZhuId);
	        %>
	        <table border="1">
	        <%
	        if(taskList != null && taskList.size() > 0){
	        %>
	        <tr><th>任务id</th><th>任务进度</th><th>任务描述</th><th>是否完成</th><th>是否领奖</th><th>操作</th></tr>
	        <%
				for(DailyTaskInfo dtInfo : taskInfoList){
					RenWu task = DailyTaskMgr.renWuMap.get(dtInfo.getTaskId());
					DescId desc = ActivityMgr.descMap.get(task.funDesc);
				%>
				 <tr>
				 <td><%= dtInfo.getTaskId() %></td>
				 <td><%= dtInfo.getJindu() %></td>
				 <td><%= desc.description%></td>
				 <td><%= dtInfo.getIsFinish()?"已完成":"未完成" %></td>
				 <td><%= dtInfo.getIsGet() ?"已领奖":"未领奖" %></td>
				 <td>
				 <a href="?action=done&taskId=<%=dtInfo.getTaskId()%>">完成</a>
				 <a href="?action=reset&taskId=<%=dtInfo.getTaskId()%>">重置</a>
				 </td>
				 </tr>
				 <%
				}
	        }
	        %>
	        </table>
	        <%
      	}
         
	}
	
	
	
	
	br();
	out("---------------------------------------------");
	tableStart();
	trS();
	td("任务id");td("名称");td("描述");//td("类型");
	td("条件");td("奖励");td("联盟贡献");
	trE();
	List<Integer> ids = DailyTaskMgr.taskIdArr;
	Map<Integer, RenWu> m= DailyTaskMgr.renWuMap;
	for(Integer i: ids){
		RenWu r = m.get(i);
		if(r != null){
			trS();
			td(r.id);td(r.name); //td(r.funDesc);
			DescId desc = ActivityMgr.descMap.get(r.funDesc);
	        if(desc != null){
	        	td(desc.description);
	        }
			//td(r.type);
			td(r.condition);td(/*r.jiangli*/"");td(r.LmGongxian);
			trE();
		}
	}
	tableEnd();
	%>
	</form>
</body>
</html>