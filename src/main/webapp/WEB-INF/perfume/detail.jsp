<%@ page import="com.edu.todayperfume.user.service.UserService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Perfume Detail</title>
    <link rel="stylesheet" href="/css/navbar.css">
    <link rel="icon" type="image/png" href="/image/logo.png">
    <link rel="stylesheet" href="/css/detail.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
    <style>

    </style>
</head>
<body>
<%@ include file="../layout/navbar.jsp" %>
<div class="container mt-5">
    <!-- 향수 상세 정보 -->
    <div class="perfume-detail mb-5">
        <div class="row">
            <!-- 왼쪽: 이미지 섹션 -->
            <div class="col-md-6">
                <div class="perfume-header">
                    <c:if test="${isAdmin == true}">
                        <div class="admin-buttons">
                            <div class="btn-group">
                                <a href="/perfume/update/${perfume.id()}" class="btn btn-outline-primary">
                                    <i class="bi bi-pencil"></i> 수정
                                </a>
                                <form action="/perfume/${perfume.id()}" method="POST" style="display: inline; margin: 0;">
                                    <input type="hidden" name="_method" value="DELETE">
                                    <button type="submit" class="btn btn-outline-danger" onclick="return confirm('정말 삭제하시겠습니까?');">
                                        <i class="bi bi-trash"></i> 삭제
                                    </button>
                                </form>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${not empty perfume.image()}">
                        <img src="${perfume.image()}" alt="${perfume.name()}" class="perfume-image">
                    </c:if>
                </div>
            </div>

            <!-- 오른쪽: 정보 섹션 -->
            <div class="col-md-6">
                <div class="perfume-title">
                    <h2>${perfume.name()}</h2>
                    <p class="text-muted">${perfume.brand()}</p>
                </div>

                <div class="row">
                    <!-- 기본 정보 -->
                    <div class="col-md-6">
                        <div class="info-section">
                            <h4 class="mb-4">기본 정보</h4>
                            <div class="info-item">
                                <div class="info-label">가격</div>
                                <div class="info-value">${perfume.priceStr()}원</div>
                            </div>
                            <div class="info-item">
                                <div class="info-label">어울리는 성별</div>
                                <div class="info-value">${perfume.gender()}</div>
                            </div>
                            <div class="info-item">
                                <div class="info-label">어울리는 날씨</div>
                                <div class="info-value">${perfume.weather()}</div>
                            </div>
                            <div class="info-item">
                                <div class="info-label">평점</div>
                                <div class="info-value">
                                    <i class="bi bi-star-fill"></i> <fmt:formatNumber value="${perfume.avgReview()}" pattern="0.00"/>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- 노트 정보 -->
                    <div class="col-md-6">
                        <div class="notes-section">
                            <h4 class="mb-4">노트 구성</h4>
                            <div class="note-item">
                                <div class="note-label">탑 노트</div>
                                <div class="note-value">${perfume.topNote()}</div>
                            </div>
                            <div class="note-item">
                                <div class="note-label">미들 노트</div>
                                <div class="note-value">${perfume.middleNote()}</div>
                            </div>
                            <div class="note-item">
                                <div class="note-label">베이스 노트</div>
                                <div class="note-value">${perfume.baseNote()}</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%--리뷰 섹션--%>
    <%@ include file="../review/list.jsp" %>
</div>

<!-- 삭제 확인 모달 -->
<div class="modal fade" id="deletePerfumeModal" tabindex="-1" aria-labelledby="deletePerfumeModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="deletePerfumeModalLabel">향수 삭제</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                정말로 이 향수를 삭제하시겠습니까?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                <form action="/perfume/${perfume.id()}" method="POST" style="display: inline;">
                    <input type="hidden" name="_method" value="DELETE">
                    <button type="submit" class="btn btn-danger">삭제</button>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
