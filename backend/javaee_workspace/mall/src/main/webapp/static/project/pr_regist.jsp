<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>AdminLTE 3 | Projects</title>
<!-- Style include -->
<%@ include file="/static/project/project_inc/head_link.jsp"%>
<script src="/static/project/project_inc/custom.js"></script>
<!-- /Style include -->
<!-- head_link -->
<%@ include file="/static/project/project_inc/footer_link.jsp"%>
<!-- head_link -->
<style type="text/css">
table.table td, table.table th {
	text-align: center;
}
</style>

</head>

<body class="hold-transition sidebar-mini">
	<!-- Navbar -->
	<%@ include file="/static/project/project_inc/navbar.jsp"%>
	<!-- /Navbar -->

	<!-- Main Sidebar Container -->
	<%@ include file="/static/project/project_inc/left_bar.jsp"%>
	<!-- Site wrapper -->
	<div class="wrapper">
	
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
           <div class="row">
           
          <div class="col-md-6">
            <!-- 상품 등록 폼 시작 -->
            <div class="card card-primary">
              <div class="card-header">
                <h3 class="card-title">상품 등록 폼</h3>
              </div>
              <!-- /.card-header -->
              <!-- form start -->
              <form id = "form1">
                <div class="card-body">
                
                
                	<!-- 카테고리 영역 시작 -->
                  <div class="row">
                    <div class="col-sm-6">
                      <!-- Select multiple-->
                      <div class="form-group">
                      
                        <label>상위 카테고리</label>
                        <select class="form-control" id="topcategory">
                        </select>
                        
                      </div>
                    </div>
                    <div class="col-sm-6">
                      <div class="form-group">
                        <label>하위 카테고리</label>
                        <select class="form-control"  id="subcategory">
                        </select>
                      </div>
                    </div>
                  </div>                	
					<!-- 카테고리 영역 끝 -->     
					
					<!-- 입력폼 영역 시작 -->
					
					<div class="row">
                    <div class="col-sm-6">
                      <div class="form-group">
                      <label>상품명</label>
                     	<div class="form-group">
                    <input type="text" class="form-control" name="product_name">
                  </div> 
                      </div>
                    </div>
                    <div class="col-sm-6">
                      <div class="form-group">
                        <label>브랜드</label>
                 		 <div class="form-group">
                   		 <input type="text" class="form-control" name="brand" >
                  		</div>
                      </div>
                    </div>
                  </div>
                  
                  	<div class="row">
                    <div class="col-sm-5">
                      <div class="form-group">
                      <label>상품 가격</label>
                     	<div class="form-group">
                    <input type="text" class="form-control" name="price">
                  </div> 
                      </div>
                    </div>
                    
                    <div class="col-sm-5">
                      <div class="form-group">
                        <label>할인 가격</label>
                 		 <div class="form-group">
                   		 <input type="text" class="form-control" name="discount" >
                  		</div>
                      </div>
                    </div>
                    
                      <div class="col-sm-2">
                      <div class="form-group">
                        <label>재고</label>
                 		 <div class="form-group">
                   		 <input type="text" class="form-control" name="quantity" >
                  		</div>
                      </div>
                    </div>
                  </div>
                  
                  	<div class="row">
                    <div class="col-sm-6">
                      <div class="form-group">
                      <label>모델명</label>
                     	<div class="form-group">
                    <input type="text" class="form-control" name="model_code">
                  </div> 
                      </div>
                    </div>
                    
                    <div class="col-sm-6">
                      <div class="form-group">
                        <label>원산지</label>
                 		 <div class="form-group">
                   		 <input type="text" class="form-control" name="origin" >
                  		</div>
                      </div>
                    </div>
                  </div>
                  
                 <div class="col-sm-12">
                      <div class="form-group">
                      <label>간단소개 (100자 이내)</label>
                     	<div class="form-group">
                    <input type="text" class="form-control" name="introduce">
                  </div> 
                      </div>
                    </div>
                   <!-- 입력폼 영역 마무리 -->

                </div>


              </form>
            </div>
            <!-- 상품 등록 폼 끝 -->
            
            
      </div>
      
          <div class="col-md-6">
            <!-- 상품 등록 폼 시작 -->
            <div class="card card-success">
              <div class="card-header">
                <h3 class="card-title">상품 썸네일 등록</h3>
              </div>
              <!-- /.card-header -->
              <!-- form start -->
              <form id = "form1">
                <div class="card-body">
					      
                  <div class="form-group">
                    <div id="preview" style="width:100%; height:400px;background:lightpink;">
                    	미리보기
                    </div>                
                    <div class="input-group">
                    
                      <div class="custom-file">
                        <input type="file" class="custom-file-input" name = "photo" id="photo" multiple="multiple">
                        <label class="custom-file-label" for="exampleInputFile">상품 이미지 선택</label>
                      </div>
                      
                      <div class="input-group-append">
                        <span class="input-group-text">Upload</span>
                      </div>
                    </div>
 
                  </div>
  
                </div>
                <!-- /.card-body -->


              </form>
            </div>
            <!-- 상품 등록 폼 끝 -->
            
            
      </div>
     
      <!-- /.container-fluid -->
        </div>
                  <div class="col-sm-12">
            <!-- 상품 등록 폼 시작 -->
            <div class="card card-primary">
              <div class="card-header">
                <h3 class="card-title">상품 상세 내용 등록</h3>
              </div>
              <!-- /.card-header -->
              <!-- form start -->
              <form id = "form1">
                <div class="card-body">
                
                      <!-- 편집기 시작 -->
                    	<div class="form-group" >
						<textarea id="summernote" name="detail"></textarea>
						</div>
					      <!-- 편집기 끝-->

                </div>
                <!-- /.card-body -->

                <div class="card-footer">
                  <button type="button" class="btn btn-primary" id = "bt_regist">상품등록</button>
                  <button type="button" class="btn btn-secondary" id = "bt_list">목록보기</button>
                </div>
              </form>
            </div>
            <!-- 상품 등록 폼 끝 -->
            
            
      </div>
      
        </div>
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

		<footer class="main-footer">
			<div class="float-right d-none d-sm-block">
				<b>Version</b> 3.2.0
			</div>
			<strong>Copyright &copy; 2014-2021 <a
				href="https://adminlte.io">AdminLTE.io</a>.
			</strong> All rights reserved.
		</footer>

		<!-- Control Sidebar -->
		<aside class="control-sidebar control-sidebar-dark">
			<!-- Control sidebar content goes here -->
		</aside>
		<!-- /.control-sidebar -->
	</div>
	<!-- ./wrapper -->

<script type="text/javascript">

$(()=>{
	    $('#summernote').summernote({
			height:800,
			placeholder:"상품 상세 설명을 채우세요"
	    });
});
</script>
</body>
</html>