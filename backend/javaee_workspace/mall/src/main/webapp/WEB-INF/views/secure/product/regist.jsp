<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>AdminLTE 3 | Dashboard</title>
	<%@ include file="../inc/head_link.jsp" %>
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
            <h1 class="m-0">상품 등록</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">상품관리>상품등록</li>
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
            <div class="card card-primary">
              <div class="card-header">
                <h3 class="card-title">상품 등록 폼</h3>
              </div>
              <!-- /.card-header -->
              <!-- form start -->
              <form>
                <div class="card-body">
                
                
                	<!-- 카테고리 영역 시작 -->
                  <div class="row">
                    <div class="col-sm-6">
                      <!-- Select multiple-->
                      <div class="form-group">
                      
                        <label>상위 카테고리</label>
                        <select class="form-control" id="topcategory">
                        <option value="0">상위 카테고리 선택</option>
                        </select>
                        
                      </div>
                    </div>
                    <div class="col-sm-6">
                      <div class="form-group">
                        <label>하위 카테고리</label>
                        <select class="form-control" id="subcategory">
                          <option>하위 카테고리 선택</option>
                        </select>
                      </div>
                    </div>
                  </div>                	
					<!-- 카테고리 영역 끝 -->     
					
					           
                  <div class="form-group">
                    <input type="email" class="form-control" name="product_name" placeholder="상품명 입력">
                  </div>
                  <div class="form-group">
                    <input type="email" class="form-control" name="brand" placeholder="브랜드 입력">
                  </div>
                  <div class="form-group">
                    <input type="email" class="form-control" name="price" placeholder="가격 입력">
                  </div>
                  <div class="form-group">
                    <input type="email" class="form-control" name="discount" placeholder="할인가 입력">
                  </div>
                  <div class="form-group">
                    <input type="email" class="form-control" name="introduce" placeholder="간단소개 100자이하입력">
                  </div>
					<div class="form-group">
                        <select class="form-control">
                          <option>색상 선택</option>
                        </select>
                      </div>
                      <div class="form-group">
                        <select class="form-control">
                          <option>사이즈 선택</option>
                        </select>
                      </div>
                      
                           <!-- 편집기 시작 -->
                            <!-- /.card-header -->
							<div class="form-group" name="detail">
								<textarea id="summernote"></textarea>
							</div>
					      <!-- 편집기 끝-->
					      
                  <div class="form-group">
                    <label for="exampleInputFile">File input</label>
                    <div class="input-group">
                      <div class="custom-file">
                        <input type="file" class="custom-file-input" id="exampleInputFile">
                        <label class="custom-file-label" for="exampleInputFile">Choose file</label>
                      </div>
                      <div class="input-group-append">
                        <span class="input-group-text">Upload</span>
                      </div>
                    </div>
                  </div>
                  <div class="form-check">
                    <input type="checkbox" class="form-check-input" id="exampleCheck1">
                    <label class="form-check-label" for="exampleCheck1">Check me out</label>
                  </div>
                </div>
                <!-- /.card-body -->

                <div class="card-footer">
                  <button type="button" class="btn btn-primary">Submit</button>
                </div>
              </form>
            </div>
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
	<script>
	function printCategory(obj,list){
		let tag="<option value='0'>카테고리 선택</option>"
		for(let i=0; i<list.length;i++){
			if(obj=="#topcategory"){
				tag+="<option value='"+list[i].topcategory_id+"'>"+list[i].top_name+"</option>";
			}else if(obj=="#subcategory"){
				tag+="<option value='"+list[i].subcategory_id+"'>"+list[i].sub_name+"</option>";
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
	  $(()=>{
		    $('#summernote').summernote({
				height:200,
				placeholder:"상품 상세 설명을 채우세요"
		    });
		    
		    //상위 카테고리 가져오기
		    getTopCategory();
		    
		    //상위 카테고리의 값을 변경시, 하위 카테고리 가져오기
		    $("#topcategory").change(function(){
				getSubCategory($(this).val());
		    });
	  });
</script>
</body>
</html>
