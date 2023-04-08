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

	<h3>User Registration Form</h3>
	
	<font color='green'>${msg}</font>

	<form:form action="save" modelAttribute="user" method="POST">

		<table>
			<tr>
				<td>Name:</td>
				<td><form:input path="name" /></td>
				<form:errors path="name" cssClass="error"/></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><form:input path="email" /></td>
				<form:errors path="email" cssClass="error"/></td>
				
			</tr>
			
			<tr>
				<td>Password:</td>
				<td><form:input path="password" /></td>
				<form:errors path="password" cssClass="error"/></td>
			</tr>
			
			<tr>
				<td>Gender:</td>
				<td><form:radiobutton path="gender" value="male"/> Male 
				<form:radiobutton path="gender" value="female"/> Female</td>
				<form:errors path="gender" cssClass="error"/></td>
			</tr>

			<tr>
				<td>Phno:</td>
				<td><input type="number" name="phno" /></td>
				
				
			</tr>

			<tr>
				<td></td>
				<td><input type="submit" value="Save" /></td>
			</tr>

		</table>
	</form:form>
	
		<a href="logged">Login</a>
		<%-- <a href="viewTask">View Task</a>--%>
</body>
</html>