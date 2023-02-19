<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Faculties and Subjects</title>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
</head>

<body>
<div class="container-fluid">
    <!-- Sidebar -->
    <div class="w3-sidebar" style="width: 10%">
        <div class="list-group" style="margin-top: 40px">
            <div class="list-group-item active">
                <div>
                    <h3>Faculties</h3>
                </div>
                <div>${pageContext.request.userPrincipal.name}</div>
            </div>
            <a href="/home" class="list-group-item"> <i class="fa fa-comment-o"></i>Home</a>
            <security:authorize access="hasRole('ROLE_ADMIN')">
                <a href="/create-faculty" class="list-group-item"><i class="fa fa-search"></i> Create Faculty</a>
            </security:authorize>
            <h1>Faculties</h1>
        </div>

        <div class="w3-container">
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <form id="logoutForm" method="POST" action="${contextPath}/logout">
                    <input type="hidden" name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
                </form>
                <a class="list-group-item"
                   onclick="document.forms['logoutForm'].submit()"
                   style="cursor: pointer"><i class="fa fa-search"></i>Logout</a>
            </c:if>
        </div>
    </div>

    <!-- Page Content -->
    <div style="margin-left: 10%">
        <div class="w3-container">
            <c:if test="${not empty faculties}">
                <c:forEach items="${faculties}" var="currentFaculty">
                    <div class="w3-card-4" style="width: 20%; margin: 2%">
                        <img src="data:image/jpg;base64, ${currentFaculty.encodedImage}" alt="Image"
                             style="width: 100%">
                        <div class="w3-container w3-center">
                            <h3>${currentFaculty.name}</h3>
                            <p>${currentFaculty.numberOfSeats}</p>
                        </div>
                        <button class="w3-button w3-block w3-dark-grey">Add to List</button>
                        <security:authorize access="hasRole('ROLE_USER')">
                            <form:form action="${contextPath}/rating" method="POST" enctype="multipart/form-data">
                                <input type="hidden" value="${currentFaculty.id}"
                                       class="form-control" name="facultyId">
                                <input type="submit" class="w3-button w3-block w3-dark-grey"
                                       value="Add to Rating">
                            </form:form>
                        </security:authorize>
                    </div>
                </c:forEach>
            </c:if>
        </div>

        <!-- List of Subjects -->
        <div class="w3-container w3-teal">
            <h1>Subjects</h1>
        </div>

        <div class="w3-container">
            <c:if test="${not empty subjects}">
                <c:forEach items="${subjects}" var="currentSubject">
                    <div class="w3-card-4" style="width: 20%; margin:2%">
                        <img src="https://kaverisias.com/wp-content/uploads/2018/01/catalog-default-img.gif"
                             alt="Norway" style="width: 100%">
                        <div class="w3-container w3-center">
                            <h3>${currentSubject.name}</h3>
                        </div>
                        <button class="w3-button w3-block w3-dark-grey">Add to List</button>
                        <form:form action="${contextPath}/rating" method="POST" enctype="multipart/form-data">
                            <input type="hidden" value="${currentFaculty.id}"
                                   class="form-control" name="facultyId">
                            <input type="submit" class="w3-button w3-block w3-dark-grey"
                                   value="Add to Rating">
                        </form:form>
                    </div>
                </c:forEach>
            </c:if>
        </div>
    </div>
</div>

<%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>--%>
<%--<script src="${contextPath}/resources/js/bootstrap.min.js"></script>--%>
</body>
</html>