<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SignUp</title>
    <link rel="stylesheet" href="/css/signup.css">
    <link rel="stylesheet" href="/css/navbar.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="icon" type="image/png" href="/image/logo.png">
</head>
<body>
<%@ include file="../layout/navbar.jsp" %>
<div class="signup-container">
    <div class="signup-box">
        <h1 class="signup-title">Today Perfume</h1>
        <form class="signup-form" action="/user/signup" method="POST">
            <div class="form-group">
                <label for="id">아이디</label>
                <input type="text" id="id" name="id" placeholder="아이디를 입력하세요" value="${info.id()}" required maxlength="20">
            </div>
            <div class="form-group">
                <label for="password">비밀번호</label>
                <input type="password" id="password" name="password" placeholder="비밀번호를 입력하세요" required minlength="4" maxlength="20">
            </div>
            <div class="form-group">
                <label for="gender">성별</label>
                <select id="gender" name="gender" required>
                    <option value="">성별을 선택하세요</option>
                    <option value="WOMAN" ${info.gender() == 'WOMAN' ? 'selected' : ''}>여성</option>
                    <option value="MAN" ${info.gender() == 'MAN' ? 'selected' : ''}>남성</option>
                </select>
            </div>
            <div class="form-group">
                <label for="age">나이</label>
                <input
                        type="number"
                        id="age"
                        name="age"
                        placeholder="나이를 입력하세요"
                        min="0"
                        max="120"
                        value="${info.age()}"
                        required
                >
            </div>
            <c:if test="${not empty error}">
                <div class="error-message">${error}</div>
            </c:if>
            <button type="submit" class="signup-btn">회원가입</button>
        </form>
        <div class="signup-options">
            이미 계정이 있으신가요 ? <a href="/user/login">로그인</a>
        </div>
    </div>
</div>
</body>
</html>
