<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="/css/login.css">
    <link rel="stylesheet" href="/css/navbar.css">
    <link rel="icon" type="image/png" href="/image/logo.png">
</head>
<body>
<!-- 메시지가 있으면 alert 띄우기 -->
<c:if test="${not empty message}">
    <script>
        alert('${message}');
    </script>
</c:if>
<%@ include file="../layout/navbar.jsp" %>
<div class="login-container">
    <div class="login-box">
        <h1 class="login-title">Today Perfume</h1>
        <form class="login-form" action="/user/login" method="POST">
            <c:if test="${not empty redirect}">
                <input type="hidden" name="redirect" value="${redirect}">
            </c:if>
            <div class="form-group">
                <label for="id">아이디</label>
                <input type="text" id="id" name="id" placeholder="아이디를 입력하세요" value="${id}" required maxlength="20">
            </div>
            <div class="form-group">
                <label for="password">비밀번호</label>
                <input type="password" id="password" name="password" placeholder="비밀번호를 입력하세요" required minlength="4" maxlength="20">
            </div>
            <c:if test="${not empty error}">
                <div class="error-message">${error}</div>
            </c:if>
            <button type="submit" class="login-btn">로그인</button>
        </form>
        <div class="login-options">
            <a href="/user/signup">회원가입</a>
        </div>
    </div>
</div>
</body>
</html>
