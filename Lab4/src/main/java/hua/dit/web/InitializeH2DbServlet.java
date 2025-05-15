package hua.dit.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import hua.dit.web.db.test.TestH2DB;

/**
 * Servlet implementation class InitializeDbServlet
 */
@WebServlet("/InitializeH2DbServlet")
public class InitializeH2DbServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    
	private static final String TAB = "\t";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			// Create H2 DB with a few records using the method developed in another Java Class
			TestH2DB.main(null);
			
			// Response - Set ContentType and Body
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.println("<html>");
			pw.println(TAB + "<head>");
			pw.println(TAB + TAB + "<title>H2 DB</title>");
			pw.println(TAB + TAB + "<link href=\"page.css\" rel=\"stylesheet\">");
			pw.println(TAB + "<head>");
			pw.println(TAB + "<body>");
			pw.println(TAB + TAB + "<h2>H2 DB status</h1>");
			pw.println(TAB + TAB + "<p>H2 DB successfully create ! Check server logs/console for more details...</p>");
			pw.println(TAB + TAB + "<p><a href=\"index.html\">Go back to Login page..</a></p>");
			pw.println(TAB + "</body>");
			pw.println("</html>");
			pw.flush();
			pw.close();
			
		} catch (Throwable t) {
			throw new RuntimeException("Testing DB problem", t);
		}
	}
		

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
