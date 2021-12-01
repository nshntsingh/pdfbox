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
				<h1 class="text-center-mb-3">Update user Form Detail</h1>

				<form action="${pageContext.request.contextPath }/handle-user" method="post">
				
				
			<input type="hidden" value="${user.id}" name="id">

				
					<div class="form-group">
						<label for="firstname">first name</label> <input type="text"
							class="form-control" id="firstname" aria-describedby="emailHelp"
							name="firstname" value="${user.firstname}"
							placeholder="Enter the firstname">
					</div>
					
					
					<div class="form-group">
						<label for="lastname">last name</label> <input type="text"
							class="form-control" id="lastname" aria-describedby="emailHelp"
							name="lastname" value="${user.lastname}"
							placeholder="Enter the lastname">
					</div>

					<div class="form-group">
						<label for="email">Email</label>
						<textarea class="form-control" name="email"  id="email"
						  placeholder="Enter the email ">${user.email}
						 </textarea>
					</div>

					

					<div class="container text-center">
						<a href="${pageContext.request.contextPath}/"
							class="btn btn-outline-danger">Back</a>
						<button type="submit" class="btn btn-primary">Update</button>
					</div>

				</form>
			</div>
		</div>
	</div>
</body>
</html>