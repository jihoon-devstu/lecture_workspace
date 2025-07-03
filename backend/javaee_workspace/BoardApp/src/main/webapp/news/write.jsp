<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset = "UTF-8">
<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}

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

input[type=submit] {
  background-color: #04AA6D;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=submit]:hover {
  background-color: #45a049;
}

.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
</style>

<%@ include file = "/inc/head_link.jsp" %>

<script type="text/javascript">


	$(()=>{
		//서머노트 연동
		$("#content").summernote({
			height : 250
		});
		
		$("input[type='button']").click(()=>{
			$("form").attr({
				action:"/news/regist",
				method : "POST"
				//머리에 데이터를 실어 나르게 됨, 따라서 편지 봉투에 나르는 격. 문제1) 노출됨 / 문제2) 잘린다.
				//body인 POST로 보내자. 
				
			});
			$("form").submit();
		})
		
		
	});

</script>



</head>
<body>

<h3>Contact Form</h3>

<div class="container">
  <form>
    <label for="fname">Title</label>
    <input type="text" id="fname" name="title" placeholder="제목 입력..">

    <label for="lname">Writer</label>
    <input type="text" id="lname" name="writer" placeholder="작성자 입력..">

    <label for="subject">Content</label>
    <textarea id="content" name="content" placeholder="내용 입력.." style="height:200px"></textarea>

    <input type="button" value="Submit">
  </form>
</div>

</body>
</html>