<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.models.ShoppingApp.Product"%>
<%@ include file="header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>QuikShop Products</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="/ShoppingApp/css/gen.css" rel="stylesheet">
</head>
<body>
<div id="main">
<%-- <jsp:include page="header.jsp"/> --%>
<c:if test="${role=='enduser'}">
	<h2 align="center">
		Welcome! Buy the products you like
		<c:out value="${name}" />
	</h2>
</c:if>
<c:if test="${role=='admin'}">
	<h2 align="center">
		Welcome! Make necessary changes to the Inventory
		<c:out value="${name}" />
	</h2>
</c:if>
	<h2 align="center">Available Products <br> 
<form action="productInfo?action=name" method="post">
	<input type="text" id="search" name="search" required="required" placeholder="Search product names.."><button type="submit">Go</button></form>
	</h2>
	<div id="hint" align="center"></div>

	<table align="center">
		<tr>
			<th>Name</th>
			<th>Product</th>
			<th>Make</th>
			<th>Category</th>
			<c:if test="${role=='enduser'}">
			<th> Qty </th>
			<th>Add </th>
			</c:if>
		</tr>
		<tbody>
			<c:forEach items="${Products}" var="prod">
				<tr>
					<td><a href="productInfo?action=info&id=${prod.id}"><c:out
								value="${prod.name}" /> </a></td>
					<td><c:out value="${prod.product}" /></td>
					<td><c:out value="${prod.make}" /></td>
					<td><c:out value="${prod.category}" /></td>
					<c:if test="${role=='enduser'}">
							<td> <input type="number" name="qty" id="${prod.id}" value="1" min="1" max="10"> </td>
					<td><button onclick="add(${prod.id})" id="add"><i class="fa fa-shopping-cart" aria-hidden="true"></i></button></td>
				</c:if>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<c:if test="${role=='admin'}">
		<h2 align="center"><a href="EditProduct.jsp">Add Products</a></h2>
		<h2 align="center"><a href="shopping?action=checkout&&page=1">View Checkout Logs</a></h2>
	</c:if>
	</div>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script type="text/javascript">
function add(id){
	var qt = $("#"+id).val();
	console.log(qt);
 $.get("addCart",{id:id,action:"add",qt: qt},function(data){$('#cartSize').html(data);}); }

$('#search').keyup(function(){
    var q = this.value;
    if(q!="")
    $.getJSON("productInfo?action=search&&q="+q, successfn);
});
 function successfn(data){
	 var out = '<h4>Hints:</h4><p>';
	 for(var index in data){
		 out += "<a  href='productInfo?action=info&id="+data[index].id+"'>"+data[index].name+", ";
	 }
	 out+= "</p>";
     $('#hint').html(out);
 }
</script>
</body>
</html>