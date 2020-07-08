import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowNotices extends HttpServlet {
    String check="init";
    Connection con; 
    ResultSet rs;
    PreparedStatement ps;
    
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
        String qr="select message from notice where type=? and status='yes'";
        ps=con.prepareStatement(qr);
        }
        catch(Exception ex){}
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String type=request.getParameter("mtype");
        out.println(type);
        out.println("<html>");
        out.println("<body>");
        out.println("<h2>"+type+" Notices</h2>");
        out.println("<hr>");
        try
        {
        ps.setString(1, type);
        rs=ps.executeQuery();
        out.println("<table border=3>");
        while(rs.next())
        {
            String s=rs.getString(1);
            out.println("<tr>");
            out.println("<td>");
            out.println(s);
            out.println("</td>");
            out.println("</tr>");
        }
        out.println("</table>");
        out.println("<a href=ShowNoticeTypes>back</a><br>");
        out.println("<a href=index.jsp>home</a><br>");
        out.println("</body>");
        out.println("</html>");
        
        }
        catch(Exception ex)
        {
            out.println(ex);
        }
//        
        
        
        
        
        
        
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
