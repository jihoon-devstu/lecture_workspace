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
		<!-- 컨텐츠 시작 -->
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
				</div>
				<!-- /.container-fluid -->
			</section>

			<section class="content">

				<!-- 상위 카테고리 카드 시작 -->
				<%for(int i=1;i<5;i++) {%>
				<div class="card">
					<div class="card-header">
						<h3 class="card-title">전자제품</h3>
						<div class="card-tools">
							<div class="input-group input-group-sm" style="width: 300px;">
								<input type="text" name="table_search"
									class="form-control float-right" placeholder="Search">
								<div class="input-group-append">
									<button type="submit" class="btn btn-default">
										<i class="fas fa-search"></i>
									</button>
									<button type="button" class="btn btn-tool"
										data-card-widget="collapse" title="Collapse">
										<i class="fas fa-minus"></i>
									</button>
								</div>
							</div>
						</div>
					</div>

					<div class="card-body p-0">
						<table class="table table-striped projects">
							<thead>
								<tr>
									<th style="width: 8%">상품 번호</th>
									<th style="width: 16%">상품명</th>
									<th style="width: 9%">카테고리</th>
									<th style="width: 9%">브랜드</th>
									<th style="width: 9%">가격</th>
									<th style="width: 9%">판매 건수</th>
									<th style="width: 9%">재고</th>
									<th style="width: 8%" class="text-center">상품 상태</th>
									<th style="width: 20%"></th>
								</tr>
							</thead>
							<!-- /컬럼 설정 -->
							
							<!-- 레코드 설정 -->
							<tbody>
							<%for(int a=1;a<6;a++){ %>
								<tr>
									<td><%=i %></td>
									<td><a href="#"> 써큘레이터 </a> <br /></td>
									<td>가전제품</td>
									<td>삼성</td>
									<td>18,000</td>
									<td>19</td>
									<td>150</td>
									<td class="project-state"><span
										class="badge badge-success">활성화</span></td>
									<td class="project-actions text-right"><a
										class="btn btn-primary btn-sm" href="#"> <i
											class="fas fa-folder"> </i> 상세
									</a> <a class="btn btn-info btn-sm" href="#"> <i
											class="fas fa-pencil-alt"> </i> 수정
									</a> <a class="btn btn-danger btn-sm" href="#"> <i
											class="fas fa-trash"> </i> 삭제
									</a></td>
								</tr>
								<%} %>
							<!-- /레코드 설정 -->


						</table>
					</div>
				</div>
				<%} %>
				<!-- /상위 카테고리 카드 마무리 -->

			</section>


		</div>

		<!-- /컨텐츠 마무리 -->

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


</body>
</html>