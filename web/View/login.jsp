<%-- 
    Document   : login
    Created on : Nov 6, 2024, 6:30:53 PM
    Author     : Kan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1><h1>Đăng nhập tài khoản</h1>
        <form action="login" method="POST">
            USERNAME: <input type="text" name="username" required><br><br>
            PASSWORD: <input type="text" name="password" required><br><br>
            <input type="submit" value="login">
            <input type="reset" value="Reset">
        </form>
    </body>
</html>
