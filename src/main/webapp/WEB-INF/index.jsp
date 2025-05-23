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
        <button class="scroll-btn" id="scrollDownBtn" aria-label="아래로 이동">
            <svg viewBox="0 0 24 24"><path d="M12 16.5l-8-8 1.4-1.4L12 13.7l6.6-6.6L20 8.5z"/></svg>
        </button>
    </div>
    <div class="section" id="list">
        <div class="container">
            <h2>About Us</h2>
            <p>이곳은 향수 추천 및 리뷰를 제공하는 서비스입니다.</p>
        </div>
    </div>
    <script>
        // 아래 버튼 클릭 시 자동 스크롤
        document.getElementById('scrollDownBtn').addEventListener('click', function() {
            document.getElementById('list').scrollIntoView({ behavior: 'smooth' });
        });
        // 스크롤 시 네비바 섹션 활성화
        window.addEventListener('scroll', function() {
            const sections = document.querySelectorAll('.section');
            const navLinks = document.querySelectorAll('.nav-link');
            let current = 'home';
            sections.forEach(section => {
                const sectionTop = section.offsetTop - 80;
                if (window.scrollY >= sectionTop) {
                    current = section.getAttribute('id');
                }
            });
            navLinks.forEach(link => {
                link.classList.remove('active');
                if (link.getAttribute('href').includes(current)) {
                    link.classList.add('active');
                }
            });
        });

        const navbar = document.getElementById('navbar');
        window.addEventListener('scroll', () => {
            if (window.scrollY > 10) {
                navbar.classList.add('scrolled');
            } else {
                navbar.classList.remove('scrolled');
            }
        });
    </script>
</body>
</html>
