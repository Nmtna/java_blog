
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="jc.Sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
      
    <% 
        
    if (session.getAttribute("login") != null) {
        int id = (Integer) session.getAttribute("login") ;
        String username = Sql.getUserFromId(id) ;
        
        
    %>
    
       
    Hello <%= username %>
    
    _________
    
    <br>
        <h4> Add New Post: </h4> <br>
        
        <form action="${pageContext.request.contextPath}/cnt/add" method="post" >
            Username: <br> <input type="text" name="title" > <br><br>
            Password: <br><textarea rows="15" cols="50" name="content" > </textarea> <br><br>
            
            <input type="submit" >
            
            
        </form>
        
    <br> <br>
    
    <br>     
    
    ___________________________________________________________________ <br>
    
    <% 
        
        if ( session.getAttribute("login") != null ) {
        
       
        Class.forName("com.mysql.jdbc.Driver") ;
       
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DATA2" , "mmm" , "1111" ) ;
        
        Statement myS = myConn.createStatement() ; 
                
        String qry = "select * from cms1_posts where user_id = " + id  ; 
        
        ResultSet myRS = myS.executeQuery(qry) ;

        while (myRS.next() ) {
            
            String page_edit_address = request.getContextPath() + "/manage/edit.jsp?id=" + myRS.getInt("id") ;
            String page_delete_address = request.getContextPath() + "/cnt/delete?id=" + myRS.getInt("id") ;
    %>
            
        
            <br><br>
            <h4>Title:</h4>  <%= myRS.getString("title") %> ____
                <a href="<%= page_edit_address %>" >Edit</a> _____
                <a href="<%= page_delete_address %>" > Delete </a>
                
            <br>  <br> 
            
            <h4> Content:</h4>
            
                <% 
                    String content = myRS.getString("content") ;
                    int maxLen = 50 ;
                    if (content.length() > maxLen) {
                        content = content.substring(0 , maxLen) ;
                    }
                %>
                
                <%= content %>....
                    
                <br><br> <br>
                
                
            
            =====================
            
    <% }
    myConn.close() ;
    %>
        
        
      

                    
   
    
        
        
    
            
    <% } else {
        response.sendRedirect(request.getContextPath()) ;
        }
    
    } else {
        response.sendRedirect(request.getContextPath() + "/manage/login.jsp") ; 
    }
    
    %>
        
        
    </body>
</html>
