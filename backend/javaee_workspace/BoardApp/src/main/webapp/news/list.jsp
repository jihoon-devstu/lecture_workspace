<%@ page contentType="text/html; charset=UTF-8"%>
<%
	int totalRecord=278; //총 레코드 수
	int pageSize =10; //페이지당 보여질 레코드 수
	int totalPage = (int)Math.ceil((float)totalRecord/pageSize); //총 페이지수
	int blockSize = 10; //블럭당 보여질 페이지 수
	int currentPage =1; //현재 보고있는 페이지
	if(request.getParameter("currentPage")!=null){ //페이지가 넘어올때만
	currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	
	int firstPage = currentPage - (currentPage-1)%blockSize; //블럭당 시작 페이지
	int lastPage = firstPage+(blockSize-1); //블럭당 마지막 페이지
	
	int num = totalRecord-((currentPage-1)*pageSize);

%>
<%="currentPage =" +currentPage %>

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

<%@ include file = "/inc/head_link.jsp" %>

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
		<%for(int i=1; i<=pageSize;i++) {%>
		<%if(num<1) break; %>
		<tr>
			<td><%=num-- %></td>
			<td>Smith</td>
			<td>50</td>
			<td>50</td>
			<td>50</td>
		</tr>
		<%} %>
		<tr>
			<td colspan="5">
				<a href = "#">◀</a>
				<%for(int i =firstPage; i<=lastPage;i++){ %> 
				<%if(i>totalPage) break; %>
					<a <%if(currentPage ==i) {%>class = "pageNum" <%} %>href = "/news/list.jsp?currentPage=<%=i%>">[<%=i %>]</a>
				<%} %>
				<a href = "#">▶</a>
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
