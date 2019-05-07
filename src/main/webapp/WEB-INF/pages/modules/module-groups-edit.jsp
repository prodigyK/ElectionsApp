<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="option" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="../fragments/headTag.jsp"/>

<head>
    <title>Title</title>

</head>
<body onload="openDatatable(${group.id})">
<script type="text/javascript" src="resources/js/groupDatatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/groupDatatables.js" defer></script>
<jsp:include page="../fragments/bodyHeader.jsp"/>

<br>
<br>
<br>

<div class="pt-2">
    <div class="container">

        <div align="right">
            <a href="/modules/module-groups" class="btn btn-outline-primary button_width"
               onclick="cancel()">
                <i class="fas fa-arrow-left"></i> <spring:message code="common.back"/> </a>
        </div>

        <form:form modelAttribute="group" action="modules/module-groups/edit" method="post">
            <form:input type="hidden" id="id" path="id"/>
            <h4 id="group_title"></h4>
            <hr>
            <div align="right">
                <table border="0">
                    <tr>
                        <td>
                        </td>
                        <td>
                        </td>

                    </tr>

                </table>
            </div>
            <table border="0">
                <tr>
                    <td width="300">
                        <b><label for="name" class="col-form-label"><spring:message code="group.name"/></label>
                            <br>
                            <form:errors path="name" cssClass="red"/></b>
                    </td>
                    <td width="300">
                        <b><label for="description" class="col-form-label"><spring:message
                                code="group.description"/></label>
                            <br>
                            <form:errors path="description" cssClass="red"/></b>
                    </td>
                    <td>

                    </td>
                    <td>

                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="form-group">
                            <form:input path="name" cssClass="form-control input_wigth" id="name" name="name"/>
                        </div>

                    </td>
                    <td>
                        <div class="form-group">

                            <form:input path="description" cssClass="form-control input_wigth" id="description"
                                        name="description"/>
                        </div>

                    </td>
                    <td></td>
                    <td></td>
                </tr>
            </table>
        </form:form>
        <h5 id="modules_title">Модули</h5>

        <table class="ui single line table table-hover very compact" id="datatable_modules">
            <thead class="table-secondary">
            <tr>
                <th>id</th>
                <th>Категория</th>
                <th>Меню</th>
                <th>Название модуля</th>
                <th>Описание модуля</th>
                <th>Вкл.</th>
            </tr>
            </thead>
        </table>
        <br>
        <div align="right">
            <button type="button" class="btn btn-outline-primary button_width"
                    onclick="save()">
                <i class="far fa-save"></i>
                <spring:message code="common.save"/>
            </button>
        </div>

        <br>

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
    i18n["addTitle"] = '<spring:message code="group.add"/>';
    i18n["editTitle"] = '<spring:message code="group.edit"/>';

    <c:forEach var="key" items='<%=new String[]{"common.deleted","common.saved","common.enabled","common.disabled","common.errorStatus"}%>'>
    i18n["${key}"] = "<spring:message code="${key}"/>";
    </c:forEach>
</script>
</html>
