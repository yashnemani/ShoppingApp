<%@ page language='java' contentType='text/html; charset=ISO-8859-1'
    pageEncoding='ISO-8859-1'%>
    <%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ page import='com.models.ShoppingApp.Product'%>
<%@ include file="header.jsp"%>
<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>
<html>
<head>
<meta http-equiv='Content-Type' content='text/html; charset=ISO-8859-1'>
<title>Cart Products</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="/ShoppingApp/css/gen.css" rel="stylesheet">
</head>
<body>
<div id="main">
<%-- <jsp:include page="header.jsp"/> --%>
	<h2 align='center'>
		You have the following products in your cart,
		<c:out value='${name}' />
	</h2>
	<h2 align='center'>Cart Products</h2>
	<table align='center' >
		<tr>
			<th>Name</th>
			<th>Product</th>
			<th>Make</th>
			<th>Category</th>
			<th>Qty</th>
			<th>Del</th>
			<th>Add </th>
		</tr>
		<tbody id='tab'>
			<c:forEach items='${products}' var='prod'>
				<tr>
					<td><c:out value='${prod.name}' /></td>
					<td><c:out value='${prod.product}' /></td>
					<td><c:out value='${prod.make}' /></td>
					<td><c:out value='${prod.category}' /></td>
					<td><input type="number"  id="${prod.id}" value="${prod.qty}" min="1" max="${prod.qty}"></td>
					<td><button onclick='remove(${prod.id})'><i class="fa fa-times" aria-hidden="true"></i></button></td>
				<td><button onclick="add(${prod.id})" id="add"><i class="fa fa-shopping-cart" aria-hidden="true"></i></button></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a class="out" href="addCart?action=checkout" style="float:right;">Checkout</a>
	<a class="out" href="productInfo?action=all" >Continue Shopping</a>
	</div>
	<script src='//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js'></script>
	<script type='text/javascript'>
	function add(id){
		var qt = $("#"+id).val();
	 $.get("addCart",{id:id,action:"add",qt: 1},function(data){
		 size(id);
		 var q = Number(qt);
	 $("#"+id).val(q+1);
	 }); }
	function size(id){
		 $.get("addCart",{id:id,action:"size"},function(data){$('#cartSize').html(data);}); }
var action = 'remove';
function remove(id){
	var qt = $("#"+id).val();
	 $.getJSON('addCart',
			 {id:id,action:action, qt:qt},
			 function(data){
		 var out = '';
		 for(var index in data){
			out += '<tr><td>'+data[index].name+'</td><td>'+data[index].product+'</td><td>'+data[index].make+'</td><td>'+data[index].category+
			'</td><td><input type="number"  id='+data[index].id+' value='+data[index].qty+
			' min="1" max='+data[index].qty+'></td><td><button onclick="remove('+data[index].id+
					')"><i class="fa fa-times" aria-hidden="true"></i></button></td><td><button onclick="add('+data[index].id+
							')" id="add"><i class="fa fa-shopping-cart" aria-hidden="true"></i></button></td></tr>';
			}
		 size(id);
		 $('#tab').html(out);
	 }); 
}
</script>
</body>
</html>