<%-- 
    Document   : taikhoan
    Created on : Nov 6, 2024, 6:31:08 PM
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
        <h1>Đăng ký tài khoản</h1>
        <form action="taikhoan" method="POST">
            <label for="username">Tài khoản:</label>
            <input type="text" name="username" required><br><br>
            <label for="password">Mật khẩu:</label>
            <input type="text" name="password" required><br><br>
            <input type="submit" name="chucnang" value="them">
            <input type="submit" name="chucnang" value="sua">
            <input type="submit" name="chucnang" value="xoa">
            <input type="reset" value="Reset">
        </form>
    </body>
</html>
