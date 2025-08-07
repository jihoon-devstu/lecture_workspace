<%@page import="com.sinse.boardapp.util.Paging"%>
<%@page import="com.sinse.mvcapp.model.News"%>
<%@page import="java.util.List"%>
<%@page import="com.sinse.mvcapp.repository.NewsDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<%! 
	NewsDAO newsDAO = new NewsDAO(); 
	Paging paging = new Paging();
%>
<%

	List<News> newsList = newsDAO.selectAll();
	//페이징 객체에게 계산을 맡긴다.
	paging.init(newsList, request);
%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/inc/head.jsp"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<style>
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

.pageNum{
	font-size:27px;
	color:red;
	font-weight:BOLD;
}

a{text-decoration: none}
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">

	$(()=>{
		$("button").click(()=>{
			location.href="/news/write.jsp";
		});
	});
</script>

</head>
<body>


	<table>
		<tr>
			<th>No</th>
			<th>기사 제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<%
		int curPos = paging.getCurPos();
		int num = paging.getNum();
		%>
		<%for(int i=1;i<=paging.getPageSize();i++) {%>
		<%if (curPos >= newsList.size() || num < 1) break; %>
		<%News news = newsList.get(curPos++);%>
		<tr>
			<td><%=num--%></td>
			<td><a href = "/news/content.jsp?news_id=<%=news.getNews_id()%>"><%=news.getTitle()%></a>[<%=news.getCommentList().size() %>]</td>
			<td><%=news.getWriter()%></td>
			<td><%=news.getRegdate()%></td>
			<td><%=news.getHit()%></td>
		</tr>
		<%} %>
		<tr>
			<td colspan="5">
  		<% if(paging.getFirstPage()-1 > 0){%>
  			<a href="/news/list.jsp?currentPage=<%=paging.getFirstPage()-1%>">◀</a>
  			<%}else{ %>
  				<a href="javascript:alert('이전 페이지가 없습니다')">◀</a>
  			<%} %>
  			
				<%for(int i =paging.getFirstPage(); i<=paging.getLastPage();i++){ %> 
				<%if(i>paging.getTotalPage()) break; %>
					<a <%if(paging.getCurrentPage() ==i) {%>class = "pageNum" <%} %>href = "/news/list.jsp?currentPage=<%=i%>">[<%=i %>]</a>
				<%} %>
				
			<%if(paging.getLastPage()<paging.getTotalPage()){ %>
			<a href="/news/list.jsp?currentPage=<%=paging.getLastPage()+1%>">▶</a>
			<%} %>
			</td>
		</tr>
		
		<tr>
			<td colspan="5">
				<button>글쓰기</button>
			</td>
		</tr>

	</table>

</body>
</html>
