<html>
<head>
<%@ include file="./base.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

</head>
<body>
	<div class="container mt-3">
		<div class="row">
			<div class="col-md-12">
				<h1 class="text-center mb-3">Welcome to Organization page</h1>
				<table class="table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">ID</th>
							<th scope="col">Name</th>
							<th scope="col">createdate</th>
							<th scope="col">updatedate</th>

							<th scope="col">Action</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${organizations }" var="ore">
						<tr>
							<th scope="row">${ore.id}</th>
							<td>${ore.name}</td>
							<td>${ore.createdate}</td>
							<td>${ore.updatedate}</td>
							
							<td>
							<a href="org/delete/${ore.id}"><i class="fas fa-trash text-danger"></i></a>
							<a href="org/update/${ore.id}"><i class="fas fa-pen-nib primary"></i></a>

							</td>
						</tr>
						</c:forEach>
						
					 
					</tbody>
				</table>

				
				<div class="container text-center">
				<a href="add-organization" class=btn btn-outline-success>Add organization</a>
				
				
				
				
				
				
			</div>
		</div>
	</div>
</body>
</html>