<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>FondBase</title>
    <jsp:include page="resources.jsp"/>
    <script type="text/javascript">
        function getDistrict(id, address) {
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    document.getElementById(id).innerHTML = this.responseText;
                }
            };
            xhttp.open("GET", '/getDistrict/' + address, true);
            xhttp.send();
        }

        // $(document).ready(function(){
        //     $('.ui.checkbox').click(function () {
        //         $(this).checkbox('set determinate toggle');
        //     });
        // });

        function setCheckbox(check) {
            if (document.getElementById(check).checked == false) {
                document.getElementById(check).checked = true;
            } else {
                document.getElementById(check).checked = false;
            }
            $("#chkbox").checkbox('toggle');
        }


    </script>
    <style>
        tbody tr {
            background-color: transparent;
        }

        tbody tr:hover {
            background-color: #E4E4E4;
        }

        thead td {
            padding: 5px;
        }

        .font-bold {
            font-weight: bold;
        }

        .font-normal {
            font-weight: normal;
        }

    </style>
</head>
<body>
<jsp:include page="header.jsp"/>

<br/>
<%--<c:if test="${not empty filter}">--%>
<div class="container" style="width: 100%;">

    <%--<div class="ui segment">--%>
    <%--<h5 class="ui right floated header">Фильтр</h5>--%>
    <%--<div class="ui clearing divider"></div>--%>

    <div class="ui secondary segment" style="height: 140px">

        <spring:url value="/generalFilterResult" var="generalFilterResult"/>
        <form:form modelAttribute="generalFilter" method="get" action="${generalFilterResult}">
        <table align="center" border="0">
            <thead>
            <tr>
                <td>
                    <table align="left" border="0">
                        <thead>
                        <tr>
                            <td style="padding: 5px;">
                                <form:select path="region" cssClass="selectpicker" title="Область" multiple="true"
                                             data-max-options="1" data-width="180px">
                                    <c:forEach items="${generalFilter.region}" var="regions">
                                        <c:if test="${generalFilter.selectedRegion == regions.id}">
                                            <form:option value="${regions.id}" label="${regions.name}"
                                                         cssStyle="font-weight: bold;" selected="selected"/>
                                        </c:if>
                                        <c:if test="${generalFilter.selectedRegion != regions.id}">
                                            <form:option value="${regions.id}" label="${regions.name}"
                                                         cssStyle="font-weight: bold;"/>
                                        </c:if>
                                    </c:forEach>
                                </form:select>
                            </td>
                            <td style="padding: 5px">
                                <form:select path="city" cssClass="selectpicker" multiple="true" title="Город"
                                             data-max-options="1"
                                             data-width="180px">
                                    <c:forEach items="${generalFilter.city}" var="cities">
                                        <c:if test="${generalFilter.selectedCity == cities.id}">
                                            <form:option value="${cities.id}" label="${cities.name}"
                                                         cssStyle="font-weight: bold;" selected="selected"/>
                                        </c:if>
                                        <c:if test="${generalFilter.selectedCity != cities.id}">
                                            <form:option value="${cities.id}" label="${cities.name}"
                                                         cssStyle="font-weight: bold;"/>
                                        </c:if>
                                    </c:forEach>
                                </form:select>

                            </td>
                            <td style="padding: 5px">
                                <form:select path="district" class="selectpicker" multiple="true" title="Район"
                                             data-max-options="1" data-width="180px">
                                    <c:forEach items="${generalFilter.district}" var="district">
                                        <c:if test="${generalFilter.selectedDistrict eq district.id}">
                                            <form:option value="${district.id}" label="${district.name}"
                                                         cssStyle="font-weight: bold;"
                                                         selected="selected"/>
                                        </c:if>
                                        <c:if test="${generalFilter.selectedDistrict != district.id}">
                                            <form:option value="${district.id}" label="${district.name}"
                                                         cssStyle="font-weight: bold;"/>
                                        </c:if>
                                    </c:forEach>
                                </form:select>

                            </td>
                            <td style="padding: 5px">
                                <form:select path="streets" class="selectpicker" multiple="true" title="Улица"
                                             data-max-options="1" data-width="200px">
                                    <c:forEach items="${generalFilter.streets}" var="street">
                                        <c:if test="${generalFilter.selectedStreet eq street.id}">
                                            <form:option value="${street.id}" label="${street.name}"
                                                         cssStyle="font-weight: bold;"
                                                         selected="selected"/>
                                        </c:if>
                                        <c:if test="${generalFilter.selectedStreet ne street.id}">
                                            <form:option value="${street.id}" label="${street.name}"
                                                         cssStyle="font-weight: bold;"/>
                                        </c:if>
                                    </c:forEach>
                                </form:select>

                            </td>
                            <td style="padding: 5px; font-size: small">
                                <form:input path="house" cssClass="form-control"
                                            cssStyle="width: 70px"
                                            placeholder="Дом"/></td>
                        </thead>
                        </tr>
                    </table>
                </td>
                <td></td>
            </tr>
            <tr>
                <td>
                    <table align="left" border="0">
                        <thead>
                        <tr style="font-size: small">
                            <td style="padding: 5px">
                                <form:input path="lastname" cssClass="form-control" cssStyle="width: 180px"
                                            placeholder="Фамилия"/></td>
                            <td style="padding: 5px">
                                <form:input path="firstname" cssClass="form-control" cssStyle="width: 180px"
                                            placeholder="Имя"/></td>
                            <td>
                                <form:input path="middlename" cssClass="form-control" cssStyle="width: 180px"
                                            placeholder="Отчество"/></td>
                            <td style="padding-left: 10px">
                                <div class="checkbox">
                                    <label><form:checkbox path="duplicates" value=""/>Дубликаты</label>
                                </div>

                            </td>
                        </tr>
                        </thead>
                    </table>
                </td>
                <td width="150px" align="right">
                    <button type="submit" class="ui button"><i class="icon filter"></i> Фильтр</button>
                </td>
            </tr>
            </thead>
        </table>
    </div>
    </form:form>
