<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>


<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Database</title>
    <base href="${pageContext.request.contextPath}/"/>

    <link rel="stylesheet" href="resources/css/style.css">
    <link rel="stylesheet" href="webjars/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="webjars/noty/3.1.4/demo/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="webjars/datatables/1.10.19/css/dataTables.bootstrap4.min.css">
    <link rel="stylesheet" href="webjars/noty/3.1.4/lib/noty.css"/>
    <link rel="stylesheet" href="webjars/font-awesome/5.3.1/css/all.css"/>
    <link rel="stylesheet" href="webjars/Semantic-UI/2.4.1/semantic.min.css">
<%--<link rel="stylesheet" href="webjars/bootstrap-select/1.13.1/dist/css/bootstrap-select.min.css"/>--%>

    <%--http://stackoverflow.com/a/24070373/548473--%>
    <script type="text/javascript" src="webjars/jquery/3.3.1-1/jquery.min.js" defer></script>
    <%--<script type="text/javascript" src="webjars/jquery-ui/1.12.1/jquery-ui.min.js" defer></script>--%>
    <script type="text/javascript" src="webjars/popper.js/1.14.4/dist/umd/popper.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/4.1.3/js/bootstrap.min.js" defer></script>
    <script type="text/javascript" src="webjars/datatables/1.10.19/js/jquery.dataTables.min.js" defer></script>
    <script type="text/javascript" src="webjars/datatables/1.10.19/js/dataTables.bootstrap4.min.js" defer></script>
    <script type="text/javascript" src="webjars/noty/3.1.4/lib/noty.min.js" defer></script>
    <script type="text/javascript" src="webjars/font-awesome/5.3.1/js/all.js"></script>
    <script
            src="https://code.jquery.com/jquery-3.1.1.min.js"
            integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
            crossorigin="anonymous"></script>

    <script type="text/javascript" src="webjars/Semantic-UI/2.4.1/semantic.min.js"></script>

    <%--<script type="text/javascript" src="webjars/bootstrap-select/1.13.1/dist/js/bootstrap-select.min.js"></script>--%>

    <link rel="shortcut icon" href="favicon.ico">
<%--
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.2/css/bootstrap-select.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.2/js/bootstrap-select.min.js"></script>

    <!-- (Optional) Latest compiled and minified JavaScript translation files -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.2/js/i18n/defaults-*.min.js"></script>
--%>

</head>