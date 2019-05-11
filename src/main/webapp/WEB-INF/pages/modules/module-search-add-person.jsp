<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="option" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="../fragments/headTag.jsp"/>
<body onload="init()">
<script type="text/javascript" src="resources/js/searchDatatables.js" defer></script>
<jsp:include page="../fragments/bodyHeader.jsp"/>

<br>
<br>
<br>
<div class="container">
    <h4 class="text-center"><i class="fas fa-search"></i> Пошук / Додання людей</h4>
    <div class="ui tiny form padded blue secondary segment margin_form" style="margin-left: 60px; margin-right: 60px;">
        <%--<h3 class="ui dividing header"></h3>--%>
        <div class="fields">
            <div class="four wide field">
                <label>ИИН</label>
                <input class="form-control" type="text" id="iin" placeholder="12345678"/>
            </div>
            <div class="four wide field">
                <label>Паспорт</label>
                <input class="form-control" type="text" id="passport" placeholder="KK012345"/>
            </div>
        </div>
        <div class="fields">
            <div class="six wide field">
                <label>Прізвище</label>
                <input class="form-control" type="text" id="lastname">
                <div class="dropdown_list hidden" id="lastname_results">

                </div>
            </div>
            <div class="six wide field">
                <label>Iм'я</label>
                <input class="form-control" type="text" id="firstname">
                <div class="dropdown_list hidden" id="firstname_results">

                </div>
            </div>
            <div class="six wide field">
                <label>По батькові</label>
                <input class="form-control" type="text" id="middlename">
                <div class="dropdown_list hidden" id="middlename_results">

                </div>
            </div>
        </div>

        <div class="fields">
            <div class="four wide field">
                <label>Область</label>
                <select class="form-control" id="region" name="region">
                    <option value=""></option>
                    <c:forEach items="${regions}" var="region">
                        <c:choose>
                            <c:when test="${region.id == '1001'}">
                                <option value="${region.id}" selected><b>${region.name}</b></option>
                            </c:when>
                            <c:otherwise>
                                <option value="${region.id}"><b>${region.name}</b></option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>
            <div class="four wide field">
                <label>Місто</label>
                <select class="form-control" id="city" name="city">
                    <option value=""></option>
                    <c:forEach items="${cities}" var="city">
                        <c:choose>
                            <c:when test="${city.id == '1001'}">
                                <option value="${city.id}" selected><b>${city.name}</b></option>
                            </c:when>
                            <c:otherwise>
                                <option value="${city.id}"><b>${city.name}</b></option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>
            <div class="four wide field">
                <label>Район</label>
                <select class="form-control" id="district" name="district">
                    <option value=""></option>
                    <c:forEach items="${districts}" var="district">
                        <option value="${district.id}"><b>${district.name}</b></option>
                    </c:forEach>
                </select>
            </div>
            <div class="five wide field">
                <label>Вулиця</label>
                <input type="text" id="street">
                <div class="dropdown_list hidden" id="street_results">

                </div>

            </div>
            <%--
                        <div class="ui right floated button small" tabindex="0" onclick="getPeople()"><i class="fas fa-search"></i><br>
                            Поиск
                        </div>
            --%>
        </div>
        <button class="ui button small" onclick="openForm()" id="search">
            <i class="user icon"></i>
            Додати людину
        </button>
        <button class="ui right floated active button small" onclick="getPeople()">
            <i class="search icon"></i>
            Пошук
        </button>

    </div>
</div>

<div class="container hidden" id="datatable_div">
    <%--<hr>--%>
    <div style="margin-left: 60px; margin-right: 60px;">
        <table class="ui single line table blue table-hover very compact small" id="datatable">
            <thead class="table-secondary">
            <tr>
                <th>id</th>
                <th>Призвище</th>
                <th>Iм'я</th>
                <th>По батькові</th>
                <th>Район</th>
                <th>Вулиця</th>
                <th>Дом</th>
                <th>Квартира</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
<br>
<br>


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
