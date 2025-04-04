package test;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	// Data 
	private  final Map<Integer, UserData> map = new HashMap<>();	
    public TestServlet() {
        super();
        map.put(1, new UserData("John", "male", 25));
		map.put(2, new UserData("Maria", "female", 23));
		map.put(3, new UserData("Eleni", "female", 30));
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Input
		final String uidstr = request.getParameter("uid");
		final Integer uid = Integer.parseInt(uidstr);
		System.out.println("uid: " + uid);
		
		// Process
		final UserData ud = map.get(uid);
		
		// Output
		response.setContentType("text/html");
		final PrintWriter pw = response.getWriter();
		pw.println("<h1>Message from Server ...</h1>");
		pw.println("<p>Presentation of data - Version 1:</p>");
		pw.println("" + ud);
		if (ud != null) {
			pw.println("<p>Presentation of data - Version 2:</p>");
			pw.println("<ul>");
			pw.println("<li>" + ud.getUsername() + "</li>");
			pw.println("<li>" + ud.getSex() + "</li>");
			pw.println("<li>" + ud.getAge() + "</li>");
			pw.println("</ul>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

