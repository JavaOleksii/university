<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Faculties and Subjects</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>

<body>
<div class="container">
    <!-- Sidebar -->
    <div class="w3-sidebar w3-light-grey w3-bar-block" style="width: 10%">
        <h3 class="w3-bar-item">Menu</h3>
        <a href="/home" class="w3-bar-item w3-button">Home</a>
        <security:authorize access="hasRole('ROLE_ADMIN')">
            <a href="/create-faculty" class="w3-bar-item w3-button">Create Faculty</a>
        </security:authorize>
        <security:authorize access="hasRole('ROLE_ADMIN')">
            <a href="/create-subject" class="w3-bar-item w3-button">Create Subject</a>
        </security:authorize>
        <security:authorize access="hasRole('ROLE_USER')">
            <a href="/ratings" class="w3-bar-item w3-button">Rating</a>
        </security:authorize>
    </div>

    <div style="margin-left: 10%">
        <!-- List of Faculties -->
        <div class="w3-container w3-teal">
            <h1>Faculties</h1>
        </div>

        <div class="w3-container">
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <form id="logoutForm" method="POST" action="${contextPath}/logout">
                    <input type="hidden" name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
                </form>
                <h2>
                    Welcome ${pageContext.request.userPrincipal.name} | <a
                        onclick="document.forms['logoutForm'].submit()">Logout</a>
                </h2>
            </c:if>

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