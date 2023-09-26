<%@ page import="com.urise.webapp.model.*" %>
<%@ page import="com.urise.webapp.util.DateUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/edit-styles.css">
    <jsp:useBean id="resume" type="com.urise.webapp.model.Resume" scope="request"/>
    <title>Резюме${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<form method="post" action="resume" enctype="application/x-www-form-urlencoded">
    <input type="hidden" name="uuid" value="${resume.uuid}">
    <div class="scrollable-panel">
        <div class="edit-wrapper">
            <div class="section">Имя:</div>
            <label>
                <input type="text" name="fullName" size=29 value="${resume.fullName}" required pattern="^$|^\S+.*">
            </label>
            <div class="section">Контакты:</div>
            <c:forEach var="type" items="<%=ContactType.values()%>">
                <dl>
                    <dd>
                        <input type="text" placeholder="${type.title}" size=29 value="${resume.getContacts(type)}">
                    </dd>
                </dl>
            </c:forEach>
            </p>
            <div class="spacer"></div>
            <div class="label-section">
                <c:forEach var="type" items="<%=SectionType.values()%>">
                    <c:set var="section" value="${resume.getSection(type)}"/>
                    <jsp:useBean id="section" type="com.urise.webapp.model.Section"/>
                    <h2><a>${type.title}</a></h2>
                    <c:choose>
                        <c:when test="${type=='OBJECTIVE'}">
                            <input type='text' name='${type}' size=55 value='<%=((TextSection) section).getContent()%>'>
                        </c:when>
                        <c:when test="${type=='PERSONAL'}">
                            <textarea name='${type}' cols=55 rows=5><%=((TextSection) section).getContent()%></textarea>
                        </c:when>
                        <c:when test="${type=='QUALIFICATIONS' || type=='ACHIEVEMENT'}">
                    <textarea name='${type}' cols=55
                              rows=5><%=String.join("\n", ((ContentSection) section).getElements())%></textarea>
                        </c:when>
                        <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                            <c:forEach var="company" items="<%=((CompanySection) section).getCompanies()%>"
                                       varStatus="counter">
                                <dl>
                                    <dt>
                                    <dd><input type="text" name='${type}' size="60" value="${company.homePage.name}"
                                               placeholder="Название учереждения:"></dd>
                                    </dt>
                                </dl>
                                <dl>
                                    <dt>
                                    <dd><input type="text" name='${type}url' size="60" value="${company.homePage.url}"
                                               placeholder="Сайт учереждения:"></dd>
                                    </dt>
                                </dl>
                                <c:forEach var="pos" items="${company.positions}">
                                    <jsp:useBean id="pos" type="com.urise.webapp.model.Position"/>
                                    <div class="date-section">
                                        <input class="field date" type="text" name="${type}${counter.index}startDate"
                                               size=10
                                               value="<%=DateUtil.format(pos.getStartDate())%>"
                                               placeholder="Начало, MM/yyyy">
                                        <input class="field date date-margin" type="text"
                                               name="${type}${counter.index}endDate" size=10
                                               value="<%=DateUtil.format(pos.getEndDate())%>"
                                               placeholder="Конец, MM/yyyy">
                                    </div>
                                    <dl>
                                        <dd><input type="text" name='${type}${counter.index}title' size=60
                                                   value="${pos.title}" placeholder="Должность:">
                                    </dl>
                                    <dl>
                                        <dd><textarea name="${type}${counter.index}description" rows=5
                                                      placeholder="Описание:"
                                                      cols=60>${pos.description}</textarea></dd>
                                    </dl>
                                </c:forEach>
                            </c:forEach>
                        </c:when>
                    </c:choose>
                </c:forEach>
                <div class="spacer"></div>
                <div class="button-section">
                <button class="submit-button" type="submit">Сохранить</button>
                <button class="cancel-button" type="reset">Отменить</button>
                </div>
            </div>
        </div>
    </div>
</form>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
