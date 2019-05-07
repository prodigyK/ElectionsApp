<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="option" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
</head>
<jsp:include page="../fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/noty.js" defer></script>
<script type="text/javascript" src="resources/js/electionCandDatatables.js" defer></script>
<jsp:include page="../fragments/bodyHeader.jsp"/>

<br>
<br>
<br>
<div class="pt-2">
    <div class="container">
        <h3 class="text-center">Кандидаты</h3>
        <a href="/modules/module-election-candidates/add" class="btn btn-outline-primary"/>
        <span class="fa fa-plus"></span>
        Добавить кандидата
        </a>
        <a href="/modules/module-election-candidates/upload" class="btn btn-outline-primary"/>
        <span class="fa fa-plus"></span>
        Загрузка из файла
        </a>
        <br>
        <br>
        <table class="ui single line table blue table-hover very compact" id="datatable">
            <thead class="table-secondary">
            <tr>
                <th>Выборы</th>
                <th>№</th>
                <th>ФИО</th>
                <th>Ред.</th>
                <th>Выбыл</th>
                <th>Наш К.</th>
                <th>Наш Т.К.</th>
                <th>Вкл.</th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<br>

</body>
<script type="text/javascript">
    var i18n = [];
    i18n["addTitle"] = '<spring:message code="election.add"/>';
    i18n["editTitle"] = '<spring:message code="election.edit"/>';

    <c:forEach var="key" items='<%=new String[]{"common.deleted","common.saved","common.enabled","common.disabled","common.errorStatus"}%>'>
    i18n["${key}"] = "<spring:message code="${key}"/>";
    </c:forEach>
</script>

</html>