<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login To QuikShop</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="/ShoppingApp/css/gen.css" rel="stylesheet">
</head>
<body>
<div id="main">
<%-- <jsp:include page="header.jsp"/> --%>
<h2 align="center">Welcome to QuikShop, Sign In to access the products you desire!</h2>
<form  name="login" action="shopping" method="post">
<c:if test="${value=='failed'}"><h5 class="msg">Username/Password is wrong!</h5></c:if>
<c:if test="${value=='register'}"><h5 class="msg">Successfully Registered! Go Ahead and Login</h5></c:if>
<c:if test="${value=='logout'}"><h5 class="msg">Session logged out! Log In to continue shopping</h5></c:if>
<fieldset>
<label for="user"> Username</label>
<input type="text" name="user"> <br>
<label for="pass"> Password</label>
<input type="password" name="pass"> <br>
<input type="hidden" name="action" value="login">
<input type="submit" value="Sign In" class="button">
<p> <a href="register.jsp" target="_self">Register</a> here if you haven't already </p>
</fieldset>
</form>
</div>
<script src='//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js'></script>
<script src='https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js'></script>
<script>
$('document').ready(function(){
	$(function() {
		  $("form[name='login']").validate({
		    rules: {
		      user: "required",
		      pass: {
		        required: true,
		        minlength: 5
		      }
		    },
		    messages: {
		      user: "Please enter username",
		      pass: {
		        required: "Please provide a password",
		        minlength: "Your password must be at least 5 characters long"
		      }
		    },
		    submitHandler: function(form) {
		      form.submit();
		    }
		  });
		});
});
</script>
</body>
</html>