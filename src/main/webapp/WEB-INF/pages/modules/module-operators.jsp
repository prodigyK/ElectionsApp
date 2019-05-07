<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="option" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
</head>
<jsp:include page="../fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/userDatatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/userDatatables.js" defer></script>
<jsp:include page="../fragments/bodyHeader.jsp"/>

<br>
<br>
<br>
<div class="pt-2">
    <div class="container">
        <h3 class="text-center">Оператори</h3>
        <button class="btn btn-outline-primary" onclick="add()">
            <span class="fa fa-plus"></span>
            Додати користувача
        </button>
        <br>
        <br>
        <table class="ui single line table blue table-hover very compact" id="datatable">
            <thead class="table-secondary">
            <tr>
                <th>ПІБ</th>
                <th>Логін</th>
                <th>Група</th>
                <th>Стан</th>
                <th>Останній вхід</th>
                <th>IP адреса</th>
                <th>Ред.</th>
                <th>Ист.</th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<div class="modal fade" tabindex="-1" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" style="background-color: lightgray;">
                <h4 class="modal-title" id="modalTitle"></h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <form id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <table width="100%">
                        <tr>
                            <td colspan="2">
                                <div class="form-group">
                                    <b><label for="name" class="col-form-label"><spring:message
                                            code="user.name"/></label></b>
                                    <input type="text" class="form-control" id="name" name="name"
                                           placeholder="<spring:message code="user.name"/>">
                                </div>

                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="form-group">
                                    <b><label for="identNumber" class="col-form-label"><spring:message
                                            code="user.ident.code"/></label></b>
                                    <input type="text" class="form-control" id="identNumber" name="identNumber"
                                           placeholder="<spring:message code="user.ident.code"/>">
                                </div>

                            </td>
                            <td>
                                <div class="form-group">
                                    <b><label for="passport" class="col-form-label"><spring:message
                                            code="user.passport"/></label></b>
                                    <input type="text" class="form-control" id="passport" name="passport"
                                           placeholder="<spring:message code="user.passport"/>">
                                </div>

                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="form-group">
                                    <b><label for="login" class="col-form-label"><spring:message
                                            code="user.login"/></label></b>
                                    <input type="text" class="form-control" id="login" name="login"
                                           placeholder="<spring:message code="user.login"/>">
                                </div>

                            </td>
                            <td>
                                <div class="form-group">
                                    <b><label for="password" class="col-form-label"><spring:message
                                            code="user.password"/></label></b>
                                    <input type="password" class="form-control" id="password" name="password"
                                           placeholder="<spring:message code="user.password"/>">
                                </div>

                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <div class="form-group">
                                    <b><label for="group"><spring:message code="user.group"/></label></b>
                                    <select class="form-control" id="group" name="group">
                                        <%--<option>Оберить групу</option>--%>
                                        <c:forEach items="${groups}" var="group">
                                            <option value="${group.id}">${group.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                            </td>
                        </tr>
                    </table>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                    <i class="fas fa-times"></i>
                    <spring:message code="common.cancel"/>
                </button>
                <button type="button" class="btn btn-primary" onclick="save()">
                    <span class="fa fa-check"></span>
                    <spring:message code="common.save"/>
                </button>
            </div>
        </div>
    </div>
</div>

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