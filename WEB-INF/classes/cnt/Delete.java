
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


@WebServlet(name = "Delete", urlPatterns = {"/cnt/delete"})
public class Delete extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession() ;
        
        if (session.getAttribute("login") != null ) {
            
            int post_id = Integer.parseInt(request.getParameter("id")) ;
            int user_id = (Integer) session.getAttribute("login") ;
            
            boolean check_edit = false ;
            
            try {
                check_edit = Sql.checkEdit(post_id , user_id) ;
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Delete.class.getName()).log(Level.SEVERE, null, ex);
                response.sendRedirect(request.getContextPath() + "/manage/serror.jsp") ;
            }
            
            
            if (check_edit) {
                
                boolean delete_check = false ;
                
                try {
                    delete_check = Sql.deleteQry(post_id) ;
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(Delete.class.getName()).log(Level.SEVERE, null, ex);
                    response.sendRedirect(request.getContextPath() + "/manage/serror.jsp") ;
                }
                
                if(delete_check) {
                    response.sendRedirect(request.getContextPath() + "/manage/admin.jsp") ;
                } else {
                    response.sendRedirect(request.getContextPath() + "/manage/serror.jsp") ;
                }
                    
                
                
                
                
            } else {
                response.sendRedirect(request.getContextPath() + "/manage/serror.jsp") ;
            }
            
            
            
        
            
        } else {
            response.sendRedirect(request.getContextPath() + "/manage/login.jsp") ;
        }
            
        
    }

}
