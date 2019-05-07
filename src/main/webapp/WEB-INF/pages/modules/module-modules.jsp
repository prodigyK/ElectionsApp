<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="option" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="../fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/moduleDatatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/moduleDatatables.js" defer></script>
<jsp:include page="../fragments/bodyHeader.jsp"/>

<br>
<br>
<br>
<div class="pt-2">
    <div class="container">
        <h3 class="text-center">Модули</h3>
        <button class="btn btn-outline-primary" onclick="add()">
            <span class="fa fa-plus"></span>
            Додати модуль
        </button>
        <br>
        <br>
        <table class="ui single line table blue table-hover very compact" id="datatable">
            <thead class="table-secondary">
            <tr>
                <th>Категория</th>
                <th>Меню</th>
                <th>Ключ безопасности</th>
                <th>Название модуля</th>
                <th>Описание модуля</th>
                <th>Вкл.</th>
                <th>Вид.</th>
                <th>Ред.</th>
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
                            <td>
                                <div class="form-group">
                                    <b><label for="name" class="col-form-label"><spring:message
                                            code="module.name"/></label></b>
                                    <input type="text" class="form-control" id="name" name="name"
                                           placeholder="<spring:message code="module.name"/>">
                                </div>

                            </td>
                            <td>
                                <div class="form-group">
                                    <b><label for="description" class="col-form-label"><spring:message
                                            code="module.description"/></label></b>
                                    <input type="text" class="form-control" id="description" name="description"
                                           placeholder="<spring:message code="module.description"/>">
                                </div>

                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="form-group">
                                    <b><label for="reference" class="col-form-label"><spring:message
                                            code="module.reference"/></label></b>
                                    <input type="text" class="form-control" id="reference" name="reference"
                                           placeholder="<spring:message code="module.reference"/>">
                                </div>

                            </td>
                            <td>
                                <div class="form-group">
                                    <b><label for="permission" class="col-form-label"><spring:message
                                            code="module.permission"/></label></b>
                                    <input type="text" class="form-control" id="permission" name="permission"
                                           placeholder="<spring:message code="module.permission"/>">
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
    i18n["addTitle"] = '<spring:message code="module.add"/>';
    i18n["editTitle"] = '<spring:message code="module.edit"/>';

    <c:forEach var="key" items='<%=new String[]{"common.deleted","common.saved","common.enabled","common.disabled","common.errorStatus"}%>'>
    i18n["${key}"] = "<spring:message code="${key}"/>";
    </c:forEach>
</script>

</html>
