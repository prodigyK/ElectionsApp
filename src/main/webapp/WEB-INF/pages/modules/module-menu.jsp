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
<script type="text/javascript" src="resources/js/menuDatatablesUtil.js" defer></script>
<script type="text/javascript" src="resources/js/menuDatatables.js" defer></script>
<jsp:include page="../fragments/bodyHeader.jsp"/>

<br>
<br>
<br>
<div class="pt-2" >
    <div class="container" id="group_menu">
        <h3 class="text-center">Меню категорий</h3>
        <button class="btn btn-outline-primary" onclick="add()">
            <span class="fa fa-plus"></span>
            Добавить категорию
        </button>
        <br>
        <br>
        <table class="ui single line table blue very compact" id="datatable">
            <thead class="table-secondary">
            <tr>
                <th>Название</th>
                <th>Категория</th>
                <th>Модуль</th>
                <th>Изм.</th>
                <th>Удал.</th>
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

                    <table width="100%" border="0">
                        <tr>
                            <td width="50%">
                                <div class="form-group">
                                    <b><label for="name" class="col-form-label"><spring:message code="menu.name"/></label></b>
                                    <input type="text" class="form-control" id="name" name="name">
                                </div>
                            </td>
                            <td width="50%">
                                <div class="form-group">
                                    <b><label for="description" class="col-form-label"><spring:message code="menu.description"/></label></b>
                                    <input type="text" class="form-control" id="description" name="description">
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="form-group">
                                    <b><label for="parent" class="col-form-label"><spring:message code="menu.parent"/></label></b>
                                    <select class="form-control" id="parent" name="parent">
                                        <option value=""></option>
                                        <c:forEach items="${menuParents}" var="menu">
                                            <option value="${menu.id}">${menu.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </td>
                            <td>
                                <div class="form-group">
                                    <b><label for="module" class="col-form-label"><spring:message code="menu.module"/></label></b>
                                    <select class="form-control" id="module" name="module">
                                        <option value=""></option>
                                        <c:forEach items="${modules}" var="module">
                                            <option value="${module.id}">${module.name}</option>
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

<!-- The Modal -->
<div class="modal" id="modalConfirm">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Удаление пункта меню</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- Modal body -->
            <div class="modal-body">
                Вы уверены, что хотите удалить элемент меню?
            </div>

            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                    <i class="fas fa-times"></i>
                    <spring:message code="common.cancel"/>
                </button>
                <button type="button" class="btn btn-danger" onclick="remove()">
                    <span class="fa fa-check"></span>
                    <spring:message code="common.delete"/>
                </button>
            </div>

        </div>
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
    i18n["addTitle"] = '<spring:message code="menu.add"/>';
    i18n["editTitle"] = '<spring:message code="menu.edit"/>';

    <c:forEach var="key" items='<%=new String[]{"common.deleted","common.saved","common.enabled","common.disabled","common.errorStatus"}%>'>
    i18n["${key}"] = "<spring:message code="${key}"/>";
    </c:forEach>
</script>
</html>
