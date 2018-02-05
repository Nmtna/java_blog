<%-- 
    Document   : edit
    Created on : Jan 27, 2018, 12:35:57 PM
    Author     : root
--%>

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
            
        if (request.getParameter("id") != null ) {
        
            String post_id_string = request.getParameter("id") ;
            int post_id_int = Integer.parseInt(post_id_string) ;

            if (session.getAttribute("login") != null ) {
                int user_id = (Integer) session.getAttribute("login") ;



                boolean check_boolean = false ;


                check_boolean = Sql.checkEdit(post_id_int , user_id) ;


                if (check_boolean) {

                String title = Sql.getTitleFromId(post_id_int) ;

                String content = Sql.getContentFromId(post_id_int) ;

                 %>

                <form action="${pageContext.request.contextPath}/cnt/edit" method="post" >
                    Title: <br><input type="text" name="title" value="<%= title %>" > <br><br>
                    Content: <br><textarea cols="60" rows="15" name="content" ><%= content %></textarea><br><br>
                    <input type="hidden" value="<%= post_id_int %>" name="post_id" >
                    <input type="submit" value="Edit" >
                    
                    

                </form>



            <%
                } else {
                    out.println("You dont have accesss to edit this file") ;
                }


            } else { 
                response.sendRedirect(request.getContextPath() + "/manage/login.jsp") ;  
            }           

        }
        %>
        
    </body>
</html>
