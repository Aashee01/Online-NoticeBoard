<%@page import="java.sql.*"%>
<%
    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    Connection con=DriverManager.getConnection("jdbc:odbc:NoticeBoardDsn");
    String qr="select message from notice where noticeid=?";
    PreparedStatement ps=con.prepareStatement(qr);
    String id=request.getParameter("id");
    ps.setInt(1, Integer.parseInt(id));
    ResultSet rs=ps.executeQuery();
    rs.next();
    String msg=rs.getString(1);
    con.close();
%>
<html>
    <body>
        <h2>Notice Udpate</h2>
        
        <form action="ChangeNotice">
            <pre>
            Notice Id   <input type="text" name="id" value="<%=id%>" readonly="readonly" />
            Message     <input type="text" name="message" value="<%=msg%>" />
                        <input type="submit" value="Update">
            </pre>
        </form> 
        
        <a href="noticeupdatter.jsp">back</a><br>
        <a href="adminhome.jsp">home</a><br>
    </body>
</html>
