<%@page import="java.sql.*"%>
<%
    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    Connection con=DriverManager.getConnection("jdbc:odbc:NoticeBoardDsn");
    String qr="select noticeid, message from notice";
    Statement stmt=con.createStatement();
    ResultSet rs=stmt.executeQuery(qr);
%>
<html>
    <body>
        <h4>Select The Notices To Remove</h4>
        <form action="RemoveNotices">
        <table border="5" width="5" cellspacing="5">
            <thead>
                <tr>
                    <th>NoticeId</th>
                    <th>Message</th>
                   
                </tr>
            </thead>
            <tbody>
                <%
                while(rs.next())
                {        
                    String s1=rs.getString(1);
                    String s2=rs.getString(2);
                %>
                <tr>
                    <td><%=s1%></td>
                    <td colspan="60%"><%=s2%></td>
                    <td><input type="checkbox" name="nid" value="<%=s1%>"/></td>
                </tr>
                <%
                 }
                con.close();
                %>
            </tbody>
        </table>
            <input type="submit" value="RemoveAll">
        </form>
    </body>
</html>
