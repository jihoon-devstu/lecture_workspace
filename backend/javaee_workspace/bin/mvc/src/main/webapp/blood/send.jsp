<%@page import="mvcproject.blood.model.BloodManager"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<%
	//jsp 파일 하나로 모든 것을 처리하는 방법
	String msg = null;
	out.print(msg);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<form action="/blood/send.jsp" method="get">
		<select name="blood">
			<option value="">혈액형 선택</option>
			<option value="A">A형</option>
			<option value="B">B형</option>
			<option value="AB">AB형</option>
			<option value="O">O형</option>
		</select>
		<button>전송</button>
	</form>
	
</body>
</html>