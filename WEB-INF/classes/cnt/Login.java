
package cnt;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jc.Sql ;

@WebServlet(name = "login", urlPatterns = {"/cnt/login"})
public class Login extends HttpServlet {



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        String username = request.getParameter("username") ;
        String password = request.getParameter("password") ;
        
        
        // if username && password == corect return true __ otherwise false
        boolean loginCheck = false  ; 
        
        if (username != null && password != null && username != "" && password != "" ) {

            try {
                loginCheck = Sql.loginCheck(username , password) ;
            } catch (ClassNotFoundException | SQLException ex) {
                loginCheck = false ;
            }
        }

        
        // runs if user name and password are correct
        if (loginCheck) {
            
            int id = 0 ;

            // Getting the Id of the user ;
            try {
                id = Sql.getIdFromUser(username) ;
            } catch (ClassNotFoundException | SQLException ex) {
                id = 0 ;
            }
            
            HttpSession session = request.getSession() ;
            session.setAttribute("login" , id ) ;
            
            
            response.sendRedirect(request.getContextPath() + "/manage/admin.jsp") ;
            
                  
        } else {

            response.sendRedirect(request.getContextPath() + "/manage/login.jsp") ;
        }
        
        
        
        
    }


}
