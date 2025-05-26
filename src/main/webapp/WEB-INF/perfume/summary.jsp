<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Summary</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/png" href="/image/logo.png">
    <link rel="stylesheet" href="/css/summary.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
<%@ include file="../layout/navbar.jsp" %>

<div class="summary-container">
    <h2 class="summary-title">향수 추천 통계 요약</h2>
    <div id="summary-info">
        <div class="card-wrapper">
            <div class="card">
                <div class="card-header card-header-blue">취향 타입별 추천수</div>
                <div class="card-body">
                    <canvas id="typeChart"></canvas>
                </div>
            </div>
        </div>
        <div class="card-wrapper">
            <div class="card">
                <div class="card-header card-header-green">성별/연령별 향수 랭킹 Top5</div>
                <div class="card-body table-responsive">
                    <table class="table table-striped text-center">
                        <thead>
                        <tr>
                            <th>성별</th>
                            <th>연령대</th>
                            <th>향수 이름</th>
                            <th>리뷰 수</th>
                            <th>평균 평점</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${perfumeRankList}" var="rank">
                            <tr>
                                <td>${rank.gender()}</td>
                                <td>${rank.ageGroup()}</td>
                                <td>${rank.perfumeName()}</td>
                                <td>${rank.reviewCnt()}</td>
                                <td><fmt:formatNumber value="${rank.avgRate()}" pattern="0.00"/></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    // JSTL 데이터를 JS로 전달
    const typeLabels = [
        <c:forEach items="${typeList}" var="type" varStatus="status">
            '<c:out value="${type.type()}"/>'<c:if test="${!status.last}">,</c:if>
        </c:forEach>
    ];
    const typeData = [
        <c:forEach items="${typeList}" var="type" varStatus="status">
            <c:out value="${type.totalCnt()}"/><c:if test="${!status.last}">,</c:if>
        </c:forEach>
    ];
    const ctx = document.getElementById('typeChart').getContext('2d');
    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: typeLabels,
            datasets: [{
                label: '추천수',
                data: typeData,
                backgroundColor: 'rgba(54, 162, 235, 0.6)',
                borderColor: 'rgba(54, 162, 235, 1)',
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
            plugins: {
                legend: { display: false },
                title: { display: true, text: '취향 타입별 추천수' }
            },
            scales: {
                y: { beginAtZero: true }
            }
        }
    });
</script>
</body>
</html>
