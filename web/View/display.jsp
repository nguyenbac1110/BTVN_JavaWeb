<%-- 
    Document   : display
    Created on : Nov 6, 2024, 6:44:25 PM
    Author     : Kan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
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
                <input type="radio" name="chucnang" value="tangdan">
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
            
            <table border="1">
            <tr>
                <th>Mã môn</th>
                <th>Tên môn</th>
                <th>Số tiết</th>
            </tr>
            <%
                ResultSet resultSet = (ResultSet) request.getAttribute("rs");
                if (resultSet != null) {
                    try {
                        while (resultSet.next()) {
                            String mamon = resultSet.getString("mamon");
                            String tenmonhoc = resultSet.getString("tenmonhoc");
                            int sotiet = resultSet.getInt("sotiet");
            %>
            <tr>
                <td><%= mamon %></td>
                <td><%= tenmonhoc %></td>
                <td><%= sotiet %></td>
            </tr>
            <%
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            %>
            </table>
    </body>
</html>
