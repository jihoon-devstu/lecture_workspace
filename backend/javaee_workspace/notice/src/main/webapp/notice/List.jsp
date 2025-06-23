<%@page import="com.sinse.notice.model.Notice"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Driver"%>
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


<!-- 

JSP란? Java Server Page 즉 자바 기술로 만든 서버 측에서 실행 되는 페이지.
		= 서블릿과 목적이 같다.
		차이점 ? 서블릿도 디자인을 표현할 수 있으나 , html 문서를 모두 문자열로 처리해야 하므로
					효율성이 극도로 떨어짐... 따라서 서블릿과 jsp중 누구를 사용해야 하느냐가
					고민된다면 , 판단 기준은 ? 현재 처리하려는 코드에서 디자인이 포함되어 있는지
					판단하면 된다....  M(.java) V(JSP , HTML) C 패턴 
		주요 경쟁자 ) ASP , PHP 와 같은 서버 측에서 실행되는 스크립트 언어와 경쟁...

JSP 코드를 작성할 수 있는 영역
1) [%page%] : 지시영역 - 현재 페이지에 대한 인코딩 등 페이지 자체에 대한 정보 작성
2) [%! %] : 선언부 - 멤버변수와 멤버 메서드를 작성할 수 있는 영역
3) [%%] : 스크립틀릿 영역 - 개발자가 로직을 작성할 수 있는 메서드 영역
4) [%=%] : 데이터 (문자,숫자,논리값,객체)를 작성하는 영역  -> out.print(); 의 줄임 표현.
 -->
 
 <% 
 	connect(); // 접속
 	//아래의 변수 out은 개발자가 현재 코드에서 선언한 적이 없음에도 불구하고그 기능을 제대로 한다...
 	//개발자가 정의하지 않아도 톰켓 컨테이너가 jsp내에서 기본적으로 사용할 수 있도록
 	//미리 생성해놓고 개발에 사용될수 있도록 지원하는 jsp 의 객체들을 가리켜 내장객체라한다.
 	//built - in object 라고 한다. 
 	out.print("접속객체는"+con);
 	
 	pstmt=con.prepareStatement("select *from notice order by notice_id desc");
 	rs = pstmt.executeQuery(); //쿼리 실행 및 표 반환
 	
 	List<Notice> list = new ArrayList();
 	
 	while(rs.next()){
 		Notice notice = new Notice();
 		notice.setNotice_id(rs.getInt("notice_id"));
 		notice.setTitle(rs.getString("title"));
 		notice.setWriter(rs.getString("writer"));
 		notice.setRegdate(rs.getString("regdate"));
 		notice.setContent(rs.getString("content"));
 		notice.setHit(rs.getInt("hit"));
 		
 		list.add(notice);
 	}
 
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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

a{
	text-decoration: none;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	/*
	제이쿼리는 아래의 문장 형식을 벗어나지 않는다.
	(누구를 - css선택자). 어떻게()
	*/
	$(()=>{
		$("button").click(()=>{
			//서버에 재 접속하여 글쓰기 폼을 요청
			location.href="/notice/write.html"; //<a> 태그를 자바스클비트로 처리할 경우... get 방식
			
		});
	});

</script>

</head>
<body>
	
<table>
  <tr>
    <th>No</th>
    <th>제목</th>
    <th>작성자</th>
    <th>등록일</th>
    <th>조회수</th>
  </tr>
  
  
  
  <%for(Notice notice : list) {%>
  
  <tr>
    <td>0</td>
    <td><a href = "/notice/detail.jsp?notice_id=<%=notice.getNotice_id()%>"><%= notice.getTitle() %></a></td>
    <td><%= notice.getWriter() %></td>
    <td><%= notice.getRegdate() %></td>
    <td><%= notice.getHit() %></td>
  </tr>
  <%} %>
  <tr>
  	<td colspan="5"><button type="button">글 등록</button></td>
  </tr>
</table>
</body>
</html>


<%

	rs.close();
	pstmt.close();
	con.close();

%>
