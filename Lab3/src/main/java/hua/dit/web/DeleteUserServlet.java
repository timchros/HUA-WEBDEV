package hua.dit.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public DeleteUserServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// ** Ensure User is valid (using sessions/cookies) ** 
		
		// Check Using Sessions
		final HttpSession httpSession = request.getSession();
		final Object sessionUsername = 
			(httpSession != null) 
				? httpSession.getAttribute("session-username") 
				: null;
		if (sessionUsername == null) {
			response.sendRedirect("index.html"); 
			return;
		}
		
		// Check Using Cookies
		String cookieUsername = null;
		final Cookie[] cookies = request.getCookies(); 
		if (cookies != null) {
			// Find Cookie with name "cookie-username"
			for ( int i=0; i<cookies.length; i++) {
				Cookie cookie = cookies[i];
				if ("cookie-username".equals(cookie.getName())) 
					cookieUsername = cookie.getValue();
			}
		}
		if (cookieUsername == null) {
			response.sendRedirect("index.html"); 
			return;
		}
		
		// Request - Get User Data
		final String uid = request.getParameter("uid");
				
		// Interaction with DB - Delete User with the given id
		LabDb.deleteUser(Integer.parseInt(uid));
		
		// Forward Request to JSP page
		request.getRequestDispatcher("/ViewUsers.jsp").forward(request, response);
				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
