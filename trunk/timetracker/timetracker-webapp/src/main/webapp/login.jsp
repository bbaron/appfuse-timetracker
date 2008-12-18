<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
    <title>TimeTracker Login</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="styles/global.css" type="text/css" />
</head>
<body>

    <div class="topmenu">
        <a class="menu" href="login.html">Log in</a>
    </div>

    <div class="header">
        <h1><span>Time</span>Tracker</h1>
    </div>

    <div class="content">
    <c:if test="${param.error == true}">
    <font color="red">
    Login error. <br />
    Reason: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
    </font>
    </c:if>

        <h2>Login</h2>

        <form method="POST" action="<c:url value="/j_spring_security_check" />">
            <label for="username">Username</label><br />
            <input type="text" name="j_username" /><br /> 
            <label for="password">Password</label><br />
            <input type="password" name="j_password" /><br />
            <input type="checkbox" name="_spring_security_remember_me" />Remember me<br />
            <br />
            <input class="button" type="submit" value="Login" />
        </form>

    </div>

</body>
</html>
