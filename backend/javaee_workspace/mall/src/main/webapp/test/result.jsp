<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    		String addr = (String)request.getAttribute("addr");
    		String name = (String)session.getAttribute("name");
    		String nick = (String)application.getAttribute("nick");
    		String tel = (String)application.getAttribute("tel");
    	out.print("request에 담은 나의 주소는 "+addr);
    	out.print("<br>");
    	out.print("session에 담은 나의 이름은 "+name);
    	out.print("<br>");
    	out.print("application에 담은 나의 별명은 "+nick);
    	out.print("<br>");
    	out.print("tomcat 가동 시점에 담은 나의 연락처는 "+tel);
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>