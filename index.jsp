
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        
        <% if (session.getAttribute("login") != null) { %>
        <a href="${pageContext.request.contextPath}/manage/admin.jsp" > Admin Page </a> <br>
        <a href="${pageContext.request.contextPath}/cnt/logout" > Logout </a>
        
        <% } else { %>
        
        <a href="${pageContext.request.contextPath}/manage/login.jsp" > Login </a>
        
        <% } %>
        
    
        
        <br><br>
        
        <% 
        Class.forName("com.mysql.jdbc.Driver") ;
       
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DATA2" , "mmm" , "1111" ) ;
        
        Statement myS = myConn.createStatement() ; 
                
        String qry = "select * from cms1_posts"  ; 
        
        ResultSet myRS = myS.executeQuery(qry) ;

        while (myRS.next() ) {  %>
            
             <h3> <%= myRS.getString("title") %> </h3> <br>
            <h5> <%= myRS.getString("content") %> </h5>
            
            __________

        <% } %>
        
    </body>
</html>
