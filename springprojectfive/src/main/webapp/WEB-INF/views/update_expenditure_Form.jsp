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
				<h1 class="text-center-mb-3">Fill the  expenditure details</h1>

		<form action="${pageContext.request.contextPath }/handle-expenditure" method="post">
				
				
		<input type="hidden" value="${expenditure.id}" name="id">
				

					<div class="form-group">
						<label for="user">user Name</label> <select class="form-control"
							id="user" name="user">
							<c:forEach var="u" items="${users}">
								<option value="${u.id}"
								  ${u.id == expenditure.user ? 'selected="u.firstname"' : ''}>${u.firstname}</option>		  
							</c:forEach>

						</select>
					</div>

					<div class="form-group">
						<label for="organization">Select Organization</label> <select
							class="form-control" id="organization" name="organization">
							<c:forEach var="organization" items="${organizations}">
								<option value="${organization.id}"
								${organization.id == expenditure.organization ? 'selected="organization.name"' : ''}>${organization.name}</option>

							</c:forEach>
						</select>
					</div>
					
					
                   <div class="form-group">
						<label for="amount">amount</label> <font color="red">*</font> <input
							type="text" class="form-control" id="amount" 
							aria-describedby="emailHelp" name="amount" placeholder="Enter amount" 
		                   value="${expenditure.amount}"
							required>
					</div>
					
					<div class="form-group">
						<label for="expendituredate">expendituredate</label> <font color="red">*</font> <input
							type="date" class="form-control" id="expendituredate"
							aria-describedby="emailHelp" name="expendituredate" value="${expenditure.expendituredate }"
							 required>
					</div>
					
                   <div class="form-group">
						<label for="purposeofdisbursement">purposeofdisbursement</label> <font color="red">*</font> <input
							type="text" class="form-control" id="purposeofdisbursement" 
							aria-describedby="emailHelp" name="purposeofdisbursement" placeholder="Enter purposeofdisbursement" 
		                   value="${expenditure.purposeofdisbursement}"
							required>
					</div>
					
					
				
					
					
					<div class="form-group">
						<label for="expenditureType">expenditureType</label> <select
							class="form-control" id="expenditureType"
							name="expenditureType">
							<c:forEach var="ete" items="${expenditureTypes}">
								<option value="${ete.id}"
								${ete.id == expenditure.expenditureType ? 'selected="ete.name"' : ''}>${ete.name}</option>

							</c:forEach>
						</select>
					</div>
					
						


					<div class="container text-center">
						<a href="${pageContext.request.contextPath}/e"
							class="btn btn-outline-danger">Back</a>
						<button type="submit" class="btn btn-primary">Add</button>
						
					</div> 
        </form>
				
			</div>
		</div>
	</div>
</body>
</html>