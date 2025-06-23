<%@page import="com.sinse.notice.model.Notice"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%!
	//느낌표를 선언하면 , 선언부라하여 멤버 변수와 멤버 메서드를 작성할 수 있다.
	String driver="com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/notice";
	String user = "shop";
	String pwd = "1234";
	
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public void connect(){
		try{
		Class.forName(driver);
		con=DriverManager.getConnection(url,user,pwd);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

%>

<%
	//서블릿의 service() 메서드 영역
	connect();

	//클라이언트가 전송한 notice_id 값을 요청 객체로부터 꺼내서 사용해보자
	//jsp 에서는 필수적인 기능들을 이미 생성하여 변숴명까지도 지정해버리는데 , 이러한 객체를 내장객체라고 부름.
	
	String notice_id = request.getParameter("notice_id");
	
	StringBuffer sql = new StringBuffer();
	sql.append("select *from notice where notice_id="+notice_id); //유저가 선택한 바로 그 게시물의 pk
	pstmt = con.prepareStatement(sql.toString());
	
	rs = pstmt.executeQuery();
	
 		Notice notice = null;
 		
 	if (rs.next()){ //레코드가 존재할 때만....
 		notice = new Notice(); //모델 생성
 		notice.setNotice_id(rs.getInt("notice_id"));
 		notice.setTitle(rs.getString("title"));
 		notice.setWriter(rs.getString("writer"));
 		notice.setRegdate(rs.getString("regdate"));
 		notice.setContent(rs.getString("content"));
 		notice.setHit(rs.getInt("hit"));
 	}

	
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

* {
	box-sizing: border-box;
}

input[type=text], select, textarea{
	width: 100%;
	padding: 12px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	margin-top: 6px;
	margin-bottom: 16px;
	resize: vertical;
}

input[type=button] {
	background-color: #04AA6D;
	color: white;
	padding: 12px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

input[type=button]:hover {
	background-color: #45A049;
}

.container {
	border-radius: 5px;
	background-color: #F2F2F2;
	padding: 20px;
}
</style>
<script type="text/javascript">
		window.addEventListener("load", ()=>{
			//버튼에 이벤트 연결
			let bt=document.querySelector("input[type='button']");
			
			bt.addEventListener("click", ()=>{
				//유저가 작성한 폼 양식을, java기반의 서버로 전송하자 !! 
				//왜 js는 직접 mysql에 대해 insert 하지 않나??
				//보안 상 js의 코드는 html과 함께 작성되므로 , 보안이란게 없다 !!
				//따라서 직접 하지 못하므로 , 간접적으로 부탁만 할 수 있다.
				
				let form = document.querySelector("form");
				form.submit(); //폼 양식을 전송 !! 이 시점에 드디어 전송 발생.
				
		});
	});
	</script>

<!-- include summernote css/js -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote.min.js"></script>

<script type="text/javascript">

	$(document).ready(function(){
		$("#summernote").summernote();
		
		
		//버튼에 클릭 이벤트 연결
		$("input[type = 'button']").click(()=>{
			//직접 mysql 연동 불가능하므로 , 웹서버의 프로그램에게 부탁할거임. (request)
			//입력 양식의 범위를 설정하는 태그인 form 태그를 이용하여 입력 양식을 서버에
			//전송하자 !! HTTP 프로토콜에서 서버에 데이터를 전송할때는 POST 방식을 사용해야한다.. -> HTTP 우편 시스템과 흡사
			
			$("form").submit(); //전송이 발생 !! 
		});
		
	});
</script>


<body>

	<h3>Contact Form</h3>
	
	<div class="container">
		<form action="/notice/write" method = "post">
		
			<label for="fname">제목</label>
			 <input type="text" id="fname"name="title" value="<%notice.getTitle();%>"> 
			 
			 <label for="lname">작성자</label> 
			 <input type="text" id="lname"name="writer" value="<%notice.getWriter();%>">
			 
			  <label for="subject">내용</label> 		  
			  <textarea id = "summernote" name ="content" placeholder = "write something..." ></textarea>
			  
			  <input type="button" value="수정">
			  <input type="button" value="삭제">
			  <input type="button" value="목록">
		</form>
	</div>


</body>
</html>



<%
	con.close();
	


%>