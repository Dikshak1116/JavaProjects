<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    
   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h3>User Login Form</h3>
	
	<font color='green'>${msg}</font>

	<form:form action="/login" modelAttribute="user" method="POST">

		<table>
			
			<tr>
				<td>Email:</td>
				<td><form:input path="email" /></td>
			</tr>
			
			<tr>
				<td>Password:</td>
				<td><form:input path="password" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Login" /></td>
			</tr>

		</table>
	</form:form>
	<%-- <a href="logged">Create Task</a>
	<a href="viewTask">View Task</a>--%>
	<a href="/">Back</a>
</body>
</html>