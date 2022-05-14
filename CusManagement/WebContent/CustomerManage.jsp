<%@page import="com.CustomerManage"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Managment</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.4.1.min.js"></script>
<script src="Components/CustomerManage.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Customer Managment</h1>

				<form id="formCustomerManage" name="formCustomerManage" method="post" action="CustomerManage.jsp">


					Customer Name: <input id="cusName" name="cusName" type="text"
						class="form-control form-control-sm"> 
						
						<br>Address: <input id="cusAddress" name="cusAddress" type="text"
						class="form-control form-control-sm"> 
						
						<br> NIC: <input id="cusNIC" name="cusNIC" type="text"
						class="form-control form-control-sm"> 
						
						<br> Email: <input id="cusEmail" name="cusEmail" type="text"
						class="form-control form-control-sm"> 
						
						<br> Phone Number: <input id="cusPno" name="cusPno" type="text"
						class="form-control form-control-sm"> 
						
						<br> <input
						id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> <input type="hidden"
						id="hidProjectIDSave" name="hidProjectIDSave" value="">
				</form>

				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>

				<br>
				<div id="divProjectGrid">
					<%
					CustomerManage projectObj = new CustomerManage();
						out.print(projectObj.readProject());
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
