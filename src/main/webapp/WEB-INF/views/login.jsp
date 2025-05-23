<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Today Perfume - 로그인</title>
    <link rel="stylesheet" href="/css/index.css">
    <style>
        .login-container {
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            background: linear-gradient(135deg, #fff6fb 0%, #ffe0e9 100%);
        }

        .login-box {
            background: white;
            padding: 40px;
            border-radius: 20px;
            box-shadow: 0 8px 24px rgba(0,0,0,0.1);
            width: 100%;
            max-width: 400px;
            text-align: center;
        }

        .login-title {
            color: #343a40;
            font-size: 28px;
            margin-bottom: 30px;
            font-weight: 600;
        }

        .login-form {
            display: flex;
            flex-direction: column;
            gap: 20px;
        }

        .form-group {
            text-align: left;
        }

        .form-group label {
            display: block;
            margin-bottom: 8px;
            color: #495057;
            font-size: 14px;
        }

        .form-group input {
            width: 100%;
            padding: 12px;
            border: 2px solid #e9ecef;
            border-radius: 8px;
            font-size: 16px;
            transition: border-color 0.3s;
        }

        .form-group input:focus {
            outline: none;
            border-color: #ff6f91;
        }

        .login-btn {
            background: #ff6f91;
            color: white;
            border: none;
            padding: 14px;
            border-radius: 8px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            transition: background 0.3s;
        }

        .login-btn:hover {
            background: #ff4d6d;
        }

        .login-options {
            margin-top: 20px;
            display: flex;
            justify-content: space-between;
            font-size: 14px;
        }

        .login-options a {
            color: #6c757d;
            text-decoration: none;
            transition: color 0.3s;
        }

        .login-options a:hover {
            color: #ff6f91;
        }

        .social-login {
            margin-top: 30px;
            padding-top: 20px;
            border-top: 1px solid #e9ecef;
        }

        .social-login-title {
            color: #6c757d;
            font-size: 14px;
            margin-bottom: 15px;
        }

        .social-buttons {
            display: flex;
            gap: 10px;
            justify-content: center;
        }

        .social-btn {
            width: 40px;
            height: 40px;
            border-radius: 50%;
            border: 1px solid #e9ecef;
            display: flex;
            align-items: center;
            justify-content: center;
            cursor: pointer;
            transition: background 0.3s;
        }

        .social-btn:hover {
            background: #f8f9fa;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <div class="login-box">
            <h1 class="login-title">Today Perfume</h1>
            <form class="login-form" action="/login" method="POST">
                <div class="form-group">
                    <label for="username">아이디</label>
                    <input type="text" id="username" name="username" placeholder="아이디를 입력하세요" required>
                </div>
                <div class="form-group">
                    <label for="password">비밀번호</label>
                    <input type="password" id="password" name="password" placeholder="비밀번호를 입력하세요" required>
                </div>
                <button type="submit" class="login-btn">로그인</button>
            </form>
            <div class="login-options">
                <a href="/signup">회원가입</a>
                <a href="/find-password">비밀번호 찾기</a>
            </div>
            <div class="social-login">
                <div class="social-login-title">소셜 로그인</div>
                <div class="social-buttons">
                    <div class="social-btn">
                        <img src="/image/kakao.png" alt="카카오 로그인" width="24" height="24">
                    </div>
                    <div class="social-btn">
                        <img src="/image/google.png" alt="구글 로그인" width="24" height="24">
                    </div>
                    <div class="social-btn">
                        <img src="/image/naver.png" alt="네이버 로그인" width="24" height="24">
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html> 