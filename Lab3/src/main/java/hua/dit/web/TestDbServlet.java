package hua.dit.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import hua.dit.web.test.TestH2DB;
import hua.dit.web.test.TestMariaDB;

@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

    public TestDbServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
/*
			// For Maria DB (OPTIONAL)
			TestMariaDB.main(null);
*/			
			// For H2 DB
			TestH2DB.main(null);
		} catch (Throwable t) {
			throw new RuntimeException("Testing DB problem", t);
		}
		
		// Send back a response
		response.setContentType("text/html");
		response.getWriter().append("<a href=\"index.html\">Go back to Login page..</a>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
