<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Cookie[] cookies=request.getCookies();
	
	for(Cookie cookie : cookies){
		out.print("쿠키명은: "+cookie.getName()+", "+cookie.getValue());
	}


%>