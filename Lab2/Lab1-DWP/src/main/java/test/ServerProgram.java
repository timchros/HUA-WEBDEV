package test;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.json.JSONArray;

/**
 * Servlet implementation class ServerProgram
 */
@WebServlet("/ServerProgram")
public class ServerProgram extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServerProgram() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Input
		final String hiddendata = request.getParameter("hiddendata");
		System.out.println("hiddendata: " + hiddendata);
		if (hiddendata == null) return;
		
		// Convert String to JSON .. Array
		JSONArray ja = new JSONArray(hiddendata);
		System.out.println("\nJSON:\n" + ja.toString(5));;
		
		response.setContentType("text/html");
		response.getWriter().append("Data Under process ... <br>Request Served at: ");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
