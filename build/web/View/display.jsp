<%-- 
    Document   : display
    Created on : Nov 6, 2024, 6:44:25 PM
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
        <h1>Danh sách Môn học</h1>
        
        <form action="display" method="POST">
            <label>
                <input type="radio" name="chucnang" value="tangdan" checked>
                Sắp xếp số lượng tăng dần
            </label>
            <br>
            <label>
                <input type="radio" name="chucnang" value="giamdan">
                Sắp xếp số lượng giảm dần
            </label>
            <br>
            <label>
                <input type="radio" name="chucnang" value="macdinh">
                Đưa về trạng thái mặc định
            </label>
            <br><br>
            <button type="submit">Sắp xếp</button>
    </body>
</html>
