<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Bootstrap demo</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
	crossorigin="anonymous">

</head>
<body>

	<div class="container">
		<h3 class="pb-3 pt-3">Report Application</h3>

		<!-- 	<button class="btn btn-primary">Search</button> -->


		<form:form action="search" modelAttribute="search" method="POST">
			<table>
				<tr>
					<td>Plan Name:</td>
					<td><form:select path="planName">
							<form:option value="">-Select-</form:option>
							<form:options items="${names}" />
						</form:select></td>
					<td>Plan Status:</td>
					<td><form:select path="planStatus">
							<form:option value="">-Select-</form:option>
							<form:options items="${status}" />
						</form:select></td>

					<td>Gender:</td>
					<td><form:select path="gender">
							<form:option value="">-Select-</form:option>
							<form:option value="Male">Male</form:option>
							<form:option value="Female">Female</form:option>
						</form:select> <!--<form:radiobutton path="gender" value="Male"/>Male
						<form:radiobutton path="gender" value="Male"/>Female --></td>
				<tr>
					<td>Start Date:</td>
					<td><form:input path="startDate" type="date" /></td>
					<td>End Date:</td>
					<td><form:input path="endDate" type="date" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Search"
						class="btn btn-primary" /></td>

				</tr>

				</tr>

			</table>

		</form:form>
		<hr />
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>S.No</th>
					<th>Id</th>
					<th>Citizen Name</th>
					<th>Gender</th>
					<th>Plan Name</th>
					<th>Plan Status</th>
					<th>Start Date</th>
					<th>End Date</th>
					<th>Benefit Amount</th>
					<th>Denial Reason</th>
					<th>Terminated Date</th>
					<th>Terminated Reason</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${searches}" var="find" varStatus="index">
				<tr>
				<td>${index.count}</td>
				<td>${find.citizenId}</td>
				<td>${find.citizenName}</td>
				<td>${find.gender}</td>
				<td>${find.planName}</td>
				<td>${find.planStatus}</td>
				<td>${find.planStartDate}</td>
				<td>${find.planEndDate}</td>
				<td>${find.benefitAmount}</td>
				<td>${find.denialReason}</td>
				<td>${find.terminatedDate}</td>
				<td>${find.terminatedReason}</td>
				</tr>
				
				</c:forEach>

			</tbody>
		</table>



		<hr />
		Export : <a href="">Excel</a> <a href="">Pdf</a>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
		crossorigin="anonymous">
		
	</script>


</body>
</html>