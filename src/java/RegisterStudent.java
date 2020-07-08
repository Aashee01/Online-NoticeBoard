
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterStudent extends HttpServlet {

    Connection con; PreparedStatement ps;
    
    public void init() {
        try
        {
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        con = DriverManager.getConnection("jdbc:odbc:NoticeBoardDsn");
        String qr = "insert into student values(?,?,?,?,?)";
        ps = con.prepareStatement(qr);
        }
        catch(Exception ex){}
    }

    public void destroy() {
        try
        {
        con.close();
        }
        catch(Exception ex){}
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();

        //step-1 reads the request parameters
        String s1 = request.getParameter("username");
        String s2 = request.getParameter("password");
        String s3 = request.getParameter("sname");
        String s4 = request.getParameter("address");
        String s5 = request.getParameter("contact no");
        
        String s6 = request.getParameter("branch");
        String s7 = request.getParameter("semester");
        //step-2 process the request
        try {
            ps.setString(1, s1);
            ps.setString(2, s2);
            ps.setString(3, s3);
            ps.setString(4, s4);
            ps.setString(5, s5);
            
            ps.setString(6, s6);
            ps.setString(7, s7);
            ps.executeUpdate();
            
            out.println("<html>");
            out.println("<body>");
            out.println("<h2>Registration Successfully Completed</h2>");
            out.println("<a href=index.jsp>login-now</a>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex) {
            out.println(ex);
        }

    }
}