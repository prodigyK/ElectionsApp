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

        <div align="right">
            <a href="/modules/module-election-candidates" class="btn btn-outline-primary button_width" onclick="cancel()">
                <i class="fas fa-arrow-left"></i> <spring:message code="common.back"/></a>
        </div>
        <h4 id="upload_title">Загрузка кандидатов из файла</h4>
        <hr>
        <form action="rest/election/candidate/upload" method="post" id="formData" enctype="multipart/form-data">
            <table border="0" width="100%">
                <tr>
                    <td width="250">
                        <select class="form-control" id="election" name="election">
                            <c:forEach items="${elections}" var="election">
                                <option value="${election.id}">${election.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td width="300">
                        <div align="center">
                            <input id="file" name="file" type="file"/>
                        </div>
                    </td>
                    <td>
                        <input type="submit" class="btn btn-outline-primary" />
                    </td>
                </tr>
            </table>
        </form>

        <br>

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
