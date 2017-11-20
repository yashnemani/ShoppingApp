<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Product Information</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="/ShoppingApp/css/gen.css" rel="stylesheet">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
</head>
<body>
<div id="main">
<%-- <jsp:include page="header.jsp"/> --%>
<c:set var="pid" value="${id}"/>
<h2 align="center"> Product Details</h2>
<div align="center">
<h3><span id="label">Name</span> <br><c:out value="${product.name}"></c:out> </h3> 
<h3><span id="label">Product</span> <br> <c:out value="${product.product}"></c:out>  </h3> 
<h3><span id="label">Make</span> <br><c:out value="${product.make}"></c:out> </h3> 
<h3><span id="label">Category</span> <br> <c:out value="${product.category}"></c:out></h3> 
<h3><span id="label">Quantity</span><br><c:out value="${product.qty}"></c:out></h3> 
<h3><span id="label">Price</span> <br><c:out value="${product.price}"></c:out></h3> 
<c:if test="${product.description!=''}">
<h3><span id="label">Info</span><br> <c:out value="${product.description}"></c:out></h3> 
</c:if>
<c:if test ="${role=='enduser'}">
<a id="add" class="out" >Add To Cart</a>
</c:if>
</div>
<c:if test ="${role=='admin'}"> <a class="out" href="productInfo?action=edit&&id=${product.id}"> Edit Product</a></c:if> 
<c:if test ="${role=='admin'}"> <a class="out" style="float:right;" href="productInfo?action=delete&&id=${product.id}"> Delete Product</a></c:if>

</div>
<script type="text/javascript">
var id = '${pid}';
$('#add').click(function(){
 $.get("addCart",{id:id,action:"add",qt:"1"},function(data){
		 $('#cartSize').html(data);
 }); 
});
</script>
</body>
</html>