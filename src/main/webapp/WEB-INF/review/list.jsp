<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <link rel="stylesheet" href="/css/review/list.css">
    <link rel="stylesheet" href="/css/navbar.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
</head>
<html>
<body>
<!-- 리뷰 섹션 -->
<div class="reviews-container">
    <div class="row">
        <!-- 리뷰 목록 -->
        <div class="col-md-8">
            <div class="reviews-section">
                <h3>리뷰 목록</h3>
                <div class="review-list">
                    <c:forEach items="${reviewList}" var="review">
                        <div class="review-item">
                            <div class="review-header">
                                <div class="review-info">
                                    <span class="review-writer">${review.writer()}</span>
                                    <span class="review-date">${review.createdAtStr()}</span>
                                </div>
                                <div class="review-rating">
                                    <c:forEach begin="1" end="5" var="i">
                                        <i class="bi bi-star${i <= review.rate() ? '-fill' : ''}"></i>
                                    </c:forEach>
                                </div>
                            </div>
                            <p class="review-content">${review.content()}</p>
                            <c:if test="${sessionScope.loginUser eq review.writer()}">
                                <form action="/review" method="post" class="review-actions">
                                    <input type="hidden" name="_method" value="DELETE">
                                    <input type="hidden" name="perfumeId" value="${perfume.id()}">
                                    <input type="hidden" name="reviewId" value="${review.id()}">
                                    <button type="submit" class="btn btn-outline-danger btn-sm">
                                        <i class="bi bi-trash"></i> 삭제
                                    </button>
                                </form>
                            </c:if>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

        <!-- 리뷰 작성 폼 -->
        <div class="col-md-4">
            <div class="review-form-section">
                <h3>리뷰 작성</h3>
                <c:choose>
                    <c:when test="${not empty existedReview}">
                        <div class="review-item">
                            <div class="review-header">
                                <div class="review-info">
                                    <span class="review-writer">${existedReview.writer()}</span>
                                    <span class="review-date">${existedReview.createdAtStr()}</span>
                                </div>
                                <div class="review-rating">
                                    <c:forEach begin="1" end="5" var="i">
                                        <i class="bi bi-star${i <= existedReview.rate() ? '-fill' : ''}"></i>
                                    </c:forEach>
                                </div>
                            </div>
                            <p class="review-content">${existedReview.content()}</p>
                            <form action="/review" method="post" class="review-actions">
                                <input type="hidden" name="_method" value="DELETE">
                                <input type="hidden" name="perfumeId" value="${perfume.id()}">
                                <input type="hidden" name="reviewId" value="${existedReview.id()}">
                                <button type="submit" class="btn btn-outline-danger btn-sm">
                                    <i class="bi bi-trash"></i> 삭제
                                </button>
                            </form>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <form action="/review" method="post" class="review-form">
                            <input type="hidden" name="perfumeId" value="${perfume.id()}">
                            <div class="mb-3">
                                <label class="form-label">별점</label>
                                <div class="rating-input">
                                    <c:forEach begin="1" end="5" var="i">
                                        <input type="radio" name="rate" value="${i}" id="star${i}" class="d-none">
                                        <label for="star${i}" class="star-label">
                                            <i class="bi bi-star"></i>
                                        </label>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="mb-3">
                                <label for="content" class="form-label">리뷰 내용</label>
                                <textarea class="form-control" id="content" name="content" rows="4" required maxlength="500"></textarea>
                                <div class="form-text text-end">
                                    <span id="contentLength">0</span>/500
                                </div>
                            </div>
                            <button type="submit" class="btn btn-primary w-100">리뷰 작성</button>
                        </form>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>
</body>
</html>


<script>
    document.addEventListener('DOMContentLoaded', function() {
        // 별점 선택 기능
        const starLabels = document.querySelectorAll('.star-label');
        const reviewForm = document.querySelector('.review-form');

        starLabels.forEach((label, index) => {
            label.addEventListener('mouseover', () => {
                for (let i = 0; i <= index; i++) {
                    starLabels[i].querySelector('i').classList.add('bi-star-fill');
                    starLabels[i].querySelector('i').classList.remove('bi-star');
                }
            });

            label.addEventListener('mouseout', () => {
                const selectedStar = document.querySelector('input[name="rate"]:checked');
                const selectedIndex = selectedStar ? parseInt(selectedStar.value) - 1 : -1;

                starLabels.forEach((label, i) => {
                    if (i <= selectedIndex) {
                        label.querySelector('i').classList.add('bi-star-fill');
                        label.querySelector('i').classList.remove('bi-star');
                    } else {
                        label.querySelector('i').classList.add('bi-star');
                        label.querySelector('i').classList.remove('bi-star-fill');
                    }
                });
            });

            label.addEventListener('click', () => {
                const radio = label.previousElementSibling;
                radio.checked = true;
            });
        });

        // 폼 제출 전 validation
        reviewForm.addEventListener('submit', function(e) {
            const rateInput = document.querySelector('input[name="rate"]:checked');
            const contentInput = document.querySelector('textarea[name="content"]');

            if (!rateInput) {
                e.preventDefault();
                alert('별점을 선택해주세요.');
                return;
            }

            if (!contentInput.value.trim()) {
                e.preventDefault();
                alert('리뷰 내용을 입력해주세요.');
                return;
            }
        });

        const contentTextarea = document.getElementById('content');
        const contentLength = document.getElementById('contentLength');
        
        contentTextarea.addEventListener('input', function() {
            const length = this.value.length;
            contentLength.textContent = length;
            
            if (length > 500) {
                this.value = this.value.substring(0, 500);
                contentLength.textContent = 500;
            }
        });
    });
</script>

