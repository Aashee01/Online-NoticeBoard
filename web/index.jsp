<%
        Cookie c[]=request.getCookies();
        String v1="", v2="";
        
        if(c!=null)
        {
        for(int i=0; i<c.length; i++)
        {
            Cookie tmp=c[i];
            String name=tmp.getName();
            if(name.equals("uid"))
            {
                v1=tmp.getValue();
            }
            
            if(name.equals("pw"))
            {
                v2=tmp.getValue();
            }
        }
        }
%>

<html>
    <body>
        <h1>College Notice Board</h1>
        <hr>
        <form action="VerifyUser" method="get">
        <pre>
            LoginId         <input type="text" name="username" value="<%=v1%>">
            Password        <input type="password" name="password" value="<%=v2%>">
            User-Type       <select name="utype">
                                <option>student</option>
                                <option>admin</option>
                            </select>
            SavePassword    <input type="checkbox" name="save" value="yes">
                            <input type="submit" value="Login">
        </pre>
        </form>
        
        <hr>
        <a href="registration.jsp">New-User</a>
    </body>
</html>
