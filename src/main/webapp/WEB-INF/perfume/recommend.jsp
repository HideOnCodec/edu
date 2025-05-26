<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>향수 추천</title>
    <link rel="icon" type="image/png" href="/image/logo.png">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/recommend.css">
    <style>
        .perfume-image {
            max-width: 200px;
            max-height: 200px;
            object-fit: contain;
            margin: 0 auto 1rem;
            display: block;
        }
    </style>
</head>
<body>
    <%@ include file="../layout/navbar.jsp" %>
    <div class="container mt-5 pt-4">
        <div class="type-selection">
            <h2 class="text-center mb-4">당신의 취향을 선택해주세요(2개)</h2>
            <form action="/perfume/recommend" method="POST" id="recommendForm">
                <div class="type-grid">
                    <c:forEach items="${typeList}" var="type">
                        <div class="type-card" data-type-id="${type.id()}" data-type-name="${type.name()}">
                            <h5>${type.name()}</h5>
                        </div>
                    </c:forEach>
                </div>
                <input type="hidden" name="type1" id="type1">
                <input type="hidden" name="type2" id="type2">
                <input type="hidden" name="type1Name" id="type1Name">
                <input type="hidden" name="type2Name" id="type2Name">
                <div class="text-center">
                    <button type="submit" class="submit-btn" disabled>향수 추천받기</button>
                </div>
            </form>
        </div>
    </div>

    <!-- 결과 모달 -->
    <div class="modal fade result-modal" id="resultModal" tabindex="-1">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">추천 향수</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">
                    <c:if test="${not empty perfume}">
                        <c:if test="${not empty perfume.image()}">
                            <img src="${perfume.image()}" alt="${perfume.name()}" class="perfume-image">
                        </c:if>
                        <h4 class="perfume-name">${perfume.name()}</h4>
                        <p class="perfume-brand">${perfume.brand()}</p>
                        <div class="perfume-info">
                            <div class="perfume-price">
                                <span class="price">${perfume.price()}원</span>
                                <span class="rating">★ <fmt:formatNumber value="${perfume.avgReview()}" pattern="0.00"/></span>
                            </div>
                        </div>
                        <div class="text-center mt-4">
                            <a href="/perfume/${perfume.id()}" class="btn btn-primary">상세 보기</a>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        // 타입 선택 처리
        const typeCards = document.querySelectorAll('.type-card');
        const submitBtn = document.querySelector('.submit-btn');
        const type1Input = document.getElementById('type1');
        const type2Input = document.getElementById('type2');
        const type1NameInput = document.getElementById('type1Name');
        const type2NameInput = document.getElementById('type2Name');
        let selectedTypes = [];
        let selectedTypeNames = [];

        typeCards.forEach(card => {
            card.addEventListener('click', () => {
                const typeId = card.dataset.typeId;
                const typeName = card.dataset.typeName;
                
                if (card.classList.contains('selected')) {
                    card.classList.remove('selected');
                    const index = selectedTypes.indexOf(typeId);
                    selectedTypes.splice(index, 1);
                    selectedTypeNames.splice(index, 1);
                } else if (selectedTypes.length < 2) {
                    card.classList.add('selected');
                    selectedTypes.push(typeId);
                    selectedTypeNames.push(typeName);
                }

                if (selectedTypes.length === 2) {
                    type1Input.value = selectedTypes[0];
                    type2Input.value = selectedTypes[1];
                    type1NameInput.value = selectedTypeNames[0];
                    type2NameInput.value = selectedTypeNames[1];
                    submitBtn.disabled = false;
                } else {
                    submitBtn.disabled = true;
                }

                console.log('type1', type1Input.value, 'name:', type1NameInput.value);
                console.log('type2', type2Input.value, 'name:', type2NameInput.value);
            });
        });

        // 에러 메시지 처리
        <c:if test="${not empty error}">
            alert('${error}');
        </c:if>

        // 결과 모달 표시
        <c:if test="${not empty perfume}">
            const resultModal = new bootstrap.Modal(document.getElementById('resultModal'));
            resultModal.show();
        </c:if>
    </script>
</body>
</html>
