<html>
<head>
<%@ include file="./base.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

</head>
<body>
	<div class="container mt-3">
		<div class="row">
			<div class="col-md-12">
				<h1 class="text-center mb-3">Welcome to Expenditure type page</h1>
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
					<c:forEach items="${expenditureTypes }" var="ete">
						<tr>
							<th scope="row">${ete.id}</th>
							<td>${ete.name}</td>
							<td>${ete.createdate}</td>
							<td>${ete.updatedate}</td>
							
							<td>
							<a href="delete/${ete.id}"><i class="fas fa-trash text-danger"></i></a>
							<a href="update/${ete.id}"><i class="fas fa-pen-nib primary"></i></a>

							</td>
						</tr>
						</c:forEach>
						
					 
					</tbody>
				</table>

				
				<div class="container text-center">
				<a href="add-expenditureType" class=btn btn-outline-success>Add expediture type</a>
				
				
			</div>
		</div>
	</div>
</body>
</html>