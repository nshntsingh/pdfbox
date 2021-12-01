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
				<h1 class="text-center-mb-3">Fill the  contribution type details</h1>

				<form action="handle-contributionType" name="myform"
					onsubmit="return validate1()" onsubmit="return ValidateEmail()"  
					method="post">

					<div class="form-group">
						<label for="name"> Name</label> <font color="red">*</font> <input
							type="text" class="form-control" id="name" 
							aria-describedby="emailHelp" name="name" placeholder="Enter name" pattern="[a-z]{1,15}"
		                    title="name should only contain lowercase letters. e.g. john"
							required>
					</div>
				
					<div class="container text-center">
						<a href="${pageContext.request.contextPath}/ct"
							class="btn btn-outline-danger">Back</a>
						<button type="submit" class="btn btn-primary">Add</button>
						
					</div>
				
				</form>
			</div>
		</div>
	</div>
</body>
</html>