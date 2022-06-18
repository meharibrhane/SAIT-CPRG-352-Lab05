
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;

/**
 *
 * @author mehar
 
 */
public class loginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        if(request.getParameter("action") != null)
        {
         
         session.invalidate();
         request.setAttribute("loggedout", true);

        }
        else if(session.getAttribute("username") != null)
        {
          response.sendRedirect("home");
          return;
        }
            
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
      
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        request.setAttribute("username", username);
        request.setAttribute("password", password);
       
      if(username == null || username.equals("") || password == null || password.equals(""))
      {
       request.setAttribute("error", true);
       request.setAttribute("user", username);
       request.setAttribute("password", password);
       getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
       return;
      }
      
     
      AccountService account = new AccountService();
     User user = account.login(username, password);
      if(user == null)
      {
        request.setAttribute("error", true);
        request.setAttribute("user", username);
        request.setAttribute("password", password);
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
       return;
      }
      else{
         session.setAttribute("username", username);
         
         session.setAttribute("user", user);
         response.sendRedirect("home");
         return;
      }

    }



}

