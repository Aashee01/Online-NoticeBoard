
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VerifyUser extends HttpServlet {

    Connection con; PreparedStatement ps;
    
    public void init() {
        try
        {
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        con = DriverManager.getConnection("jdbc:odbc:NoticeBoardDsn");
        String qr = "select * from student where userid=? and password=?";
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
        String s3= request.getParameter("utype");
        //step-2 process the request
        if(s3.equals("admin"))
        {
            if(s1.equals("admin") && s2.equals("indore"))
            {
                response.sendRedirect("adminhome.jsp");
            }
            else
            {
                out.println("Invalid Admin");
            }
        }
        else
        {
            try
            {
            ps.setString(1, s1);
            ps.setString(2, s2);
            ResultSet rs=ps.executeQuery();
            boolean b=rs.next();
            if(b==true)
            {
                
                String op=request.getParameter("save");
                if(op!=null)
                {
                Cookie c1=new Cookie("uid", s1);
                Cookie c2=new Cookie("pw", s2);
                c1.setMaxAge(60*60*24*7);
                c2.setMaxAge(60*60*24*7);
                response.addCookie(c1);
                response.addCookie(c2);
                }
                else
                {
                Cookie c1=new Cookie("uid", "");
                Cookie c2=new Cookie("pw", "");
                c1.setMaxAge(60*60*24*7);
                c2.setMaxAge(60*60*24*7);
                response.addCookie(c1);
                response.addCookie(c2);
                }
                response.sendRedirect("studenthome.jsp");
            }
            else
            {
                out.println("Invalid Student");
            }
            }
            catch(Exception ex){}
            
            
            
            
            
            
            
        }
        
        
        
        
        
        

    }
}