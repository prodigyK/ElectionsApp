<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: kostya
  Date: 01.03.2018
  Time: 18:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FondBase</title>
    <div class="container">
        <h1>Upload File</h1>

        <br/>
        <form:form action="/download" method="post" enctype="multipart/form-data">
            <div ID="items">
                <strong>File: </strong>
                <input type="file" name="file" size="65" class="btn btn-default btn-sm"/>

                <input type="submit" value="Upload"/>

            </div>

        </form:form>
    </div>

</head>
<body>

</body>
</html>
