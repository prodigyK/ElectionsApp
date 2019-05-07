<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="option" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="../fragments/headTag.jsp"/>

<head>
    <title>Title</title>

</head>
<body>
<script type="text/javascript" src="resources/js/groupDatatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/groupDatatables.js" defer></script>
<jsp:include page="../fragments/bodyHeader.jsp"/>

<br>
<br>
<br>
<div class="pt-2" >
    <div class="container" id="group_menu">
        <h3 class="text-center">Группы операторов</h3>
        <a href="/modules/module-groups/add" class="btn btn-outline-primary">
            <span class="fa fa-plus"></span>
            Добавить группу
        </a>
        <br>
        <br>
        <table class="ui single line table blue very compact" id="datatable">
            <thead class="table-secondary">
            <tr>
                <th>Название</th>
                <th>Описание</th>
                <th>Вкл.</th>
                <th>Изменить</th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<br>
<br>
<br>
<br>
<br>


</body>
<script type="text/javascript">
    var i18n = [];
    i18n["addTitle"] = '<spring:message code="user.add"/>';
    i18n["editTitle"] = '<spring:message code="user.edit"/>';

    <c:forEach var="key" items='<%=new String[]{"common.deleted","common.saved","common.enabled","common.disabled","common.errorStatus"}%>'>
    i18n["${key}"] = "<spring:message code="${key}"/>";
    </c:forEach>
</script>
</html>
