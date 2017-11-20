<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ page import='com.models.ShoppingApp.CheckLog'%>
<%@ include file="header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CheckOut Logs</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="/ShoppingApp/css/gen.css" rel="stylesheet">
</head>
<body>
<div id="main">
<h2 align="center">CheckOut User Logs <br>
<form action="shopping?action=name" method="post">
	<input type="text" id="search" name="search" required="required" placeholder="Search User.."><button type="submit">Go</button></form>
</h2>
<div id="hint" align="center"></div>
	<table align='center' >
		<tr>
			<th>User</th>
			<th>ProdId</th>
			<th>Qty</th>
			<th>DateTime</th>
		</tr>
		<tbody id='tab'>
			<c:forEach items='${checks}' var='check'>
				<tr>
					<td><c:out value='${check.user}' /></td>
					<td><c:out value='${check.prodId}' /></td>
					<td><c:out value='${check.qty}' /></td>
					<td><c:out value='${check.dateTime}' /></td>
				</tr>
			</c:forEach>
		</tbody>
		</table>
		<div align="center">
		<h4>Pages:</h4>
		<c:forEach begin="1" end="${count}" var="loop">
    <a class="pg" href="shopping?action=checkout&&page=${loop}"><c:out value="${loop}"/></a>
</c:forEach>
</div>
		</div>
		<script src='//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js'></script>
		<script type="text/javascript">
		$('#search').keyup(function(){
		    var q = this.value;
		    if(q!="")
		    $.getJSON("shopping?action=search&&q="+q, successfn);
		});
		 function successfn(data){
			 var out = '<h4>Hints:</h4><p>';
			 for(var index in data){
				 out += "<a  href='shopping?action=name&name="+data[index]+"'>"+data[index]+", ";
			 }
			 out+= "</p>";
		     $('#hint').html(out);
		 }
		</script>
</body>
</html>