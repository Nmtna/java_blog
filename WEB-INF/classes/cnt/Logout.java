
package cnt;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "Logout", urlPatterns = {"/cnt/logout"})
public class Logout extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        

        HttpSession session = request.getSession() ;
        
        if (session.getAttribute("login") != null) {
            session.removeAttribute("login") ;
            response.sendRedirect(request.getContextPath()) ; 
        } else {
            response.sendRedirect(request.getContextPath()) ; 
        }   
        
    }



}
