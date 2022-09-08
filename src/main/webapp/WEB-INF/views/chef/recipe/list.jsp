<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="chef.recipe.code" path="code" width="20%"/>
	<acme:list-column code="chef.recipe.heading" path="heading" width="70%"/>
	<acme:list-column code="chef.recipe.published" path="published" width="10%"/>
</acme:list>