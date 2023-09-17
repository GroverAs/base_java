<%@ page import="com.urise.webapp.model.Resume" %>
<%@ page import="com.urise.webapp.model.ContactType" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/resume-list-styles.css">
    <title>Список всех резюме</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<div class="scrollable-panel">
    <div class="table-wrapper">
        <div class="add-resume">
            <a href="resume?action=add"><img src="img/add.png" width="24" height="25"></a>
            <a class="add-text-decoration" href="resume?action=add">
                <p class="add-resume-title">Добавить резюме</p>
            </a>
        </div>
        <div class="resumes-list">
            <table>
                <tr class="t-header">
                    <th class="column-name">Имя</th>
                    <th class="column-info">Контакты</th>
                    <th class="column-img-delete">Удалить</th>
                    <th class="column-img-edit">Редактировать</th>
                </tr>
                <c:forEach items="${resumes}" var="resume">
                    <jsp:useBean id="resume" type="com.urise.webapp.model.Resume"/>
                    <tr class="t-body">
                        <td class="column-name"><a href="resume?uuid=${resume.uuid}&action=view">${resume.fullName}</a></td>
                        <td class="column-info"><%=ContactType.EMAIL.toHtml(resume.getContacts(ContactType.EMAIL))%></td>
                        <td class="column-img-delete"><a href="resume?uuid=${resume.uuid}&action=delete">
                            <img src="img/delete.png" width="20" height="20"></a></td>
                        <td class="column-img-edit"><a href="resume?uuid=${resume.uuid}&action=edit">
                            <img src="img/pen.png" width="20" height="20"></a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
<div class="footer">
    <a href="http://javaops.ru/reg/basejava">
        <div>Проект: разработка web-приложения «База данных резюме»</div>
    </a>
</div>
</body>
</html>