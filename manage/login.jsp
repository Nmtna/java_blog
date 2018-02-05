
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        
        <% if (session.getAttribute("login") == null ) { %>
        
        
        <br>
        <form action="${pageContext.request.contextPath}/cnt/login" method="post" >      
            Username: <br><input name="username" ><br><br>
            Password: <br><input name="password" type="password" ><br><br>
            <input type="submit" value="Login" >
        </form>
            
        <% } else {
            response.sendRedirect(request.getContextPath()) ; 
        } %>
        
    </body>
</html>
