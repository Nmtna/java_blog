
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

/**
 *
 * @author root
 */
@WebServlet(name = "Add", urlPatterns = {"/cnt/add"})
public class Add extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession() ;
        
        if (session.getAttribute("login") != null ) {
            
            String title = request.getParameter("title") ;
            String content = request.getParameter("content") ;
            int user_id = (Integer) session.getAttribute("login") ;
            
            if (title != null && content != null) {
                
                if (title.length() < 50 && content.length() < 1000 ) {
                
                    boolean add_result = false ;

                    try {
                        add_result = Sql.addQry(title, content , user_id) ;
                    } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(Add.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (add_result) {
                        response.sendRedirect(request.getContextPath() + "/manage/admin.jsp") ;
                    } else {
                        response.sendRedirect(request.getContextPath() + "/manage/serror.jsp") ;
                    }
                    
                } else {
                    response.sendRedirect(request.getContextPath() + "/manage/serror.jsp") ;
                }
                
                
                
                
            } else {
                response.sendRedirect(request.getContextPath()) ;
            }   
            
            
            
            
             
        } else {
            response.sendRedirect(request.getContextPath() + "/manage/login.jsp") ;
        }
                
             
        
        
        
        
    }



}
