<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Perfume List</title>
    <link rel="icon" type="image/png" href="/image/logo.png">
    <link href="/css/list.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="../layout/navbar.jsp" %>
<div class="container mt-5">
    <div class="d-flex flex-column gap-3 mb-4">
        <!-- 정렬 버튼 -->
        <div class="d-flex justify-content-center">
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
                <div class="perfume-card">
                    <div class="card-body">
                        <h5 class="perfume-title">${perfume.name()}</h5>
                        <p class="perfume-brand">${perfume.brand()}</p>
                        <div class="perfume-notes">
                            <div class="note-item">
                                <span class="note-label">탑 노트</span>
                                <span class="note-value">${perfume.topNote()}</span>
                            </div>
                            <div class="note-item">
                                <span class="note-label">미들 노트</span>
                                <span class="note-value">${perfume.middleNote()}</span>
                            </div>
                            <div class="note-item">
                                <span class="note-label">베이스 노트</span>
                                <span class="note-value">${perfume.baseNote()}</span>
                            </div>
                        </div>
                        <div class="perfume-info mt-3">
                            <div class="perfume-price">
                                <span class="price">${perfume.price()}원</span>
                                <span class="rating">★ ${perfume.avgReview()}</span>
                            </div>
                        </div>
                    </div>
                    <div class="card-footer bg-transparent border-top-0">
                        <button class="detail-btn w-100"
                                onclick="location.href='/perfume/${perfume.id()}'">
                            상세보기
                        </button>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

