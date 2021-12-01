<html>
<head>
<%@ include file="./base.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

</head>
<body>
	<div class="container mt-3">
		<div class="row">
			<div class="col-md-12">
				<h1 class="text-center mb-3">Welcome to user page</h1>
				<table class="table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">ID</th>
							<th scope="col">First Name</th>
							<th scope="col">Last Name</th>
							<th scope="col">Email</th>
							<th scope="col">createdate</th>
							<th scope="col">updatedate</th>

							<th scope="col">Action</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${users }" var="u">
						<tr>
							<th scope="row">${u.id}</th>
							<td>${u.firstname}</td>
							<td>${u.lastname}</td>
							<td>${u.email}</td>
							<td>${u.createdate}</td>
							<td>${u.updatedate}</td>
							
							<td>
							<a href="delete/${u.id}"><i class="fas fa-trash text-danger"></i></a>
							<a href="update/${u.id}"><i class="fas fa-pen-nib primary"></i></a>

							</td>
						</tr>
						</c:forEach>
						
					 
					</tbody>
				</table>

				
				<div class="container text-center">
				<a href="add-user" class=btn btn-outline-success>Add User</a>
				</div>
			      Add data<br><br>
			      <a href="org">Add Organization</a><br>
				  <a href="ct">Add Contribution type</a><br>
				  <a href="et">Add Expenditure type</a><br>
				  <a href="c">Add Contribution</a><br>
				  <a href="e">Add Expenditure</a><br><br>
				  
				  
				  complete Data in PDf<br>
				  <a href="newPdf">CompletePdf</a><br><br>

                   Generate pdf<br>
				<a href="contributionPdf">Contribution pdf</a><br>
	            <a href="expenditurePdf">Expenditure pdf</a><br><br>
	            
	              Generate excel<br>
	            <a href="contributionExcel">Contribution Excel</a><br>
	            <a href="expenditureExcel">Expenditure Excel</a> <br><br><br><br>
	            
	            
	            
	            
	            	
			</div>
		</div>
	</div>
</body>
</html>