</div>
<%--</div>--%>
</div>
<%--</c:if>--%>
<hr>

<c:if test="${filteredPersons != null}">
    <div class="container" style="width: 80%">

        <table class="ui striped table w3-smaller compact selectable blue" style="font-family: Calibri;">
            <thead style="background-color: lightgrey">
            <tr>
                <th>id</th>
                <th>Индекс</th>
                <th>Область</th>
                <th>Город</th>
                <th>Район</th>
                <th>Улица</th>
                <th>Дом</th>
                <th>Корп.</th>
                <th>Кв.</th>
                <th>Фамилия</th>
                <th>Имя</th>
                <th>Отчество</th>
                <th>Тел.</th>
                <th>Статус</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${filteredPersons}" var="subscriber">
                <tr class="hover">
                    <td>${subscriber.id}</td>
                    <td>${subscriber.address.mailIndex}</td>
                    <td>${subscriber.address.region.name}</td>
                    <td>${subscriber.address.city.name}</td>
                    <td onclick="getDistrict('${subscriber.id}','${subscriber.address.city.name} ${subscriber.address.street.name} ${subscriber.address.house}');">
                        <div id="${subscriber.id}">${subscriber.address.district.name}</div>
                    </td>
                    <td>${subscriber.address.street.name}</td>
                    <td>${subscriber.address.house}</td>
                    <td>${subscriber.address.corps}</td>
                    <td>${subscriber.address.flat}</td>
                    <td>${subscriber.lastname}</td>
                    <td>${subscriber.firstname}</td>
                    <td>${subscriber.middlename}</td>
                    <td>${subscriber.phone}</td>
                    <td>${subscriber.status}</td>
                    <td></td>
                    <spring:url var="edit" value="/edit"/>
                    <td><a href="/edit/${subscriber.id}" target="_blank"/><i class="icon edit"></i></td>
                </tr>
            </c:forEach>
            </tbody>
            <tfoot>
            <tr>
                <th colspan="3">Найдено ${generalFilter.pager.amountOfRows} человек(а)</th>
                <th colspan="13">
                    <div class="ui right floated pagination menu">
                        <c:if test="${generalFilter.pager.currentPage > 1}">
                            <a class="icon item" href="/pager/${generalFilter.pager.currentPage-1}">
                                <i class="left chevron icon"></i></a>
                        </c:if>
                        <c:if test="${generalFilter.pager.currentPage == 1}">
                            <a class="icon item">
                                <i class="left chevron icon"></i></a>
                        </c:if>

                        <c:if test="${generalFilter.pager.lastPage == 1}">
                            <a class="item" href="/pager/1"><b>1</b></a>
                        </c:if>
                        <c:if test="${generalFilter.pager.lastPage == 2}">
                            <a class="item ${generalFilter.pager.currentPage==1 ? 'font-bold' : 'font-normal'}"
                               href="/pager/1">1</a>
                            <a class="item ${generalFilter.pager.currentPage==2 ? 'font-bold' : 'font-normal'}"
                               href="/pager/2">2</a>
                        </c:if>
                        <c:if test="${generalFilter.pager.lastPage == 3}">
                            <a class="item" href="/pager/1">1</a>
                            <a class="item" href="/pager/2">2</a>
                            <a class="item" href="/pager/3">3</a>
                        </c:if>
                        <c:if test="${generalFilter.pager.lastPage == 4}">
                            <a class="item" href="/pager/1">1</a>
                            <a class="item" href="/pager/2">2</a>
                            <a class="item" href="/pager/3">3</a>
                            <a class="item" href="/pager/4">4</a>
                        </c:if>
                        <c:if test="${generalFilter.pager.lastPage > 4}">
                            <c:if test="${generalFilter.pager.currentPage == 1}">
                                <a class="item" href="/pager/1"><b>1</b></a>
                            </c:if>
                            <c:if test="${generalFilter.pager.currentPage != 1}">
                                <a class="item" href="/pager/1">1</a>
                            </c:if>
                            <c:if test="${generalFilter.pager.currentPage == 1}">
                                <a class="item"
                                   href="/pager/${generalFilter.pager.currentPage+1}">${generalFilter.pager.currentPage+1}</a>
                                <a class="item"
                                   href="/pager/${generalFilter.pager.currentPage+2}">${generalFilter.pager.currentPage+2}</a>
                                <a class="item">...</a>
                            </c:if>
                            <c:if test="${generalFilter.pager.currentPage == 2}">
                                <a class="item"
                                   href="/pager/${generalFilter.pager.currentPage}"><b>${generalFilter.pager.currentPage}</b></a>
                                <a class="item"
                                   href="/pager/${generalFilter.pager.currentPage+1}">${generalFilter.pager.currentPage+1}</a>
                                <a class="item">...</a>
                            </c:if>
                            <c:if test="${generalFilter.pager.currentPage > 2 && generalFilter.pager.currentPage < generalFilter.pager.lastPage-2}">
                                <a class="item">...</a>
                                <a class="item"
                                   href="/pager/${generalFilter.pager.currentPage-1}">${generalFilter.pager.currentPage-1}</a>
                                <a class="item"
                                   href="/pager/${generalFilter.pager.currentPage}"><b>${generalFilter.pager.currentPage}</b></a>
                                <a class="item"
                                   href="/pager/${generalFilter.pager.currentPage+1}">${generalFilter.pager.currentPage+1}</a>
                                <a class="item">...</a>
                            </c:if>
                            <c:if test="${generalFilter.pager.currentPage == generalFilter.pager.lastPage-2}">
                                <a class="item">...</a>
                                <a class="item"
                                   href="/pager/${generalFilter.pager.currentPage}"><b>${generalFilter.pager.currentPage}</b></a>
                                <a class="item"
                                   href="/pager/${generalFilter.pager.currentPage+1}">${generalFilter.pager.currentPage+1}</a>
                            </c:if>
                            <c:if test="${generalFilter.pager.currentPage == generalFilter.pager.lastPage-1}">
                                <a class="item">...</a>
                                <a class="item"
                                   href="/pager/${generalFilter.pager.currentPage-1}">${generalFilter.pager.currentPage-1}</a>
                                <a class="item"
                                   href="/pager/${generalFilter.pager.currentPage}"><b>${generalFilter.pager.currentPage}</b></a>
                            </c:if>
                            <c:if test="${generalFilter.pager.currentPage == generalFilter.pager.lastPage}">
                                <a class="item">...</a>
                                <a class="item"
                                   href="/pager/${generalFilter.pager.currentPage-2}">${generalFilter.pager.currentPage-2}</a>
                                <a class="item"
                                   href="/pager/${generalFilter.pager.currentPage-1}">${generalFilter.pager.currentPage-1}</a>
                                <a class="item"
                                   href="/pager/${generalFilter.pager.lastPage}"><b>${generalFilter.pager.lastPage}</b></a>
                            </c:if>
                            <c:if test="${generalFilter.pager.currentPage != generalFilter.pager.lastPage}">
                                <a class="item"
                                   href="/pager/${generalFilter.pager.lastPage}">${generalFilter.pager.lastPage}</a>
                            </c:if>
                        </c:if>

                        <c:if test="${generalFilter.pager.currentPage < generalFilter.pager.lastPage}">
                            <a class="icon item" href="/pager/${generalFilter.pager.currentPage+1}">
                                <i class="right chevron icon"></i>
                            </a>
                        </c:if>
                        <c:if test="${generalFilter.pager.currentPage == generalFilter.pager.lastPage}">
                            <a class="icon item">
                                <i class="right chevron icon"></i>
                            </a>
                        </c:if>
                    </div>
                </th>
            </tr>
            </tfoot>

        </table>
        <br/>

    </div>
</c:if>


</body>
</html>
