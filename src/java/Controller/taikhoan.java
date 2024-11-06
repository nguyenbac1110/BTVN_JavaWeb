/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Kan
 */
@WebServlet(name = "taikhoan", urlPatterns = {"/taikhoan"})
public class taikhoan extends HttpServlet {
KetNoi k = new KetNoi();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet taikhoan</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet taikhoan at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        request.getRequestDispatcher("/View/taikhoan.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //processRequest(request, response);
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String chucNang = request.getParameter("chucnang");
        try{
            k.getConnection();
            switch(chucNang){
                case "them" -> AddUser(username, password);
                case "sua" -> UpdateUser(username, password);
                case "xoa" -> DeleteUser(username);
                
            }
            request.getRequestDispatcher("/View/taikhoan.jsp").include(request, response);
            getAll(response);
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }
      private void AddUser(String username, String password) throws SQLException{
        String themSql = "INSERT INTO user (username, password) VALUES (?, ?)";
        try(PreparedStatement stm = k.con.prepareStatement(themSql)){
            stm.setString(1, username);
            stm.setString(2, password);
            stm.executeUpdate();
        }    
    }
    private void UpdateUser(String username, String password) throws SQLException{
        String suaSql = "UPDATE user SET password = ? WHERE username = ?";
        try(PreparedStatement stm = k.con.prepareStatement(suaSql)){
            stm.setString(1, password);
            stm.setString(2, username);
            stm.executeUpdate();
        }    
    }   
    private void DeleteUser(String username) throws SQLException{
        String xoaSql = "DELETE FROM user WHERE username = ?";
        try(PreparedStatement stm = k.con.prepareStatement(xoaSql)){
            stm.setString(1, username);
            stm.executeUpdate();
        }    
    }   
    public void getAll(HttpServletResponse response) throws IOException{
        try {
            k.getConnection();
            String sql = "SELECT * FROM user";
            PreparedStatement stm = k.con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery(); //Thuc thi cau lenh sql
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.print("<table border = '1'>");
            out.print("<tr><th>STT</th><th>USERNAME</th><th>PASSWORD</th></tr>");
            while(rs.next()){
             int stt = rs.getInt(1);
             String user = rs.getString(2);
             String pass = rs.getString(3);
             out.println("<tr>");
             out.println("<td>"+ stt +"</td>");
             out.println("<td>"+ user +"</td>");
             out.println("<td>"+ pass +"</td>");
             out.println("<tr>");
             out.println("</br>");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }  
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
