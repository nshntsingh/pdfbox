<html>
<head>
<%@ include file="./base.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

</head>
<body>
	<div class="container mt-3">
		<div class="row">
			<div class="col-md-12">
				<h1 class="text-center mb-3">Welcome to Expenditure page</h1>
				<table class="table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">ID</th>
							<th scope="col">user</th>
							
							<th scope="col">organization</th>
							
							<th scope="col">amount</th>
							<th scope="col">expenditureDate</th>
							
							<th scope="col">purposeofdisbursement</th>
							
							<th scope="col">expenditure type</th>
							
							<th scope="col">createdate</th>
							<th scope="col">updatedate</th>

							<th scope="col">Action</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${expenditures }" var="e">
						<tr>
						
						<th scope="row">${e.id}</th>
						

						<th>
						<c:forEach items="${users }" var="u1">
						
						<c:if test="${e.user==u1.id}">
                        <c:out value="${u1.firstname}"></c:out>
                        </c:if>
		
						</c:forEach>
						</th>
						
						
						<th>
						<c:forEach items="${organizations }" var="org1">
						
						<c:if test="${e.organization==org1.id}">
                        <c:out value="${org1.name}"></c:out>
                        </c:if>
		
						</c:forEach>
						</th>
						
						
						
					  	<th scope="row">${e.amount}</th>
						  <th scope="row">${e.expendituredate}</th>
						  <th scope="row">${e.purposeofdisbursement}</th>
						  
						 <th>
						<c:forEach items="${expenditureTypes }" var="ent1">
						<c:if test="${e.expenditureType==ent1.id}">
                        <c:out value="${ent1.name}"></c:out>
                        </c:if>
		
						</c:forEach>
						</th>
						 
						
							<td>${e.createdate}</td>
							<td>${e.updatedate}</td>
							
							<td>
							<a href="e/delete/${e.id}"><i class="fas fa-trash text-danger"></i></a>
							<a href="e/update/${e.id}"><i class="fas fa-pen-nib primary"></i></a>

							</td>
						</tr>
						</c:forEach>
						
					 
					</tbody>
				</table>

				
				<div class="container text-center">
				<a href="add-expenditure" class=btn btn-outline-success>Add expenditure</a>
				</div>
				
			</div>
		</div>
	</div>
</body>
</html>