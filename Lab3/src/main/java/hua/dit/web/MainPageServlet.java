package hua.dit.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/MainPageServlet")
public class MainPageServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	private static final String TAB = "\t";
	
    public MainPageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// ** Check if user already logged in (using sessions/cookies) ** 
		
		/* We use both Sessions and Cookies for testing purposes */
		
		// Check Using Sessions
		final HttpSession httpSession = request.getSession();
		final Object sessionUsername = 
			(httpSession != null) 
				? httpSession.getAttribute("session-username") 
				: null;
		
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
		
		// IF there is no information in session/cookies then check DB ...
		if (sessionUsername == null && cookieUsername == null) {
		
			// Request - Get User Data
			final String username = request.getParameter("username");
			final String password = request.getParameter("password");
			
			// Interaction with DB - Check User Data
			final String password_hash = Util.getHash256(password);
			final boolean isUserValid = LabDb.isUserValid(username, password_hash);
			System.out.println(" * isUserValid: " + isUserValid);
			if (!isUserValid) {
				response.sendRedirect("index.html");
				return;
			}
			
			// We store data in both Session and Cookies for testing purposes
			if (isUserValid) {
				// Storing User Data to Cookie(s)
				response.addCookie(new Cookie("cookie-username", username));
				// Storing User Data to Session(s)
				request.getSession().setAttribute("session-username", username);
			}
		
		}
		
		// At this point, the information about user is stored in sessions/cookies
		final String theUser = "" + request.getSession().getAttribute("session-username");
		
		// Response - Set ContentType and Body
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		pw.println("<html>");
		pw.println(TAB + "<head>");
		pw.println(TAB + TAB + "<title>Main-Page</title>");
		pw.println(TAB + TAB + "<link href=\"css/page.css\" rel=\"stylesheet\">");
		pw.println(TAB + "<head>");
		pw.println(TAB + "<body>");
		pw.println(TAB + TAB + "<a href=\"LogoutServlet\">Logout</a>");
		pw.println(TAB + TAB + "<h1>Main Page</h1>");
		pw.println(TAB + TAB + "<p>Hello user with username:<strong>" + theUser + "</strong>.</p>");
		pw.println(TAB + TAB + "<p><a href=\"ViewUsers.jsp\">View ALL Users</a></p>");
		pw.println(TAB + "</body>");
		pw.println("</html>");
		pw.flush();
		pw.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
