<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import = "java.io.*,java.util.*" %>
<%@ page import = "javax.servlet.*,java.text.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#datepicker" ).datepicker();
  } );
  </script>
<title>Insert title here</title>
</head>
<body>
	<form:form action="/tasksave" modelAttribute="task" method="POST">
	<%
    String email=request.getParameter("email");  
    session.setAttribute("Email",email);
  %>
		<table>
		<tr>
            	<td><h1>TODO List</h1></td>
            	<font color='green'>${msg}</font>
            <tr/>
			<tr>
				
				<td>Name:<form:input path="name" /></td>
			</tr>
			<tr>
				
			<%-- 	<td> <%
         Date dNow = new Date( );
         SimpleDateFormat ft = 
         new SimpleDateFormat ("yyyy/MM/dd");
         out.print(ft.format(dNow));
      %></td>--%>
      <tr>
			<tr>
			<td>Date:<input  type="date"  pattern="yyyy-MM-dd" name="date"><br /><br/>
      		 Time:<input type="time"   name="time"><br /></td>
			</tr>	
		<%-- 	<td>Date:<input type="text" id="datepicker"></td>	--%>
			<tr>
				<td></td>
				<td><input type="submit" value="Save" /></td>
			</tr>	
				
			</tr>
			<tr><td><a href="logged">Back</a></td>
			</tr>
		</table>
	</form:form>
</body>
</html>