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
<body onload="init(${candidate.id})">
<script type="text/javascript" src="resources/js/noty.js" defer></script>
<script type="text/javascript" src="resources/js/electionCandDatatables.js" defer></script>
<jsp:include page="../fragments/bodyHeader.jsp"/>

<br>
<br>
<br>

<div class="pt-2">
    <div class="container">

        <form:form modelAttribute="candidate" action="modules/module-election-candidates/edit" method="post">
            <form:input type="hidden" id="id" path="id"/>
            <div align="right">
                <a href="/modules/module-election-candidates" class="btn btn-outline-primary button_width"
                   onclick="cancel()">
                    <i class="fas fa-arrow-left"></i> <spring:message code="common.back"/> </a>
            </div>
            <h4 id="candidate_title"></h4>
            <hr>
            <table border="0" width="100%">
                <tr>
                    <td>
                        <b>Выборы</b>
                    </td>
                    <td width="350">
                        <b><label for="name" class="col-form-label"><spring:message code="candidate.name"/></label>
                            <br>
                            <form:errors path="name" cssClass="red"/></b>
                    </td>
                    <td width="200">

                    </td>
                    <td>

                    </td>
                </tr>
                <tr>
                    <td width="250">
                        <c:choose>
                            <c:when test="${edit}">
                                <button type="button" class="btn btn-outline-dark">${candidate.election.name}</button>
                            </c:when>
                            <c:otherwise>
                                <select class="form-control" id="election" name="election">
                                    <c:forEach items="${elections}" var="election">
                                        <option value="${election.id}">${election.name}</option>
                                    </c:forEach>
                                </select>
                            </c:otherwise>
                        </c:choose>

                    </td>
                    <td>
                        <form:input path="name" cssClass="form-control input_wigth" id="name" name="name"/>
                    </td>
                    <td>

                    </td>
                    <td align="right">
<%--
                        <button type="button" class="btn btn-outline-primary button_width" onclick="save()">
                            <i class="far fa-save"></i> <spring:message code="common.save"/>
                        </button>
--%>
                    </td>
                </tr>
                <tr>
                    <td>
                        <br>
                        <div class="ui toggle checkbox">
                            <form:checkbox path="top" cssClass="form-control input_wigth" id="top" name="top"/>
                            <label><b><spring:message code="candidate.top"/></b></label>
                        </div>

                    </td>
                    <td><br><form:input path="color" type="color"/><b> Цвет кандидата</b></td>
                    <td></td>
                    <td></td>
                </tr>
            </table>

        </form:form>

        <br>
        <div id="selectTechnicals">
            <h5 id="modules_title">Выбрать технических кандидатов</h5>
            <hr>

            <table class="ui single line table table-hover very compact" id="datatable_technicals" width="50%">
                <thead class="table-secondary">
                <tr>
                    <th>№</th>
                    <th>ФИО</th>
                    <th>Вкл.</th>
                </tr>
                </thead>
            </table>

            <div align="right">
                <button type="button" class="btn btn-outline-primary button_width" onclick="saveTechnicals()">
                    <i class="far fa-save"></i> <spring:message code="common.save"/>
                </button>
            </div>
        </div>
        <br>

        <div align="right">
            <button type="button" class="btn btn-outline-primary button_width" onclick="save()">
                <i class="far fa-save"></i> <spring:message code="common.save"/>
            </button>
        </div>
    </div>
</div>
<br>
<br>

</body>
<script type="text/javascript">
    var i18n = [];
    i18n["addTitle"] = '<spring:message code="candidate.add"/>';
    i18n["editTitle"] = '<spring:message code="candidate.edit"/>';

    <c:forEach var="key" items='<%=new String[]{"common.deleted","common.saved","common.enabled","common.disabled","common.errorStatus"}%>'>
    i18n["${key}"] = "<spring:message code="${key}"/>";
    </c:forEach>
</script>
</html>
