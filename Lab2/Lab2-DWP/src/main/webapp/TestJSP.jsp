<%@page import="test.UserData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>

<!DOCTYPE html>
<html>
	<head> <meta charset="UTF-8"> <title>Test JSP</title> </head>
	<body>
		<%! Map<Integer, UserData> map = new HashMap<>(); %>
		<% 
		map.put(1, new UserData("John", "male", 25));
		map.put(2, new UserData("Maria", "female", 23));
		map.put(3, new UserData("Eleni", "female", 30));
		%>
		<h1>Table</h1>
		<table border="1">
			<tr><th>Name</th><th>Sex</th><th>Age</th></tr>
			<% for (UserData ud : map.values()) { %>
				<tr>
					<td><%=ud.getUsername()%></td>
					<td><%=ud.getSex()%></td>
					<td><%=ud.getAge()%></td>
				</tr>
			<% } %>
		</table>
	</body>
</html>