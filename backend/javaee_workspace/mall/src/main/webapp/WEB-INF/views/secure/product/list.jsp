<%@page import="mall.util.Paging"%>
<%@page import="mall.domain.Size"%>
<%@page import="mall.domain.ProductSize"%>
<%@page import="mall.domain.ProductColor"%>
<%@page import="mall.domain.Color"%>
<%@page import="mall.domain.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	List<Product> productList = (List)request.getAttribute("productList");
	Paging paging = (Paging)request.getAttribute("paging");
%>
    
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>AdminLTE 3 | Dashboard</title>
	<%@ include file="../inc/head_link.jsp" %>
	<script src="/static/project/project_inc/custom.js"></script>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
<div class="wrapper">

  <!-- Preloader -->
  <div class="preloader flex-column justify-content-center align-items-center">
    <img class="animation__shake" src="/static/admin/dist/img/AdminLTELogo.png" alt="AdminLTELogo" height="60" width="60">
  </div>

  <!-- Navbar -->
	<%@ include file="../inc/navbar.jsp" %>
  <!-- /.navbar -->

  <!-- Main Sidebar Container -->
	<%@ include file="../inc/left_bar.jsp" %>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
   
   
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">상품 목록</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">상품관리>상품목록</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->


    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        
            <!-- 상품 등록 폼 시작 -->
				        <!-- /.row -->
        <div class="row">
          <div class="col-12">
            <div class="card">
              <div class="card-header">
                <h3 class="card-title">Responsive Hover Table</h3>

                <div class="card-tools">
                  <div class="input-group input-group-sm" style="width: 150px;">
                    <input type="text" name="table_search" class="form-control float-right" placeholder="Search">

                    <div class="input-group-append">
                      <button type="submit" class="btn btn-default">
                        <i class="fas fa-search"></i>
                      </button>
                    </div>
                  </div>
                </div>
              </div>
              <!-- /.card-header -->
              <div class="card-body table-responsive p-0">
                <table class="table table-hover text-nowrap">
                  <thead>
                    <tr>
                      <th>상품 번호</th>
                      <th>이미지</th>
                      <th>카테고리</th>
                      <th>상품명</th>
                      <th>브랜드</th>
                      <th>가격</th>
                      <th>할인가</th>
                      <th>색상</th>
                      <th>사이즈</th>
                    </tr>
                  </thead>
                  <tbody>
                  <% 
                  	int curPos = paging.getCurPos();
                  	int num = paging.getNum();
                  %>
                  <%for(int i=1; i<paging.getPageSize();i++){ %>
                  <%if(num<1) break; //1보다 작으면 데이터가 없는 것이므로 break %>
                  <%Product product = productList.get(curPos++); %>
                    <tr>
                      <td><%=num-- %></td>
                      <td> <img width="40px" src="/data/p_<%= product.getProduct_id()%>/<%=product.getImgList().get(0).getFilename()%>"> </td>
                      <td><%=product.getSubcategory().getSub_name() %></td>
                      <td><a href="/admin/admin/product/detail?product_id=<%=product.getProduct_id()%>"><%=product.getProduct_name() %></a></td>
                      <td><%=product.getBrand() %></td>
                      <td><%=product.getPrice() %></td>
                      <td><%=product.getDiscount() %></td>
                      <td>
                      <%
                      List<ProductColor> colorList = product.getColorList();
                      StringBuffer sb = new StringBuffer();	
                      for(ProductColor productColor : colorList){
                    	  Color color = productColor.getColor();
                    	  sb.append(color.getColor_name());
                    	  sb.append(", ");
                      }
                      out.print(sb.toString());
                      %>
                      </td>
                      <td>
					<%
                      List<ProductSize> sizeList = product.getSizeList();
                      sb.delete(0,sb.length());
                      for(ProductSize productSize : sizeList){
                    	  Size size = productSize.getSize();
                    	  sb.append(size.getSize_name());
                    	  sb.append(", ");
                      }
                      out.print(sb.toString());
                      %>
					</td>   
                    </tr>
                    <%} %>
                  </tbody>
                </table>
                 <div class="card-footer clearfix">
                <ul class="pagination pagination-sm m-0 float-left">
                  <li class="page-item"><a class="page-link" href="#">&laquo;</a></li>
                  <%for(int i = paging.getFirstPage();i<=paging.getLastPage();i++){ %>
                  <%if(i>paging.getTotalPage()) break; //총 페이지 수를 넘으면 반복문 빠져나오기 %>
                  <li class="page-item"><a class="page-link" href="#"><%=i %></a></li>
                  <%} %>
                  <li class="page-item"><a class="page-link" href="#">&raquo;</a></li>
                </ul>
              </div>
              </div>
              <!-- /.card-body -->
            </div>
            <!-- /.card -->
          </div>
        </div>
        <!-- /.row -->     
            <!-- 상품 등록 폼 끝 -->
            
            
      </div>
      <!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
	<%@ include file="../inc/footer.jsp" %>

  <!-- Control Sidebar -->
	<%@ include file="../inc/right_bar.jsp" %>
  <!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->

	<%@ include file="../inc/footer_link.jsp" %>
	<script src="/static/admin/custom/ProductImg.js"></script>
	<script>
	function printCategory(obj,list){
		let tag="<option value='0'>카테고리 선택</option>"
		for(let i=0; i<list.length;i++){
			if(obj=="#topcategory"){
				tag+="<option value='"+list[i].topcategory_id+"'>"+list[i].top_name+"</option>";
			}else if(obj=="#subcategory"){
				tag+="<option value='"+list[i].subcategory_id+"'>"+list[i].sub_name+"</option>";
			}else if(obj=="#color"){
				tag+="<option value='"+list[i].color_id+"'>"+list[i].color_name+"</option>";
			}else if(obj=="#size"){
				tag+="<option value='"+list[i].size_id+"'>"+list[i].size_name+"</option>";
			}
				
		}
		$(obj).html(tag);
	}
	
	//비동기 방식으로 서버에 요청을 시도하여 , 데이터 가져오기
	function getTopCategory(){
		$.ajax({
			url:"/admin/admin/topcategory/list",
			type:"get",
			success:function(result, status, xhr){ //200번대의 성공 응답 시, 이 함수 실행
				printCategory("#topcategory",result);
			},
			error:function(xhr,status,err){
			}
		});
	
	}
	
	function getSubCategory(topcategory_id){

		$.ajax({
			url :"/admin/admin/subcategory/list?topcategory_id="+topcategory_id,
			type:"GET",
			success:function(result, status, xhr){
				printCategory("#subcategory",result);
			}
		
		});
	
	}
	
	function getColorList(){
		$.ajax({
			url:"/admin/admin/color/list",
			type:"get",
			success:function(result, status, xhr){ //200번대의 성공 응답 시, 이 함수 실행
				printCategory("#color",result);
			},
			error:function(xhr,status,err){
			}
		});
	}
	
	function getSizeList(){
		$.ajax({
			url:"/admin/admin/size/list",
			type:"get",
			success:function(result, status, xhr){ //200번대의 성공 응답 시, 이 함수 실행
				printCategory("#size",result);
			},
			error:function(xhr,status,err){
			}
		});
	}
	
	//크롬 브라우저에서 지원하는 e.target.files 유사 배열은 읽기전용 !
	//개발자가 쓰기가 안되므로, 배열을 하나 선언하여 , 담아서 처리
	//주의) 아래의 배열은 , 개발자가 정의한 배열일 뿐이지 , form 태그가 전송할 컴포넌트는 아니므로,
	//submit 시, selectedFile에 들어있는 파일을 전송할 수 는 없다.
	//해결책 ? form태그에 인식을 시켜야 한다... (javascript로 프로그래밍적 formData 객체를 사용해야 함.)
	//HTML 작성된 기존 폼에서 텍스트 입력관련된 컴포넌트는 사용하되 , 이미지 업로드 컴포넌트는 재설정해야함...
	let selectedFile=[];
	
	
	function regist(){
		//기존 폼을 이용하되, file 컴포넌트 파라미터만 새로 교체(selectedFile 배열로 대체)
		//js에서 프로그래밍 적 form 생성
		let formData = new FormData(document.getElementById("form1"));
		//formData 동기/비동기 둘다 지원하지만 , 대부분 비동기 방식을 많이 씀
		//Jquery Ajax 자체에서 formData를 비동기 방식으로 간단하게 사용할 수 있는 코드를 지원
		//기존 photo 버리고 ,. 우리가 선언한 배열로 대체
		//formData는 개발자가 명시하지 않아도 , 디폴트로 multipart/form-data가 지정되어있음.
		//기존의 photo 파라미터 제거하기 append의 반대
		
		formData.delete("photo");
		
		
		for(let i=0; i<selectedFile.length;i++){
			formData.append("photo",selectedFile[i]); //<input type="text" name="email">
		}
		//파일 마저도 비동기로 업로드 가능 !!!
		$.ajax({
			url:"/admin/admin/product/regist",
			type:"post",
			data: formData,
			processData:false, /*form을 이루는 대상으로, 문자열로 변환되는 것을 방지(바이너리 파일 포함 때문)*/
			contentType:false, /*브라우저가 자동으로 content-type을 설정하도록 하는 것 방지*/
			success:function(result, status, xhr){
				alert("업로드 성공");
			},
			error:function(xhr,status,err){
				alert(err);
			}
			
		});
	}
	
	
	  $(()=>{
		    $('#summernote').summernote({
				height:200,
				placeholder:"상품 상세 설명을 채우세요"
		    });
		    
		    //상위 카테고리 가져오기
		    getTopCategory();
		    //색상 목록 가져오기
		    getColorList();
		    //사이즈 목록 가져오기
		    getSizeList();
		    //상위 카테고리의 값을 변경시, 하위 카테고리 가져오기
		    $("#topcategory").change(function(){
				getSubCategory($(this).val());
		    });
		    
		    //파일 컴포넌트의 값 변경 시 이벤트 연결
		    $("#photo").change(function(e){
		    	console.log(e);
		    	//e.target.files 안에는 브라우저가 읽어들인 , 파일의 정보가 배열 유사 객체인 FileList에 담겨져 있다.
		    	let files = e.target.files; //배열 유사 객체 접근
		    	
		    	//첨부된 파일 수 만큼 반복
		    	for(let i=0;i<files.length;i++){
					selectedFile[i] = files[i];
					//파일을 읽기 위한 스트림 객체 생성
					const reader = new FileReader();
					
					reader.onload=function(e){ //파일을 스트림으로 읽어들인 정보가 e에 들어있음.
						console.log("읽은 결과는 = ",e);
						//e.target.result에 이미지 정보가 들어있음 !! img src에 속성에 대입할 예정
						
						let productImg = new ProductImg(document.getElementById("preview"),files[i],e.target.result,50,50);
					}
					
					reader.readAsDataURL(files[i]); //지정한 파일을 읽기
		    		
					
		    	}
		    	
		    });
		    
		    //등록버튼 이벤트 연결
		    $("#bt_regist").click(()=>{
				regist();
			});
		    
		    //목록버튼 이벤트 연결
		    $("#bt_list").click(()=>{
				$(location).attr("href","/admin/admin/product/list");
			});
		    
		    
	  });
</script>
</body>
</html>
