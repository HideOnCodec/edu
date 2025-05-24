<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Perfume Detail</title>
    <link rel="stylesheet" href="/css/navbar.css">
    <link rel="icon" type="image/png" href="/image/logo.png">
    <link rel="stylesheet" href="/css/detail.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
</head>
<body>
<%@ include file="../layout/navbar.jsp" %>
<div class="container mt-5">
    <!-- 향수 상세 정보 -->
    <div class="perfume-detail mb-5">
        <div class="row">
            <div class="col-md-6">
                <div class="perfume-header">
                    <h2 class="perfume-name">${perfume.name()}</h2>
                    <p class="perfume-brand">${perfume.brand()}</p>
                    <div class="perfume-rating">
                        <i class="bi bi-star-fill"></i>
                        <span>${perfume.avgReview()}</span>
                    </div>
                </div>
                <div class="perfume-info">
                    <div class="info-item">
                        <span class="label">가격</span>
                        <span class="value">${perfume.price()}원</span>
                    </div>
                    <div class="info-item">
                        <span class="label">어울리는 성별</span>
                        <span class="value">${perfume.gender()}</span>
                    </div>
                    <div class="info-item">
                        <span class="label">어울리는 날씨</span>
                        <span class="value">${perfume.weather()}</span>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="perfume-notes">
                    <h4>노트 구성</h4>
                    <div class="notes-container">
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

    <%--리뷰 섹션--%>
    <%@ include file="../review/list.jsp" %>

</div>


</body>
</html>
