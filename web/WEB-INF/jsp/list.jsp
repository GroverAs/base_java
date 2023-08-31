<%@ page import="com.urise.webapp.model.Resume" %>
<%@ page import="com.urise.webapp.model.ContactType" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Список всех резюме</title>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }

        th, td {
            background-color: #96D4D4;
        }
    </style>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <table style="width:100%">
        <tr>
            <th>Name</th>
            <th>Email</th>
        </tr>
        <c:forEach items="${resumes}" var="resume">
        <jsp:useBean id="resume" type="com.urise.webapp.model.Resume "/>
        <tr>
            <td><a href="resume?uuid=${resume.uuid}">${resume.fullName}</a></td>
            <td>${resume.getContacts(ContactType.EMAIL)}</td>
        </tr>
        </c:forEach>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>