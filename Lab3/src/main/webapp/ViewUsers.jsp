<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="hua.dit.web.User"%>
<%@page import="hua.dit.web.LabDb"%>
<%@page import="java.util.List"%>
<%
	/* We use both Sessions and Cookies for testing purposes */
	
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
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ALL Users</title>
		<!-- FILE with CSS RULES -->
        <link href="css/page.css" rel="stylesheet">
        <style type="text/css">
        	form { margin: 0px; padding:0px; border: 0px; background-color: white; }
        	th {
        		background-color: aliceblue;
        	}
        	th, td {
        		padding: 5px 10px;
        	}
        </style>
	</head>
	<body>
		<h1>Hello <%= sessionUsername %> ! ALL USERS</h1>
		<p>Note: 
			<b>session-username</b>: <%= sessionUsername %> , 
			<b>cookie-username</b>: <%= cookieUsername %>
		</p>
		<table>
			<tr> 
				<th>ID</th> 
				<th>Username</th> 
				<th>Password-Hash</th>
				<th>Action</th> 
			</tr>
		<%  
			final List<User> userList = LabDb.getUsers();
			for(User user: userList) { 
		%>
				<tr> 
					<td><%= user.getId() %></td>
					<td><%= user.getUsername() %></td>
					<td><%= user.getPasswordHash() %></td>
					<td>
						<form action="DeleteUserServlet">
							<input type="hidden" name="uid" value="<%=user.getId()%>">
							<input type="submit" value="Delete User">
						</form>
					</td>
				</tr>
		<% 
			} 
		%>
		</table>
		
		<a href="MainPageServlet">Go back to main menu.</a>
		
	</body>
</html>



