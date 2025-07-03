<%@page import="com.sinse.boardapp.model.Comment"%>
<%@page import="java.util.List"%>
<%@page import="com.sinse.boardapp.repository.CommentDAO"%>
<%@page import="com.sinse.boardapp.model.News"%>
<%@page import="com.sinse.boardapp.repository.NewsDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%!NewsDAO newsDAO = new NewsDAO();
		CommentDAO commentDAO = new CommentDAO();%>
<%
//요청 객체로부터 파라미터 뽑아내기
//이 스크립틀릿 영역은 이 jsp가 서블릿으로 변경되어질 때 , service() 메서드 영역이므로 , 이미 service() 메서드의
// 매개변수 요청 객체와 응답 객체를 넘겨받은 상태....
//service(HttpServletRequest request , HttpServletEResponse response) 
String news_id = request.getParameter("news_id");
out.print("넘겨받은 파라미터는" + news_id);
News news = newsDAO.select(Integer.parseInt(news_id));
List<Comment> commentList = commentDAO.selectByNewsId(Integer.parseInt(news_id));
%>


<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

* {
	box-sizing: border-box;
}

input[type=text], select, textarea {
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
	background-color: #45a049;
}

.container {
	border-radius: 5px;
	background-color: #f2f2f2;
	padding: 20px;
}

table {
	border-collapse: collapse;
	border-spacing: 0;
	width: 100%;
	border: 1px solid #ddd;
}

th, td {
	text-align: left;
	padding: 16px;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}
</style>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<!-- include libraries(jQuery, bootstrap) -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote.min.js"></script>

<script type="text/javascript">
$(()=>{
	//서머노트 연동
	$("#content").summernote({
		height : 250,
	});
	$("#content").summernote('code', "<%=news.getContent()%>");
	
	//버튼에 이벤트 연결
	//0번째 - 수정
	$("#bt_news_edit").click(()=>{
		if(confirm("수정하시겠습니까?")){
			//서버로 입력폼의 내용을 모두 가져가야 하므로, Post 방식으로 보내야함.
			$("form").attr({
				method:"POST",
				action:"/news/update"
			});
			$("form").submit();
		}
	});
	//1번째 삭제
	$("#bt_news_del").click(()=>{
		
	});
	
	//2번째 - 목록
	$("#bt_news_list").click(()=>{
		location.href="/news/list.jsp";
	});
	
	//댓글 등록
	$("#bt_comment_regist").click(()=>{
		$("#comment_form").attr({
			method:"post", //HTTP프로토콜 통신에 사용되는 데이터 구성 (Payload) body에 탑재됨...
			action:"/comment/regist"
		});
		$("#comment_form").submit();
	});
	
});

</script>

</head>
<body>

	<div class="container">
		<form>

			<label for="fname">Title</label> 
			<input type="text" id="fname"name="title" value="<%=news.getTitle()%>"> 
			
			<label for="lname">Writer</label> 
			<input type="text" id="lname" name="writer" value="<%=news.getWriter()%>"> 
			
			<label for="subject">Content</label>
			<textarea id="content" name="content" "style="height: 200px"></textarea>

			<input type="button" value="수정" id="bt_news_edit"> 
			<input type="button" value="삭제" id="bt_news_del">
			<input type="button" value="목록" id="bt_news_list">
		</form>

		<div id="comment_header">
			<form id = "comment_form">
				<input type="hidden" name="news_id" value="<%=news.getNews_id()%>">
				<input type="text" style="width: 73%" name="msg">
				<input type="text" style="width: 20%" name="user"> 
				<input type="button" value="등록" id="bt_comment_regist">
			</form>
		</div>
		
		<div id="comment_content">
			<table>
				<tr>
					<th>댓글 제목</th>
					<th>작성자</th>
					<th>등록일</th>
				</tr>
				<%for(Comment comment : commentList){ %>
				<tr>
					<th><%=comment.getMsg() %></th>
					<th><%=comment.getUser() %></th>
					<th><%=comment.getMsgdate() %></th>
				</tr>
				<%} %>
			</table>

		</div>

	</div>

</body>
</html>