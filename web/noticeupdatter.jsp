<%@page import="java.sql.*"%>
<%
    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    Connection con=DriverManager.getConnection("jdbc:odbc:NoticeBoardDsn");
    Statement stmt=con.createStatement();
    ResultSet rs=stmt.executeQuery("select noticeid from notice");
%>
<html>
    <body>
        <h2>Notice Updatter</h2>
        <h3>Select Notice To Be Updatted</h3>
        <pre>
        <form action="updatenotice.jsp">
            Notice Id   <select name="id">
                <%
                    while(rs.next())
                    {
                        String s=rs.getString(1);
                    
                %>
                <option> <%=s%> </option>            
                <%
                     }
                    con.close();
                %>
                
                        </select>
            <input type="submit" value="Update">
        </form>
        </pre>
    </body>
</html>
