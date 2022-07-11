<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="any.amount.code" path="code" width="10%"/>
	<acme:list-column code="any.amount.name" path="name" width="30%"/>
	<acme:list-column code="any.amount.retailprice" path="retailPrice" width="10%"/>
	<acme:list-column code="any.amount.type" path="type" width="10%"/>
	<acme:list-column code="any.amount.recipe" path="recipeTitle" width="20%"/>
	<acme:list-column code="any.amount.number" path="number" width="10%"/>
	<acme:list-column code="any.amount.unit" path="unit" width="10%"/>
	
</acme:list>
