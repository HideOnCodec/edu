<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Today perfume</title>
    <link rel="icon" type="image/png" href="/image/logo.png">
    <link rel="stylesheet" href="/css/index.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <%@ include file="layout/navbar.jsp" %>
    <div class="banner-section" id="home">
        <div class="banner-title">WELCOME TO TODAY PERFUME</div>
        <div class="banner-desc">오늘의 향수에 오신 것을 환영합니다.<br>당신만의 향기를 찾아보세요!</div>
        <button class="scroll-btn" onclick="window.location.href='/perfume/recommend'">
            <span class="scroll-text">향수 추천받기</span>
            <svg viewBox="0 0 24 24"><path d="M12 16.5l-8-8 1.4-1.4L12 13.7l6.6-6.6L20 8.5z"/></svg>
        </button>
    </div>
</body>
</html>
