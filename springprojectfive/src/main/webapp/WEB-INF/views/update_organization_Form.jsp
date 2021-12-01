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
				<h1 class="text-center-mb-3">Update organization Form Detail</h1>

				<form action="${pageContext.request.contextPath }/handle-organization" method="post">
				
				
			<input type="hidden" value="${organization.id}" name="id">

				
					<div class="form-group">
						<label for="name">name</label> <input type="text"
							class="form-control" id="name" aria-describedby="emailHelp"
							name="name" value="${organization.name}"
							placeholder="Enter the name">
					</div>
					

					<div class="container text-center">
						<a href="${pageContext.request.contextPath}/org"
							class="btn btn-outline-danger">Back</a>
						<button type="submit" class="btn btn-primary">Update</button>
					</div>

				</form>
			</div>
		</div>
	</div>
</body>
</html>