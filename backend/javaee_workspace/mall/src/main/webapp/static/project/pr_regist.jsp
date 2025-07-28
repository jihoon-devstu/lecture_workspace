<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>AdminLTE 3 | Projects</title>
<!-- Style , js include -->
<%@ include file="/static/project/project_inc/head_link.jsp"%>
<!-- /Style, js include -->
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
  <!-- Content Wrapper -->
  <div class="content-wrapper">
    <!-- Content Header -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">상품 등록</h1>
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">상품관리 > 상품등록</li>
            </ol>
          </div>
        </div>
      </div>
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <div class="row">
          <!-- 좌측: 상품 기본 정보 -->
          <div class="col-md-6">
            <div class="card card-primary">
			  <div class="card-header">
			    <h3 class="card-title"><i class="fas fa-box"></i> 상품 등록</h3>
			  </div>
			  <div class="card-body">
			    <form id="form1">
			      <!-- 카테고리 섹션 -->
			      <h5 class="mb-3"><i class="fas fa-layer-group text-primary"></i> 카테고리</h5>
			      <div class="form-row mb-4">
			        <div class="form-group col-md-6">
			          <label>상위 카테고리</label>
			          <select class="form-control" id="topcategory"></select>
			        </div>
			        <div class="form-group col-md-6">
			          <label>하위 카테고리</label>
			          <select class="form-control" id="subcategory"></select>
			        </div>
			      </div>
			
			      <!-- 기본 정보 -->
			      <h5 class="mb-3"><i class="fas fa-info-circle text-primary"></i> 기본 정보</h5>
			      <div class="form-row mb-4">
			        <div class="form-group col-md-6">
			          <label>상품명</label>
			          <input type="text" class="form-control" name="product_name" placeholder="상품명을 입력하세요">
			        </div>
			        <div class="form-group col-md-6">
			          <label>브랜드</label>
			          <input type="text" class="form-control" name="brand" placeholder="브랜드명">
			        </div>
			      </div>
			
			      <!-- 가격/할인/재고 -->
			      <h5 class="mb-3"><i class="fas fa-tags text-primary"></i> 가격 정보</h5>
			      <div class="form-row mb-4">
			        <div class="form-group col-md-4">
			          <label>상품 가격</label>
			          <input type="text" class="form-control" name="price" placeholder="원">
			        </div>
			        <div class="form-group col-md-4">
			          <label>할인 가격</label>
			          <input type="text" class="form-control" name="discount" placeholder="원">
			        </div>
			        <div class="form-group col-md-4">
			          <label>재고</label>
			          <input type="text" class="form-control" name="quantity" placeholder="수량">
			        </div>
			      </div>
			
			      <!-- 모델명/원산지 -->

				<h5 class="mb-3"><i class="fas fa-cogs text-primary"></i> 제품 세부 정보</h5>
				<div class="form-row mb-4">
				  <div class="form-group col-md-6">
				    <label><i class="fas fa-barcode text-secondary mr-1"></i> 모델명</label>
				    <input type="text" class="form-control" name="model_code" placeholder="모델명을 입력하세요">
				  </div>
				  <div class="form-group col-md-6">
				    <label><i class="fas fa-globe-asia text-secondary mr-1"></i> 원산지</label>
				    <input type="text" class="form-control" name="origin" placeholder="국가명을 입력하세요">
				  </div>
				</div>
			
			      <!-- 간단소개 -->
			      <h5 class="mb-3"><i class="fas fa-pencil-alt text-primary"></i> 간단소개</h5>
			      <div class="form-group">
			        <textarea class="form-control" name="introduce" rows="2" placeholder="100자 이내로 작성하세요"></textarea>
			      </div>
			    </form>
			  </div>
			</div>
          </div>

			<!-- 우측: 이미지 업로드 -->
			<div class="col-md-6">
			  <div class="card card-success">
			    <div class="card-header">
			      <h3 class="card-title"><i class="fas fa-image"></i> 상품 썸네일 등록</h3>
			    </div>
			    <div class="card-body text-center">
			      <!-- 큰 썸네일 영역 -->
			      <div id="main-thumbnail-preview" class="border rounded mb-3" 
			           style="min-height:550px; display:flex; justify-content:center; align-items:center;">
			        <img id="main-preview" style="max-width: 100%; max-height: 550px; display:none;" alt="선택된 이미지">
			        <p id="main-upload-placeholder" class="text-muted">이미지를 선택하세요</p>
			      </div>
			
			      <!-- 작은 썸네일 리스트 -->
			      <div id="thumbnail-list" class="d-flex flex-wrap border rounded" 
			           style="gap:10px; padding:10px; min-height:100px; justify-content:center; overflow-y:auto;">
			        <!-- JS로 미리보기 썸네일들 동적 생성 -->
			      </div>
			
			      <!-- 파일 선택 버튼 -->
			      <div class="mt-3">
			        <input type="file" id="photo" name="photo" accept="image/*" multiple class="d-none" onchange="previewMultipleImages(event)">
			        <button type="button" class="btn btn-outline-primary" onclick="document.getElementById('photo').click();">
			          <i class="fas fa-upload"></i> 파일 선택
			        </button>
			      </div>
			    </div>
			  </div>
			</div>
        </div>

        <!-- 상세 내용 -->
        <div class="card card-primary mt-3">
          <div class="card-header">
            <h3 class="card-title"><i class="fas fa-info-circle"></i> 상품 상세 내용 등록</h3>
          </div>
          <div class="card-body">
            <textarea id="summernote" name="detail"></textarea>
          </div>
          <div class="card-footer text-right">
            <button type="button" class="btn btn-primary" id="bt_regist"><i class="fas fa-save"></i> 상품등록</button>
            <button type="button" class="btn btn-secondary" id="bt_list">목록보기</button>
          </div>
        </div>
      </div>
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <footer class="main-footer">
    <div class="float-right d-none d-sm-block"><b>Version</b> 3.2.0</div>
    <strong>Copyright &copy; 2014-2021 <a href="https://adminlte.io">AdminLTE.io</a>.</strong> All rights reserved.
  </footer>

  <aside class="control-sidebar control-sidebar-dark"></aside>
