<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>View Task Details</h2>

	<a href="/"></a>
	<table border="1">
		<thead>
			<tr>
				<th>S.No</th>
				<th>Task Name</th>
				<th>Date</th>
				<th>Timing</th>
				
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${task}" var="task" varStatus="index">
				<tr>
					<td>${index.count}</td>
					<td>${task.name}</td>
					<td>${task.date}</td>
					<td>${task.time}</td>
					
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>