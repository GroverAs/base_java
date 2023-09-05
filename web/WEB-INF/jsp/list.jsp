<%@ page import="com.urise.webapp.model.Resume" %>
<%@ page import="com.urise.webapp.model.ContactType" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <p><a href="resume?action=add"><img src="img/add.png" width="19" height="20"></a></p>
    <table style="width:15%">
        <tr>
            <th>Name</th>
            <th>Email</th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach items="${resumes}" var="resume">
        <jsp:useBean id="resume" type="com.urise.webapp.model.Resume"/>
        <tr>
            <td><a href="resume?uuid=${resume.uuid}&action=view">${resume.fullName}</a></td>
            <td><%=ContactType.EMAIL.toHtml(resume.getContacts(ContactType.EMAIL))%></td>
            <td><a href="resume?uuid=${resume.uuid}&action=delete"><img src="img/delete.png" width="20" height="20"></a></td>
            <td><a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pen.png" width="20" height="20"></a></td>
        </tr>
        </c:forEach>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>