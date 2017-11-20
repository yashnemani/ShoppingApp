<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <%@ include file="header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add/Edit product</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="/ShoppingApp/css/gen.css" rel="stylesheet">
</head>
<body>
<div id="main">
<%-- <jsp:include page="header.jsp"/> --%>
<c:if test="${role=='enduser'}"></c:if>
<h2 align="center">Change Inventory</h2>
<c:if test="${product!=null}">
<form name="change" action="productInfo" method="post">
<fieldset>
<label >Name</label><input type="text" name="name" value="${product.name}"><br>
<label for="product">product</label><input type="text" name="product" value="${product.product}"><br>
<label for="make">Make</label><input type="text" name="make" value="${product.make}"><br>
<label for="qty">Quantity</label><input type="text" name="qty" value="${product.qty}"><br>
<label for="category">Category</label><input type="text" name="category" value="${product.category}"><br>
<label for="price">Price</label><input type="text" name="price" value="${product.price}"><br>
<label>Info</label><textarea name="info"  rows="1" cols="70">${product.description}</textarea><br>
<input type="hidden" name="action" value="change">
<input type="hidden" name="id" value="${product.id}">
<input type="submit" value="Done" class="button">
</fieldset>
</form>
</c:if>
<c:if test="${product==null}" >
<form name="change" action="productInfo" method="post">
<fieldset>
<label for="name">Name</label><input type="text" name="name"><br>
<label for="product">product</label><input type="text" name="product"><br>
<label for="make">Make</label><input type="text" name="make"><br>
<label for="qty">Quantity</label><input type="text" name="qty"><br>
<label for="category">Category</label><input type="text" name="category"><br>
<label for="price">Price</label><input type="text" name="price"><br>
<label>Info</label><textarea name="info" rows="1" cols="70"></textarea><br>
<input type="hidden" name="action" value="add">
<input type="hidden" name="id" value="">
<input type="submit" value="Done" class="button">
</fieldset>
</form>
</c:if>
</div>
<script src='//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js'></script>
<script src='https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js'></script>
<script type="text/javascript">
$('document').ready(function(){
	
});
$(function() {
	  $("form[name='change']").validate({
	    rules: {
	      name: {
	    	  required : true,
	    	  minlength: 4
	      },
	      make: "required",
	      category: {
	    	  required : true,
	    	  minlength: 4
	      },
	      price: {
	    	  required : true,
	    	  number: true
	      },
	      product: {
	    	  required : true,
	    	  minlength: 4
	      },
	      qty: "number"
	    },
	    messages: {
	    	   name: {
	   	        required: "Please provide a name",
	   	        minlength: "must be at least 4 characters long"
	   	      },
	   	   product: {
	   	        required: "Please provide product information",
	   	        minlength: "must be at least 4 characters long"
	   	      },
	   	   category: {
	   	        required: "Please provide category",
	   	        minlength: "must be at least 4 characters long"
	   	      },
	     make: "Please enter make information",
	     price: {
	   	        required: "Please provide price",
	   	        number: "must be a number"
	   	      },
	   qty: "must be a number"
	    },
	    submitHandler: function(form) {
	      form.submit();
	    }
	  });
	});
</script>
</body>
</html>