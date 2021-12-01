<html>
<head>
<%@ include file="./base.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

</head>
<body>
	<div class="container mt-3">
		<div class="row">
			<div class="col-md-12">
				<h1 class="text-center mb-3">Welcome to contribution page</h1>
				<table class="table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">ID</th>
							<th scope="col">user</th>

							<th scope="col">organization</th>

							<th scope="col">amount</th>
							<th scope="col">contributedDate</th>

							<th scope="col">purposeofdisbursement</th>

							<th scope="col">contribution type</th>

							<th scope="col">createdate</th>
							<th scope="col">updatedate</th>

							<th scope="col">Action</th>
						</tr>
					</thead>
					<tbody>



					    <c:forEach items="${contributions }" var="con">
							<tr>	
						<th scope="row">${con.id}</th>
						
	                    <th>
						<c:forEach items="${users }" var="u1">
						
						<c:if test="${con.user==u1.id}">
                        <c:out value="${u1.firstname}"></c:out>
                        </c:if>
		
						</c:forEach>
						</th>
						
						<th>
						<c:forEach items="${organizations }" var="org1">
						
						<c:if test="${con.organization==org1.id}">
                        <c:out value="${org1.name}"></c:out>
                        </c:if>
		
						</c:forEach>
						</th>
		
						
						
						<th scope="row">${con.amount}</th>
						<th scope="row">${con.contributedDate}</th>
						<th scope="row">${con.purposeofdisbursement}</th>
						
						
						<th>
						<c:forEach items="${contributionTypes }" var="cont1">
						
						<c:if test="${con.contributionType==cont1.id}">
                        <c:out value="${cont1.name}"></c:out>
                        </c:if>
		
						</c:forEach>
						</th>

						<td>${con.createdate}</td>
						<td>${con.updatedate}</td>

						<td><a href="c/delete/${con.id}"><i class="fas fa-trash text-danger"></i></a> 
						<a href="c/update/${con.id}"><i class="fas fa-pen-nib primary"></i></a>

						</td>
						</tr>
						</c:forEach>


					</tbody>
				</table>

				<div class="container text-center">
					<a href="add-contribution" class=btn btn-outline-success>Add
						contribution</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>