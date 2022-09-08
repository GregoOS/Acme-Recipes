<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="chef.quantity.code" path="code" width="10%"/>
	<acme:list-column code="chef.quantity.type" path="type" width="20%"/>
	<acme:list-column code="chef.quantity.amount" path="amount" width="10%"/>
	<acme:list-column code="chef.quantity.name" path="name" width="60%"/>
	
</acme:list>
