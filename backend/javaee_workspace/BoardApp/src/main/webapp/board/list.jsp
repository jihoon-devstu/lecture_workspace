<%@ page contentType="text/html; charset=UTF-8"%>

<%

	int totalRecord= 521; //총 레코드 수
	int pageSize = 10; //한 페이지당 보여질 레코드 수
	int totalPage = (int)Math.ceil((float)totalRecord/pageSize);
	int blockSize = 10; //블럭당 보여질 페이지 수
	int currentPage =1; //(현재 유저가 보고 있는 페이지)
	if(request.getParameter("currentPage") !=null){ //페이지 파라미터가 넘어올때만...
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	int firstPage = (blockSize*((currentPage - 1) / blockSize))+1 ; //블럭당 시작 페이지78 = 71~80
			
	int lastPage = firstPage+blockSize-1; //블럭당 마지막 페이지
%>
<%="totalRecord="+totalRecord+"<br>" %>
<%="pageSize="+pageSize+"<br>" %>
<%="totalPage="+totalPage+"<br>" %>
<%="blockSize="+blockSize+"<br>" %>


<!DOCTYPE html>
<html>
<head>
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
</style>
</head>
<body>

<table>
  <tr>
    <th>First Name</th>
    <th>Last Name</th>
    <th>Points</th>
  </tr>
  <%for(int i=0; i<pageSize; i++) {%>
  <tr>
    <td>Jill</td>
    <td>Smith</td>
    <td>50</td>
  </tr>
  <%} %>
  <tr>
  			
  	<td colspan="3" align="center">
  			<a href="#">◀</a>
			<%for(int i =1; i<=blockSize;i++){ %>
			<a href="/board/list.jsp?currentPage=<%=i%>">[<%=i%>]</a>
			<%} %>
			<a href="#">▶</a>
	</td>
  </tr>
  
  
</table>

</body>
</html>
