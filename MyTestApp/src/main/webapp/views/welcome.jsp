<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
</head>
<body>

<font color='green'>${msg}</font>
<table align="center">
<%
    String email=request.getParameter("email");  
    session.setAttribute("email",email);
  %>
  <%
    String name=request.getParameter("name");  
    session.setAttribute("name",name);
  %>
            <tr>
                <td><h1>Welcome ${sessionScope.email }</h1></td>
                <tr/>
                <tr></tr>
                <tr></tr>
                 <td><h1>Login Success</h1></td>
                
            </tr>
            <tr>
            </tr>
            <tr>
            </tr>
            
            <tr>
            <td><a href="task">Create Task</a></td>
			<td><a href="viewTask">View Task</a></td>
			<tr>
                <td><a href="/">Logout</a>
                </td>
            </tr>
            </tr>
        </table>
</body>
</html>