<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register to QuikShop</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="/ShoppingApp/css/gen.css" rel="stylesheet">
</head>
<body>
<div id="main">
<h2 align="center">Welcome to QuikShop, Sign Up to access the products you desire!</h2>
<%-- <jsp:include page="header.jsp"/> --%>
<form name="register" align="center" action="shopping" method="post">
<fieldset>
<label for="name"> Name</label>
<input type="text" name="name"> <br>
<label for="user"> Username</label>
<input type="text" name="user"> <br>
<label for="pass"> Password</label>
<input id="pass" type="password" name="pass"> <br>
<label for="pass2"> Repeat Password</label>
<input type="password" name="pass2"> <br>
<input type="hidden" name="action" value="register">
<input type="submit" value="Register" class="button">
</fieldset>
</form>
<%
if(request.getAttribute("value")=="failed"){
	out.println("Registration Failed! Try logging in if User already registered");
}
%>
</div>
<script src='//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js'></script>
<script src='https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js'></script>
<script>
$('document').ready(function(){
	
});
$(function() {
	  $("form[name='register']").validate({
	    rules: {
	      name: "required",
	      user: "required",
	      pass: {
	        required: true,
	        minlength: 5
	      },
	      pass2: {
		        required: true,
		        minlength: 5,
		        equalTo: "#pass"
		      }
	    },
	    messages: {
	      name: "Please enter your name",
	      user: "Please enter your username",
	      pass: {
	        required: "Please provide a password",
	        minlength: "Your password must be at least 5 characters long"
	      },
	    pass2: {
	        required: "Please provide password again",
	        minlength: "Your password must be at least 5 characters long",
	        equalTo: "Passwords do not match"
	      }
	    },
	    submitHandler: function(form) {
	      form.submit();
	    }
	  });
	});
</script>
</body>
</html>