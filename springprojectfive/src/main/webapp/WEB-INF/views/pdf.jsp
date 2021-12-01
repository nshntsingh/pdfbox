<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="./base.jsp"%>
</head>

<body>
	<div class="container m-3">
		<div class="row">
			<div class="col-md-6offset-md-3">
				<h1 class="text-center-mb-3">Fill the contribution details</h1>

				<form action="handle" name="myform"
					method="post">

	           
					

					<div class="form-group">
						<label for="organization">Select Organization</label> 
						<select
							class="form-control" id="organization" name="organization">
							<c:forEach var="organization" items="${organizations}">
								<option value="${organization.id}">${organization.name}</option>
							</c:forEach>
						</select>
					</div>


					
					
					
					<div class="form-group">
						<label for="startDate">startDate</label> <font color="red">*</font> <input
							type="date" class="form-control" id=startDate
							aria-describedby="emailHelp" name="startDate"
							 required>
					</div>
					
					
					<div class="form-group">
						<label for="endDate">endDate</label> <font color="red">*</font> <input
							type="date" class="form-control" id=endDate
							aria-describedby="emailHelp" name="endDate"
							 required>
					</div>
										

					<div class="container text-center">
						
						<button type="submit" class="btn btn-primary">submit</button>

					</div>

					
				</form>
			</div>
		</div>
	</div>
</body>
</html>