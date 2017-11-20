<div class="head" >
<a href="productInfo?action=all" class="out" style="float:left;padding-right:20px;"><i id="home" class="fa fa-home" aria-hidden="true"></i>Home</a>
<a href="shopping?action=logout" class="out" style="float:right;padding-left:20px;"><i class="fa fa-sign-out" aria-hidden="true"></i>Log Out</a>
<c:if test="${role=='enduser'}">
<a class="out" href="addCart?action=cart" style="float:right;"><i class="fa fa-shopping-cart" aria-hidden="true"></i>Cart<span id="cartSize"><c:out value="${cartSize}"></c:out></span> </a>
</c:if>
<h1>QUIK <i class="fa fa-shopping-bag" aria-hidden="true"></i> SHOP</h1>
<h5>Welcome,<c:out value="${name}"></c:out></h5>
</div>