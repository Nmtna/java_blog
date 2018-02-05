
package cnt;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jc.Sql;


@WebServlet(name = "edit", urlPatterns = {"/cnt/edit"})
public class Edit extends HttpServlet {


 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession() ;
        
       if (session.getAttribute("login") != null) {
           
           int user_id = (Integer) session.getAttribute("login") ;
           
           int post_id = 0 ;
           
           try {
              post_id = Integer.parseInt(request.getParameter("post_id")) ;
           } catch (NumberFormatException ex) {
                response.sendRedirect(request.getContextPath() + "/manage/login") ;
            }
               
           
           
           boolean check_edit = false ; // check if the the user has access to edit the post
           
            try {
                check_edit = Sql.checkEdit(post_id , user_id) ;
            } catch (ClassNotFoundException | SQLException ex) {
                check_edit = false ;
            }
           
           if (check_edit) {
           
             String title = request.getParameter("title") ;
             String content = request.getParameter("content") ;
             
             if (title.length() < 50 && content.length() < 1000 ) {
             
                boolean check_update = false ; // return true if any row was effected. return false if 0 row was effected ;

                try {
                    check_update = Sql.updateQry(post_id , title , content) ;
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(Edit.class.getName()).log(Level.SEVERE, null, ex);
                    response.sendRedirect(request.getContextPath() + "/manage/serror.jsp") ; 
                }

                if (!check_update) {
                    response.sendRedirect(request.getContextPath() + "/manage/serror.jsp") ; 
                } else {
                    response.sendRedirect(request.getContextPath() + "/manage/admin.jsp") ; 
                }
               
            } else {
               response.sendRedirect(request.getContextPath() + "/manage/serror.jsp") ; 
             }
             
               
           
           }  
           
       }
        
        
    }


    
}
