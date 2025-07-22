<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

   <!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>AdminLTE 3 | Projects</title>
	<!-- Style include -->
  		<%@ include file="/static/project/project_inc/head_link.jsp" %>
	<!-- /Style include -->
	<!-- head_link -->
		<%@ include file="/static/project/project_inc/footer_link.jsp" %>
	<!-- head_link -->
	<style type="text/css">
		table.table td, table.table th {
    	text-align: center;
		}
	</style>
</head>

<body class="hold-transition sidebar-mini">

<!-- Site wrapper -->
<div class="wrapper">

  <!-- Navbar -->
  	<%@ include file = "/static/project/project_inc/navbar.jsp" %>
  <!-- /Navbar -->

  <!-- Main Sidebar Container -->
	<%@ include file = "/static/project/project_inc/left_bar.jsp" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
          
            <h1>상품리스트</h1>
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Main</a></li>
              <li class="breadcrumb-item active">ProductList</li>
            </ol>
          </div>
        </div>
      </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">

      <!-- Default box -->
      <div class="card">
        <div class="card-header">
          <h3 class="card-title">Projects</h3>

          <div class="card-tools">
            <button type="button" class="btn btn-tool" data-card-widget="collapse" title="Collapse">
              <i class="fas fa-minus"></i>
            </button>
            <button type="button" class="btn btn-tool" data-card-widget="remove" title="Remove">
              <i class="fas fa-times"></i>
            </button>
          </div>
        </div>
       
        <div class="card-body p-0">
          <table class="table table-striped projects">
          
          
           <!-- 컬럼 설정 -->
              <thead>
                  <tr>
                      <th style="width: 8%" >
                          상품 번호
                      </th>
                      <th style="width: 16%">
                          상품명
                      </th>
                      <th style="width: 9%">
                          카테고리
                      </th>
                      <th style="width: 9%">
                          브랜드
                      </th>
                      <th style="width: 9%">
                          가격
                      </th>
                      <th style="width: 9%">
                          판매 건수
                      </th>                      
                      <th style="width: 9%">
                          재고
                      </th>
                      <th style="width: 8%" class="text-center">
                          상품 상태
                      </th>
                      <th style="width: 20%">
                      </th>
                  </tr>
              </thead>
              <!-- /컬럼 설정 -->
              <!-- 레코드 설정 -->
              <tbody>
                  <tr>
                      <td>
                          1
                      </td>
                      <td>
                          <a>
                              써큘레이터
                          </a>
                          <br/>
                      </td>
                      <td>
						가전제품
                      </td>
                      <td>
						삼성
                      </td>
                      <td>
						18,000
                      </td>
                      <td>
						19
                      </td>
                      <td>
						150
                      </td>                                                                  
                      <td class="project-state">
                          <span class="badge badge-success">활성화</span>
                      </td>
                      <td class="project-actions text-right">
                          <a class="btn btn-primary btn-sm" href="#">
                              <i class="fas fa-folder">
                              </i>
                              View
                          </a>
                          <a class="btn btn-info btn-sm" href="#">
                              <i class="fas fa-pencil-alt">
                              </i>
                              Edit
                          </a>
                          <a class="btn btn-danger btn-sm" href="#">
                              <i class="fas fa-trash">
                              </i>
                              Delete
                          </a>
                      </td>
                  </tr>
                  
                   <tr>
                      <td>
                          2
                      </td>
                      <td>
                          <a>
                              손선풍기
                          </a>
                          <br/>
                      </td>
                      <td>
						가전제품
                      </td>
                      <td>
						샤오미
                      </td>
                      <td>
						8,000
                      </td>
                      <td>
						25
                      </td>
                      <td>
						100
                      </td>                                                                  
                      <td class="project-state">
                          <span class="badge badge-success">활성화</span>
                      </td>
                      <td class="project-actions text-right">
                          <a class="btn btn-primary btn-sm" href="#">
                              <i class="fas fa-folder">
                              </i>
                              View
                          </a>
                          <a class="btn btn-info btn-sm" href="#">
                              <i class="fas fa-pencil-alt">
                              </i>
                              Edit
                          </a>
                          <a class="btn btn-danger btn-sm" href="#">
                              <i class="fas fa-trash">
                              </i>
                              Delete
                          </a>
                      </td>
                  </tr>
                  
                   <tr>
                      <td>
                          1
                      </td>
                      <td>
                          <a>
                              냉장고
                          </a>
                          <br/>
                      </td>
                      <td>
						가전제품
                      </td>
                      <td>
						LG
                      </td>
                      <td>
						6,500,000
                      </td>
                      <td>
						48
                      </td>
                      <td>
						58
                      </td>                                                                  
                      <td class="project-state">
                          <span class="badge badge-success">활성화</span>
                      </td>
                      <td class="project-actions text-right">
                          <a class="btn btn-primary btn-sm" href="#">
                              <i class="fas fa-folder">
                              </i>
                              View
                          </a>
                          <a class="btn btn-info btn-sm" href="#">
                              <i class="fas fa-pencil-alt">
                              </i>
                              Edit
                          </a>
                          <a class="btn btn-danger btn-sm" href="#">
                              <i class="fas fa-trash">
                              </i>
                              Delete
                          </a>
                      </td>
                  </tr>
                  
                   <tr>
                      <td>
                          1
                      </td>
                      <td>
                          <a>
                              김치냉장고
                          </a>
                          <br/>
                      </td>
                      <td>
						가전제품
                      </td>
                      <td>
						LG
                      </td>
                      <td>
						4,800,000
                      </td>
                      <td>
						29
                      </td>
                      <td>
						110
                      </td>                                                                  
                      <td class="project-state">
                          <span class="badge badge-success">활성화</span>
                      </td>
                      <td class="project-actions text-right">
                          <a class="btn btn-primary btn-sm" href="#">
                              <i class="fas fa-folder">
                              </i>
                              View
                          </a>
                          <a class="btn btn-info btn-sm" href="#">
                              <i class="fas fa-pencil-alt">
                              </i>
                              Edit
                          </a>
                          <a class="btn btn-danger btn-sm" href="#">
                              <i class="fas fa-trash">
                              </i>
                              Delete
                          </a>
                      </td>
                  </tr>
                  
              </tbody>
              
              <!-- /레코드 설정 -->


          </table>
        </div>
        <!-- /.card-body -->
      </div>
      <!-- /.card -->

    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <footer class="main-footer">
    <div class="float-right d-none d-sm-block">
      <b>Version</b> 3.2.0
    </div>
    <strong>Copyright &copy; 2014-2021 <a href="https://adminlte.io">AdminLTE.io</a>.</strong> All rights reserved.
  </footer>

  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Control sidebar content goes here -->
  </aside>
  <!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->


</body>
</html>