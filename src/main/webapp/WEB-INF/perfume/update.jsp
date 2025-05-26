<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Perfume Update</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/png" href="/image/logo.png">
    <link rel="stylesheet" href="/css/index.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<%@ include file="../layout/navbar.jsp" %>

<div class="container" style="margin-top: 100px;">
    <div class="row justify-content-center">
        <div class="col-md-10">
            <div class="card" style="width: 100%">
                <div class="card-header">
                    <h3 class="text-center">향수 수정</h3>
                </div>
                <div class="card-body">
                    <form action="/perfume/${perfume.id()}" method="POST" enctype="multipart/form-data">
                        <input type="hidden" name="_method" value="PATCH">
                        <div class="row">
                            <!-- 왼쪽 컬럼 -->
                            <div class="col-md-6">
                                <div class="mb-3">
                                    <label for="image" class="form-label">향수 이미지</label>
                                    <input type="file" class="form-control" id="image" name="image" accept="image/*">
                                    <c:if test="${not empty perfume.image()}">
                                        <div class="mt-2">
                                            <img src="${perfume.image()}" alt="현재 이미지" style="max-width: 200px;">
                                        </div>
                                    </c:if>
                                </div>
                                <div class="mb-3">
                                    <label for="name" class="form-label">향수 이름</label>
                                    <input type="text" class="form-control" id="name" name="name" value="${perfume.name()}" required>
                                </div>
                                <div class="mb-3">
                                    <label for="brand" class="form-label">브랜드</label>
                                    <input type="text" class="form-control" id="brand" name="brand" value="${perfume.brand()}" required>
                                </div>
                                <div class="mb-3">
                                    <label for="price" class="form-label">가격</label>
                                    <input type="number" class="form-control" id="price" name="price" value="${perfume.price()}" required>
                                </div>
                                <div class="mb-3">
                                    <label for="gender" class="form-label">성별</label>
                                    <select class="form-select" id="gender" name="gender" required>
                                        <option value="">선택하세요</option>
                                        <option value="WOMAN" ${perfume.gender().equals("WOMAN") ? 'selected' : ''}>여성</option>
                                        <option value="MAN" ${perfume.gender().equals("MAN") ? 'selected' : ''}>남성</option>
                                    </select>
                                </div>
                            </div>
                            <!-- 오른쪽 컬럼 -->
                            <div class="col-md-6">
                                <div class="mb-3">
                                    <label for="weather" class="form-label">어울리는 날씨</label>
                                    <select class="form-select" id="weather" name="weather" required>
                                        <option value="">선택하세요</option>
                                        <option value="SUNNY" ${perfume.weather().equals("SUNNY") ? 'selected' : ''}>맑음</option>
                                        <option value="CLOUD" ${perfume.weather().equals("CLOUD") ? 'selected' : ''}>흐림</option>
                                        <option value="RAINY" ${perfume.weather().equals("RAINY") ? 'selected' : ''}>비</option>
                                        <option value="SNOW" ${perfume.weather().equals("SNOW") ? 'selected' : ''}>눈</option>
                                        <option value="HOT" ${perfume.weather().equals("HOT") ? 'selected' : ''}>더움</option>
                                        <option value="COLD" ${perfume.weather().equals("COLD") ? 'selected' : ''}>추움</option>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label for="topNote" class="form-label">탑 노트</label>
                                    <select class="form-select" id="topNote" name="topNote" required>
                                        <option value="">선택하세요</option>
                                        <c:forEach items="${noteList}" var="note">
                                            <option value="${note.id()}" ${perfume.topNote() == note.name() ? 'selected' : ''}>${note.name()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label for="middleNote" class="form-label">미들 노트</label>
                                    <select class="form-select" id="middleNote" name="middleNote" required>
                                        <option value="">선택하세요</option>
                                        <c:forEach items="${noteList}" var="note">
                                            <option value="${note.id()}" ${perfume.middleNote() == note.name() ? 'selected' : ''}>${note.name()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label for="baseNote" class="form-label">베이스 노트</label>
                                    <select class="form-select" id="baseNote" name="baseNote" required>
                                        <option value="">선택하세요</option>
                                        <c:forEach items="${noteList}" var="note">
                                            <option value="${note.id()}" ${perfume.baseNote() == note.name() ? 'selected' : ''}>${note.name()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="d-grid gap-2 col-6 mx-auto mt-4">
                            <button type="submit" class="btn btn-primary">수정</button>
                            <a href="/perfume/${perfume.id()}" class="btn btn-secondary">취소</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
