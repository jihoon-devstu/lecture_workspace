<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>AdminLTE 3 | Projects</title>

    <!-- css,js 파일 포함 -->
    <%@ include file="/static/project/project_inc/head_link.jsp" %>

    <style type="text/css">
        /* 테이블 셀 가운데 정렬 */
        table.table td,
        table.table th {
            text-align: center;
        }
    </style>
</head>

<body class="hold-transition sidebar-mini">
    <!-- 네비게이션 바 -->
    <%@ include file="/static/project/project_inc/navbar.jsp" %>

    <!-- 좌측 사이드바 -->
    <%@ include file="/static/project/project_inc/left_bar.jsp" %>

    <!-- 사이트 전체 래퍼 -->
    <div class="wrapper">
        <!-- 메인 컨텐츠 시작 -->
        <div class="content-wrapper">
            <!-- 컨텐츠 헤더 (페이지 제목 및 브레드크럼) -->
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

            <!-- 실제 컨텐츠 영역 -->
            <section class="content">

                <!-- 상위 카테고리 카드 반복 시작 -->
                <% for (int i = 1; i < 5; i++) { %>
                    <div class="card">
                        <div class="card-header">
                            <h3 class="card-title">전자제품</h3>
                            <div class="card-tools">
                                <div class="input-group input-group-sm" style="width: 300px;">
                                    <input
                                        type="text"
                                        name="table_search"
                                        class="form-control float-right"
                                        placeholder="Search"
                                    />
                                    <div class="input-group-append">
                                        <button type="submit" class="btn btn-default">
                                            <i class="fas fa-search"></i>
                                        </button>
                                        <button
                                            type="button"
                                            class="btn btn-tool"
                                            data-card-widget="collapse"
                                            title="Collapse"
                                        >
                                            <i class="fas fa-minus"></i>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- 카드 본문 : 테이블 -->
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

                                <!-- 레코드 반복 시작 -->
                                <tbody>
                                    <% for (int a = 1; a < 6; a++) { %>
                                        <tr>
                                            <td><%= i %></td>
                                            <td>
                                                <a href="#">써큘레이터</a><br />
                                            </td>
                                            <td>가전제품</td>
                                            <td>삼성</td>
                                            <td>18,000</td>
                                            <td>19</td>
                                            <td>150</td>
                                            <td class="project-state">
                                                <span class="badge badge-success">활성화</span>
                                            </td>
                                            <td class="project-actions text-right">
                                                <a class="btn btn-primary btn-sm" href="#">
                                                    <i class="fas fa-folder"></i> 상세
                                                </a>
                                                <a class="btn btn-info btn-sm" href="#">
                                                    <i class="fas fa-pencil-alt"></i> 수정
                                                </a>
                                                <a class="btn btn-danger btn-sm" href="#">
                                                    <i class="fas fa-trash"></i> 삭제
                                                </a>
                                            </td>
                                        </tr>
                                    <% } %>
                                </tbody>
                                <!-- 레코드 반복 끝 -->

                            </table>
                        </div>
                    </div>
                <% } %>
                <!-- 상위 카테고리 카드 반복 끝 -->

            </section>
            <!-- /실제 컨텐츠 영역 끝 -->

        </div>
        <!-- /메인 컨텐츠 끝 -->

        <!-- 푸터 -->
        <footer class="main-footer">
            <div class="float-right d-none d-sm-block">
                <b>Version</b> 3.2.0
            </div>
            <strong>
                Copyright &copy; 2014-2021
                <a href="https://adminlte.io">AdminLTE.io</a>.
            </strong>
            All rights reserved.
        </footer>

        <!-- 컨트롤 사이드바 -->
        <aside class="control-sidebar control-sidebar-dark">
            <!-- 컨트롤 사이드바 내용 -->
        </aside>
        <!-- /.control-sidebar -->

    </div>
    <!-- ./wrapper -->
    <!-- 커스텀 JS 파일 포함 -->
    <!-- <script src="/static/project/project_inc/custom.js"></script> -->
</body>
</html>