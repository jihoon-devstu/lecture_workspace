<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<form action="/blood.do" method="get">
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