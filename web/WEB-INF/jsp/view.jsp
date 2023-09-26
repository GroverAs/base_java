<%@ page import="com.urise.webapp.model.TextSection" %>
<%@ page import="com.urise.webapp.model.ContentSection" %>
<%@ page import="com.urise.webapp.model.CompanySection" %>
<%@ page import="com.urise.webapp.util.HtmlUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/view-resume-styles.css">
    <jsp:useBean id="resume" type="com.urise.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<div class="scrollable-panel">
    <div class="view-wrapper">
        <div class="full-name">${resume.fullName}&nbsp
            <a href="resume?uuid=${resume.uuid}&action=edit">
                <img src="img/pen.png" width="20" height="20">
            </a>
        </div>
        <div class="contacts">
            <c:forEach var="contactEntry" items="${resume.contacts}">
                <jsp:useBean id="contactEntry"
                             type="java.util.Map.Entry<com.urise.webapp.model.ContactType, java.lang.String>"/>
                <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br/>
            </c:forEach>
        </div>
        <div class="separate-line"></div>
        <c:forEach var="sectionEntry" items="${resume.sections}">
        <jsp:useBean id="sectionEntry"
                     type="java.util.Map.Entry<com.urise.webapp.model.SectionType, com.urise.webapp.model.Section>"/>
        <c:set var="type" value="${sectionEntry.key}"/>
        <c:set var="section" value="${sectionEntry.value}"/>
        <jsp:useBean id="section" type="com.urise.webapp.model.Section"/>
        <div class="section">
            <a id="type.name">${type.title}</a>
            <c:if test="${type=='OBJECTIVE'}">
                <div class="position"> <%=((TextSection) section).getContent()%></div>
            </c:if>
        </div>
            <c:if test="${type!='OBJECTIVE'}">
                <c:choose>
                    <c:when test="${type=='PERSONAL'}">
                        <div class="list"><%=((TextSection) section).getContent()%></div>
                    </c:when>
                    <c:when test="${type=='ACHIEVEMENT' || type=='QUALIFICATIONS'}">
                                <ul class="list">
                                    <c:forEach var="element" items="<%=((ContentSection)section).getElements()%>">
                                        <li>${element}</li>
                                    </c:forEach>
                                </ul>
                    </c:when>
                    <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
        <div class="section-wrapper">
                        <c:forEach var="company" items="<%=((CompanySection) section).getCompanies()%>">
                                    <c:choose>
                                        <c:when test="${empty company.homePage.url}">
                                            <div class="company-name">${company.homePage.name}</div>
                                        </c:when>
                                        <c:otherwise>
                                            <a class="company-name" href="${company.homePage.url}">${company.homePage.name}</a>
                                        </c:otherwise>
                                    </c:choose>
                            <div class="period-position">
                                <c:forEach var="position" items="${company.positions}">
                                    <jsp:useBean id="position" type="com.urise.webapp.model.Position"/>
                                    <div class="period"><%=HtmlUtil.formatDates(position)%></div>
                                    <div class="position">${position.title}</div>
                                    <div class="position-description">${position.description}</div>
                                </c:forEach>
                            </div>
                        </c:forEach>
                    </c:when>
                </c:choose>
            </c:if>
        </c:forEach>
        </div>
        <br>
        <div class="button-OK"><button onclick="window.history.back()">OK</button></div>
    </div>
</div>
<br>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
