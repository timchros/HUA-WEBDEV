package test;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Servlet implementation class TestProgram
 */
@WebServlet("/TestProgram")
public class TestProgram extends HttpServlet {

	private static final String TAB = "\t";
	
	private static final long serialVersionUID = 1L;

    public TestProgram() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html"); // Check: "text/plain" and "text/html"
		PrintWriter pw = response.getWriter();
		pw.println("<html>");
		pw.println("<head>");
		pw.println(TAB + "<title>Form 1 process</title>");
		pw.println(TAB + "<style> h2 { color: red; } </style>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println(TAB + "<h2>Test Program - User Data Received</h2>");
		pw.println(TAB + "<ul>");
		Enumeration<String> paramsEnum = request.getParameterNames();
		while(paramsEnum.hasMoreElements()) {
			final String attrName = paramsEnum.nextElement();
			final String attrValue = request.getParameter(attrName);
			pw.println(TAB + TAB + "<li><strong>" + attrName + "</strong>:" + attrValue + "</li>");
		}
		pw.println(TAB + "</ul>");
		pw.println(TAB + "</body>");
		pw.println("</html>");
		pw.flush();
		pw.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
