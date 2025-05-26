<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Perfume List</title>
    <link rel="icon" type="image/png" href="/image/logo.png">
    <link href="/css/list.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
    <style>

    </style>
</head>
<body>
<%@ include file="../layout/navbar.jsp" %>
<div class="container mt-5">
    <div class="d-flex flex-column gap-3 mb-4">
        <!-- 정렬 버튼 -->
        <div class="d-flex justify-content-center position-relative">
            <div class="btn-group" role="group" id="sort-btn">
                <a href="?sort=last<c:if test="${not empty noteId}">&noteId=${noteId}</c:if>"
                   class="btn btn-outline-primary ${sort == 'last' or empty sort ? 'active' : ''}">
                    최신순
                </a>
                <a href="?sort=rate<c:if test="${not empty noteId}">&noteId=${noteId}</c:if>"
                   class="btn btn-outline-primary ${sort == 'rate' ? 'active' : ''}">
                    별점순
                </a>
            </div>
            <c:if test="${isAdmin == true}">
                <a href="/perfume/create" class="btn btn-primary position-absolute end-0">
                    <i class="bi bi-plus-lg"></i> 향수 등록
                </a>
            </c:if>
        </div>

        <!-- 노트 선택 -->
        <div class="note-selection">
            <div class="btn-group" role="group">
                <a href="?sort=${sort}<c:if test="${empty sort}">last</c:if>"
                   class="btn btn-outline-secondary ${empty param.noteId or param.noteId == '0' ? 'active' : ''}">
                    전체
                </a>
                <c:forEach items="${noteList}" var="note">
                    <a href="?sort=${sort}<c:if test="${empty sort}">last</c:if>&noteId=${note.id()}"
                       class="btn btn-outline-secondary ${noteId == note.id() ? 'active' : ''}">
                        ${note.name()}
                    </a>
                </c:forEach>
            </div>
        </div>
    </div>
    <div class="row row-cols-1 row-cols-md-3 g-4">
        <c:forEach items="${perfumeList}" var="perfume">
            <div class="col">
                <div class="card perfume-card h-100" style="width: 90%">
                    <c:if test="${not empty perfume.image()}">
                        <img src="${perfume.image()}" class="card-img-top perfume-image" alt="${perfume.name()}">
                    </c:if>
                    <div class="card-body">
                        <h5 class="card-title">${perfume.name()}</h5>
                        <p class="card-text">${perfume.brand()}</p>
                        <p class="card-text">
                            <small class="text-muted">
                                <i class="bi bi-star-fill"></i> <fmt:formatNumber value="${perfume.avgReview()}" pattern="0.00"/>
                            </small>
                        </p>
                        <a href="/perfume/${perfume.id()}" class="btn btn-outline-primary">상세보기</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

