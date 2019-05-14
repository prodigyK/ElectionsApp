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
<body onload="init('${dateFrom}', '${dateTo}')">
<script type="text/javascript" src="resources/js/noty.js" defer></script>
<script type="text/javascript" src="resources/js/loggingDatatables.js" defer></script>
<jsp:include page="../fragments/bodyHeader.jsp"/>

<br>
<br>
<br>
<div class="pt-2">
    <div class="container">
        <h3 class="text-center">Iсторiя змiн</h3>
        <div class="ui tiny form secondary segment">
            <div class="fields">
                <div class="four wide field">
                    <label>Користувач</label>
                    <select class="form-control font-weight-bold" id="username" name="username">
                        <option value="" selected>Все</option>
                        <c:forEach items="${users}" var="user">
                            <option value="${user.id}"><b>${user.name}</b></option>
                        </c:forEach>
                    </select>
                </div>
                <div class="four wide field">
                    <label>Дата c</label>
                    <input class="form-control font-weight-bold" id="dateFrom" type="date" value=""/>
                </div>
                <div class="four wide field">
                    <label>Дата по</label>
                    <input class="form-control font-weight-bold" id="dateTo" type="date" value=""/>
                </div>
                <div class="four wide field pt-4">
                    <button class="ui right bottom button small" style="float: bottom" onclick="showResult()">
                        <i class="search icon"></i>
                        Показать
                    </button>
                </div>
            </div>


        </div>
        <br>
        <table class="ui single line table blue table-hover very compact" id="datatable">
            <thead class="table-secondary">
            <tr>
                <th></th>
                <th>Дата</th>
                <th>Логiн</th>
                <th>Користувач</th>
                <th>Картка</th>
                <th>Новий</th>
                <th>Деталi</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
<br>
<br>

<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-body" align="center">

                <table align="center" width="400" class="ui single line table table-hover table-bordered very compact"
                       id="datatable_changes">
                    <thead>
                    <tr>
                        <th>Поле</th>
                        <th>Предыдущее значение</th>
                        <th>Новое значение</th>
                    </tr>
                    </thead>
                </table>


            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close
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