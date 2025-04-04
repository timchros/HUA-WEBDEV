package test;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class UnpdatePassword
 */
@WebServlet("/UnpdatePassword")
public class UnpdatePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UnpdatePassword() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// Input: User Data
		final String username = request.getParameter("newpass1");
		final String oldpass = request.getParameter("oldpass");
		final String newpass1 = request.getParameter("newpass1");
		final String newpass2 = request.getParameter("newpass2");
		
		// Process
		
		// (a) Ensure that newpass1 == newpass2
		if (!newpass1.equals(newpass2)) {
			response.setContentType("text/plain");
			response.getWriter().println("New password problem...");
			return;
		}
		
		// (b) Update password
		// ...
		
		// Output
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		pw.println("<h4>Passord Update Response</h4>");
		pw.println("<p>Password of user <strong>" + username + "</strong> successfully updated !</p>");
		pw.println("<p>OLD password: " + oldpass + "</p>");
		pw.println("<p>NEW password: " + newpass1 + "</p>");
		pw.close();	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