</div>

<!-- Summernote & Image Preview Script -->
<script>
  $(()=>{
    $('#summernote').summernote({
      height: 600,
      placeholder: "상품 상세 설명을 입력하세요"
    });
  });
  
  function previewMultipleImages(event) {
	    let files = event.target.files;
	    let mainPreview = document.getElementById('main-preview');
	    let mainPlaceholder = document.getElementById('main-upload-placeholder');
	    let thumbnailList = document.getElementById('thumbnail-list');

	    thumbnailList.innerHTML = ''; // 작은 썸네일 초기화

	    if (files.length === 0) {
	      mainPreview.style.display = 'none';
	      mainPlaceholder.style.display = 'block';
	      return;
	    }

	    // 첫번째 이미지를 크게 보여주기
	    let firstFile = files[0];
	    let readerMain = new FileReader();
	    readerMain.onload = function(e) {
	      mainPreview.src = e.target.result;
	      mainPreview.style.display = 'block';
	      mainPlaceholder.style.display = 'none';
	    }
	    readerMain.readAsDataURL(firstFile);

	    // 작은 썸네일 생성
	    Array.from(files).forEach(file => {
	    	let reader = new FileReader();
	      reader.onload = function(e) {
	    	let img = document.createElement('img');
	        img.src = e.target.result;
	        img.style.width = '80px';
	        img.style.height = '80px';
	        img.style.objectFit = 'cover';
	        img.style.cursor = 'pointer';
	        img.classList.add('border', 'rounded');

	        // 호버 시 큰 이미지 변경
	        img.addEventListener('mouseenter', () => {
	          mainPreview.src = img.src;
	        });

	        thumbnailList.appendChild(img);
	      }
	      reader.readAsDataURL(file);
	    });
	  }
</script>
<!-- <script src="/static/project/project_inc/custom.js"></script> -->
</body>
</html>