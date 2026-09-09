<%@page import="com.bcs.zsg.core.cache.service.CacheService"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="org.apache.commons.lang.math.RandomUtils"%>
<%@page import="com.bcs.zsg.core.helper.BaseContext"%>
<%@page import="java.util.UUID"%>
<%@page import="com.bcs.zsg.component.common.vo.SysParamVO"%>
<%@page import="com.bcs.zsg.core.helper.BeanFactory"%>
<%@page import="com.bcs.zsg.component.common.dao.SysParamDAO"%>
<%
	try {
		CacheService cacheService = BeanFactory.getBean("cacheService", CacheService.class);
		cacheService.removeAll();
		
		out.println("Cached cleared!");
		
	} catch (Throwable t) {
		t.printStackTrace();
		out.println("<span style='color: red;'>" + t + "</span>");
		
	} finally {
	}
%>