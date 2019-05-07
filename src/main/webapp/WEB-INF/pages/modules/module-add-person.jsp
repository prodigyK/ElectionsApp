<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="option" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://fondbase.loc/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="../fragments/headTag.jsp"/>
<script type="text/javascript" src="resources/js/noty.js" defer></script>
<script type="text/javascript" src="resources/js/addPersonDatatables.js" defer></script>
<body onload="init_onload(${person.id})">
<jsp:include page="../fragments/bodyHeader.jsp"/>

<br>
<br>
<br>

<div class="container ui form padded blue secondary segment tiny">
    <h4 class="text-center"><i class="user icon"></i> Додати людину</h4>
    <%--<form:form modelAttribute="person" action="modules/module-add-person/edit" method="post">--%>
        <input type="hidden" id="id" name="id"/>
        <div class="ui tiny form" style="margin-left: 20px; margin-right: 20px;">
            <h6 class="ui dividing header">Данные человека</h6>
            <div class="fields">
                <div class="six wide field">
                    <label>Прізвище</label>
                    <input class="form-control font-weight-bold" type="text" id="lastname"/>
                    <div class="dropdown_list hidden" id="lastname_results">

                    </div>
                </div>
                <div class="six wide field">
                    <label>Iм'я</label>
                    <input class="form-control font-weight-bold" type="text" id="firstname"/>
                    <div class="dropdown_list hidden" id="firstname_results">

                    </div>
                </div>
                <div class="six wide field">
                    <label>По батькові</label>
                    <input path="middlename" class="form-control font-weight-bold" type="text" id="middlename"/>
                    <div class="dropdown_list hidden" id="middlename_results">

                    </div>
                </div>
            </div>
            <div class="fields">
                <div class="six wide field">
                    <label>Дата народження</label>
                    <input path="birthday" class="form-control font-weight-bold" id="birthday" type="date" value=""/>
                </div>
                <div class="six wide field">
                    <label>Телефон</label>
                    <input path="phone" class="form-control font-weight-bold" type="text" id="phone" placeholder="xxx1234567"/>
                </div>
                <div class="six wide field">
                    <label>Пошта</label>
                    <input path="email" class="form-control font-weight-bold" type="text" id="email"
                                placeholder="somebody@example.com"/>
                </div>

            </div>

            <h6 class="ui dividing header">Паспортные данные</h6>
            <div class="fields">
                <div class="six wide field">
                    <label>ИИН</label>
                    <input class="form-control font-weight-bold" type="text" id="iin"/>
                </div>
                <div class="six wide field">
                    <label>Паспорт</label>
                    <input class="form-control font-weight-bold" type="text" id="passport"/>
                </div>
                <div class="six wide field">
                    <label>Дата видачи</label>
                    <input path="dateOfIssue" class="form-control font-weight-bold" id="dateOfIssue" type="date" value=""/>
                </div>
            </div>
            <div class="inline field">
                <div class="ui toggle checkbox">
                    <input path="storonnik" class="form-control font-weight-bold" type="checkbox" tabindex="0" id="type_storonnik"/>
                    <label><b>Сторонник</b></label>
                </div>
            </div>


            <h6 class="ui dividing header">Место прописки</h6>
            <input type="hidden" id="id_reg" name="id_reg">
            <div class="four fields">
                <div class="field">
                    <label class="label">Индекс</label>
                    <input class="form-control font-weight-bold" type="text" id="mailindex_reg"/>
                </div>
                <div class="field">
                    <label class="label">Область</label>
                    <select class="form-control font-weight-bold" id="region_reg" name="region_reg">
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
                <div class="field">
                    <label class="label">Місто</label>
                    <select class="form-control font-weight-bold" id="city_reg" name="city_reg">
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
                <div class="field">
                    <label class="label">Район</label>
                    <select class="form-control font-weight-bold" id="district_reg" name="district_reg">
                        <option value=""></option>
                        <c:forEach items="${districts}" var="district">
                            <option value="${district.id}"><b>${district.name}</b></option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="fields">
                <input type="hidden" id="id_house_reg" name="id_house_reg">
                <div class="eight wide field">
                    <label class="label">Вулиця</label>
                    <select class="form-control font-weight-bold" id="street_reg" name="street_reg">
                        <option value=""></option>
                        <c:forEach items="${streets}" var="street">
                            <option value="${street.id}"><b>${street.name}</b></option>
                        </c:forEach>
                    </select>

                </div>
                <div class="two wide field">
                    <label class="label">Дом</label>
                    <input class="form-control font-weight-bold" type="text" id="house_reg">
                </div>
                <div class="two wide field">
                    <label class="label">Корпус</label>
                    <input class="form-control font-weight-bold" type="text" id="corp_reg">
                </div>
                <div class="two wide field">
                    <label class="label">Буква</label>
                    <input class="form-control font-weight-bold" type="text" id="letter_reg">
                </div>
                <div class="two wide field">
                    <label class="label">Квартира</label>
                    <input class="form-control font-weight-bold" type="text" id="flat_reg">
                </div>

            </div>

            <h6 class="ui dividing header">Место проживания</h6>
            <input type="hidden" id="id_reg_res" name="id_reg_res">
            <div class="inline field">
                <div class="ui toggle checkbox">
                    <input class="form-control font-weight-bold" type="checkbox" tabindex="0" class="hidden"
                           id="living_for_registration">
                    <label><b>За реєстраціею</b></label>
                </div>
            </div>

            <div class="four fields" id="living_place_block">
                <div class="field">
                    <label class="label">Индекс</label>
                    <input class="form-control font-weight-bold" type="text" id="mailindex">
                </div>
                <div class="field">
                    <label class="label">Область</label>
                    <select class="form-control font-weight-bold" id="region" name="region">
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
                <div class="field">
                    <label class="label">Місто</label>
                    <select class="form-control font-weight-bold" id="city" name="city">
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
                <div class="field">
                    <label class="label">Район</label>
                    <select class="form-control font-weight-bold" id="district" name="district">
                        <option value=""></option>
                        <c:forEach items="${districts}" var="district">
                            <option value="${district.id}"><b>${district.name}</b></option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="fields" id="living_place_block_2">
                <input type="hidden" id="id_house" name="id_house">
                <div class="eight wide field">
                    <label class="label">Вулиця</label>
                    <select class="form-control font-weight-bold" id="street" name="street">
                        <option value=""></option>
                        <c:forEach items="${streets}" var="street">
                            <option value="${street.id}"><b>${street.name}</b></option>
                        </c:forEach>
                    </select>
                </div>
                <div class="two wide field">
                    <label class="label">Дом</label>
                    <input class="form-control font-weight-bold" type="text" id="house">
                </div>
                <div class="two wide field">
                    <label class="label">Корпус</label>
                    <input class="form-control font-weight-bold" type="text" id="corp">
                </div>
                <div class="two wide field">
                    <label class="label">Буква</label>
                    <input class="form-control font-weight-bold" type="text" id="letter">
                </div>
                <div class="two wide field">
                    <label class="label">Квартира</label>
                    <input class="form-control font-weight-bold" type="text" id="flat">
                </div>

            </div>

            <br>
            <button class="ui right floated button small" onclick="savePerson()">
                <i class="search icon"></i>
                Сохранить
            </button>
            <br>
            <br>


        </div>
    <%--</form:form>--%>
        <div class="ui success message hidden" id="success_block">
            <i class="close icon"></i>
            <div class="header" id="success_header">

            </div>
            <p id="success_text"></p>
        </div>
</div>

<br>
<br>


</body>
<script type="text/javascript">
    var i18n = [];
    i18n["addTitle"] = '<spring:message code="module.add"/>';
    i18n["editTitle"] = '<spring:message code="module.edit"/>';

    <c:forEach var="key"
               items='<%=new String[]{"common.deleted","common.saved","common.enabled","common.disabled","common.errorStatus"}%>'>
    i18n["${key}"] = "<spring:message code="${key}"/>";
    </c:forEach>
</script>

</html>
