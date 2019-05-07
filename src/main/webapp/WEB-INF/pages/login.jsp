<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<jsp:include page="fragments/headTag.jsp"/>

<body class="text-center">

<div class="container">
    <div class="row text-center mb-4">
        <div class="col-md-12">
        </div>
    </div>
    <div class="row text-center" style="border: #6ec6ff">
        <div class="col-md-4 offset-md-4">
            <div class="card">
                <div class="card-body">
                    <div class="login-title">
                        <h4>Вход</h4>
                    </div>
                    <div class="login-form mt-4">
                        <form action="spring_security_check" method='POST'>
                            <div class="form-row">
                                <div class="form-group col-md-12">
                                    <input name="username" placeholder="Логин" class="form-control" type="text">
                                </div>
                                <div class="form-group col-md-12">
                                    <input name="password" type="password" class="form-control" placeholder="Пароль">
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group">
                                    <div class="form-check">
                                        <label class="form-check-label">
                                            <input type="checkbox" class="form-check-input" name="remember-me">
                                            <small>Запомнить меня</small>
                                        </label>

                                    </div>
                                </div>
                            </div>

                            <div class="form-row">
                                <button type="submit" class="btn btn-primary btn-block">Вход</button>
                            </div>
                            <br>
                            <c:if test="${not empty error}">
                                <div style="color: red; text-align: center; font-size:small;"><h7>${error}</h7></div>
                            </c:if>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>


