import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowUnpostedNotices extends HttpServlet {

    Connection con; 
    ResultSet rs;
    Statement stmt;
    public void destroy() {
        try
        {
        con.close();
        }
        catch(Exception ex){}
    }
    
    public void init() {
        try
        {
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        con = DriverManager.getConnection("jdbc:odbc:NoticeBoardDsn");
        stmt=con.createStatement();
        }
        catch(Exception ex){}
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String qr="select * from notice where status='no'";
        out.println("<html>");
        out.println("<body>");
        out.println("<h2>Unposted Notices</h2>");
        out.println("<hr>");
        try
        {
        rs=stmt.executeQuery(qr);
        out.println("<table border=4>");
        out.println("<tr>");
        out.println("<td>Code</td>");
        out.println("<td>Message</td>");
        out.println("<td>Type</td>");
        out.println("<td>Post</td>");
        out.println("</tr>");
        while(rs.next())
        {
            String s1=rs.getString(1);
            String s2=rs.getString(2);
            String s3=rs.getString(3);
            out.println("<tr>");
            out.println("<td>");
            out.println(s1);
            out.println("</td>");
            out.println("<td>");
            out.println(s2);
            out.println("</td>");
            out.println("<td>");
            out.println(s3);
            out.println("</td>");                    
            out.println("<td>");
            out.println("<a href=PostNotice?id="+s1+">Post-Now</a>");
            out.println("</td>");
            out.println("</tr>");
        }
        
        out.println("</table>");
        out.println("<a href=adminhome.jsp>home</a><br>");
        out.println("</body>");
        out.println("</html>");
        
        }
        catch(Exception ex)
        {
            out.println(ex);
        }
        
        
        
        
        
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
