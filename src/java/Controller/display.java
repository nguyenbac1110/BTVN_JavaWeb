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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author Kan
 */
@WebServlet(name = "display", urlPatterns = {"/display"})
public class display extends HttpServlet {
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
            out.println("<title>Servlet display</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet display at " + request.getContextPath() + "</h1>");
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
         request.getRequestDispatcher("/View/display.jsp").forward(request, response);

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
        String chucnang = request.getParameter("chucnang");
        try {
            k.getConnection();
            switch (chucnang) {
                case "tangdan" -> sapxeptangdan(response); 
                case "giamdan" -> sapxepgiamdan(response);          
                case "macdinh" -> trovemacdinh(response);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
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

    private void sapxeptangdan(HttpServletResponse response) throws SQLException{
        String sql = "select * from monhoc order by sotiet asc";
        PreparedStatement stm = k.con.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        try {
        getAll(response,rs);
    } catch (IOException ex) {
        Logger.getLogger(display.class.getName()).log(Level.SEVERE, null, ex);
    }
          
    }

    private void sapxepgiamdan(HttpServletResponse response) throws SQLException{
        String sql = "select * from monhoc order by sotiet desc";
        PreparedStatement stm = k.con.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        try {
        getAll(response,rs);
    } catch (IOException ex) {
        Logger.getLogger(display.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    private void trovemacdinh(HttpServletResponse response) throws SQLException{
        String sql = "select * from monhoc";
         PreparedStatement stm = k.con.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        try {
            getAll(response,rs);
        } catch (IOException ex) {
            Logger.getLogger(display.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getAll(HttpServletResponse response,ResultSet rs) throws IOException {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.print("<table border = '1'>");
            out.print("<tr><th>Mã môn</th><th>Tên môn học</th><th>Số tiết</th></tr>");
            try {
                while(rs.next()){
                    String ma = rs.getString(1);
                    String ten = rs.getString(2);
                    String sotiet = rs.getString(3);
                    out.println("<tr>");
                    out.println("<td>"+ ma +"</td>");
                    out.println("<td>"+ ten +"</td>");
                    out.println("<td>"+ sotiet +"</td>");
                    out.println("<tr>");
                    out.println("</br>");
                }
            } catch (SQLException ex) {
                Logger.getLogger(display.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.print("</table>");
        
    }

}