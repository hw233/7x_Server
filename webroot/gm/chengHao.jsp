<%@page import="java.util.Map"%>
<%@page import="com.qx.junzhu.ChengHaoDao"%>
<%@page import="com.qx.award.AwardMgr"%>
<%@page import="com.manu.dynasty.util.DateUtils"%>
<%@page import="com.qx.junzhu.ChenghaoMgr"%>
<%@page import="com.manu.dynasty.template.Chenghao"%>
<%@page import="java.util.List"%>
<%@page import="com.qx.junzhu.ChengHaoBean"%>
<%@page import="com.qx.activity.XianShiActivityMgr"%>
<%@page import="com.manu.dynasty.store.Redis"%>
<%@page import="com.qx.yuanbao.YBType"%>
<%@page import="com.manu.dynasty.hero.service.HeroService"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.qx.junzhu.PlayerTime"%>
<%@page import="com.qx.world.PosInfo"%>
<%@page import="com.qx.yuanbao.YuanBaoMgr"%>
<%@page import="com.qx.vip.PlayerVipInfo"%>
<%@page import="com.qx.vip.VipMgr"%>
<%@page import="qxmobile.protobuf.JunZhuProto.BuyTimesInfo"%>
<%@page import="com.manu.network.PD"%>
<%@page import="com.manu.network.BigSwitch"%>
<%@page import="com.manu.network.SessionAttKey"%>
<%@page import="org.apache.mina.core.future.WriteFuture"%>
<%@page import="com.qx.account.FunctionOpenMgr"%>
<%@page import="com.manu.network.SessionUser"%>
<%@page import="com.manu.network.SessionManager"%>
<%@page import="com.qx.pve.PveMgr"%>
<%@page import="com.qx.junzhu.JunZhuMgr"%>
<%@page import="com.manu.dynasty.base.TempletService"%>
<%@page import="com.manu.dynasty.template.ExpTemp"%>
<%@page import="com.qx.junzhu.JunZhu"%>
<%@page import="com.qx.purchase.XiLian"%>
<%@page import="com.qx.yabiao.YaBiaoBean"%>
<%@page import="com.qx.persistent.HibernateUtil"%>
<%@page import="com.qx.account.Account"%>
<%@page import="com.qx.timeworker.TimeWorkerMgr"%>
<%@page import="com.manu.dynasty.template.FunctionOpen"%>
<%@page import="com.qx.alliance.AlliancePlayer"%>
<%@page import="qxmobile.protobuf.TimeWorkerProtos.TimeWorkerResponse"%>
<%@page import="com.manu.dynasty.boot.GameServer"%>
<%@page import="com.qx.purchase.PurchaseMgr"%>
<%@page import="com.qx.alliance.AllianceMgr"%>
<%@include file="/myFuns.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
function go(act){
	var v = document.getElementById(act).value;
	location.href = '?action='+act+"&v="+v;
}

</script>
<title>Insert title here</title>
</head>
<body>
<%
String idStr = request.getParameter("jzId");
if(idStr == null){
	out("请从玩家信息界面的连接访问本页");
	return;
}
String act = request.getParameter("act");
if("add".equals(act)){
	String chIdStr = request.getParameter("chId");
	if(chIdStr != null){
		List<Chenghao> confList = TempletService.listAll(Chenghao.class.getSimpleName());
		Chenghao cc = null;
		for(Chenghao conf : confList){
			if((conf.id+"").equals(chIdStr)){
				cc = conf;
				break;
			}
		}
		//ChengHaoBean bean = HibernateUtil.find(ChengHaoBean.class, "where jzId="+idStr+" and tid="+chIdStr);
		ChengHaoBean bean = ChengHaoDao.inst.getChengHaoBeanById(Long.parseLong(idStr),Integer.parseInt(chIdStr));
		if(bean == null && cc != null){
			long jzId = Long.parseLong(idStr);
			ChenghaoMgr.inst.add(jzId, cc);
			out("添加成功");
		}
	}
}
out("玩家ID:"+idStr);br();
%>
<a href='chengHao.jsp?jzId=<%=idStr%>'>刷新</a>
<form action="">
称号ID<input type='text' name='chId'/><input type="submit" value="添加"/>
<input type='hidden' name='jzId' value='<%=idStr%>'/>
<input type='hidden'  name='act' value='add'/>
</form>

已有称号：
<%
//List<ChengHaoBean> list = HibernateUtil.list(ChengHaoBean.class, "where jzId="+idStr);
Map<Integer,ChengHaoBean> m = ChengHaoDao.inst.getMap(Long.parseLong(idStr));
out("数量:"+m.size());br();
out("状态说明:G 已获得；U使用中");br();
tableStart();
ths("dbId,模板id,状态,失效时间");

for(Integer key:m.keySet()){
	ChengHaoBean bean = m.get(key);
	String dbId = request.getParameter("dbId");
	if("modExpire".equals(act) && String.valueOf(bean.dbId).equals(dbId)){
		String v = request.getParameter("v");
		bean.expireTime = DateUtils.text2Datetime(v);
		HibernateUtil.update(bean);
	}
	trS();
	td(bean.dbId);
	td(bean.tid);
	td(bean.state);
	String t = DateUtils.datetime2Text(bean.expireTime);
	tdS();
	%>
	<form>
	<input type='text' name='v' value='<%=t %>'/>
	<input type='hidden' value='<%=bean.dbId %>' name='dbId'/>
	<input type='hidden' value='modExpire' name='act'/>
	<input type='hidden' value='<%=idStr%>' name='jzId'/>
	<input type="submit" value="修改"/>
	</form>
	<%
	tdE();
	trE();
}
tableEnd();
//
br();
List<Chenghao> confList = TempletService.listAll(Chenghao.class.getSimpleName());
tableStart();
ths("id,name,价格,货币类型");
for(Chenghao ch : confList){
	trS();
	td(ch.id);td(ch.name); td(ch.price);
	td(ch.daibi == AwardMgr.ITEM_GONG_XUN ? "功勋" : "威望");
	trE();
}
tableEnd();
%>
</body>
</html>