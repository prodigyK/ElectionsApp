<%--
  Created by IntelliJ IDEA.
  User: kostya
  Date: 04.05.2019
  Time: 18:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="webjars/Semantic-UI/2.4.1/semantic.min.css">
    <link rel="stylesheet" type="text/css" href="webjars/Semantic-UI/2.4.1/components/dropdown.min.css">
    <link rel="stylesheet" type="text/css" href="webjars/Semantic-UI/2.4.1/components/transition.min.css">
    <link rel="stylesheet" href="webjars/bootstrap/4.1.3/css/bootstrap.min.css">
    <%--<script type="text/javascript" src="webjars/jquery/3.3.1-1/jquery.min.js" defer></script>--%>
    <script
            src="https://code.jquery.com/jquery-3.1.1.min.js"
            integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
            crossorigin="anonymous"></script>
    <script src="webjars/Semantic-UI/2.4.1/semantic.min.js"></script>
    <script src="webjars/Semantic-UI/2.4.1/components/dropdown.min.js"></script>
    <script src="webjars/Semantic-UI/2.4.1/components/transition.min.js"></script>
    <script type="text/javascript" src="webjars/popper.js/1.14.4/dist/umd/popper.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/4.1.3/js/bootstrap.min.js" defer></script>
    <title>Title</title>
    <script language="JavaScript">
        $(document).ready(function () {

            $('#search-select').dropdown();
        })
    </script>
</head>
<body>

<select class="ui search selection dropdown" id="search-select">
    <option value="">State</option>
    <option value="AL">Alabama</option>
    <option value="AK">Alaska</option>
    <option value="AZ">Arizona</option>
    <option value="AR">Arkansas</option>
    <option value="CA">California</option>
    <!-- Saving your scroll sanity !-->
    <option value="OH">Ohio</option>
    <option value="OK">Oklahoma</option>
    <option value="OR">Oregon</option>
    <option value="PA">Pennsylvania</option>
    <option value="RI">Rhode Island</option>
    <option value="SC">South Carolina</option>
    <option value="SD">South Dakota</option>
    <option value="TN">Tennessee</option>
    <option value="TX">Texas</option>
    <option value="UT">Utah</option>
    <option value="VT">Vermont</option>
    <option value="VA">Virginia</option>
    <option value="WA">Washington</option>
    <option value="WV">West Virginia</option>
    <option value="WI">Wisconsin</option>
    <option value="WY">Wyoming</option>
</select>
</body>
</html>
