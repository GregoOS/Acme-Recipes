<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="chef.recipe.code" path="code" placeholder="AA:AAA-111"/>
	<acme:input-textbox code="chef.recipe.heading" path="heading"/>
	<acme:input-textarea code="chef.recipe.description" path="description"/>
	<acme:input-textarea code="chef.recipe.preparationnotes" path="preparationNotes"/>
	<acme:input-url code="chef.recipe.link" path="link"/>
		
	<jstl:if test="${!(command == 'create')}">
		<acme:input-money code="chef.recipe.retailprice" path="retailPrice" readonly="true"/>
	</jstl:if>	
	
	<jstl:choose>
		<jstl:when test="${command == 'show' && draft == false}">
			<acme:button code="chef.recipe.button.elements" action="/chef/quantity/list?recipeId=${id}"/>
		</jstl:when>	 
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish') && draft == true}">
			<acme:button code="chef.recipe.button.elements" action="/chef/quantity/list?recipeId=${id}"/>
			<acme:submit code="chef.recipe.button.update" action="/chef/recipe/update"/>
			<acme:submit code="chef.recipe.button.delete" action="/chef/recipe/delete"/>
			<acme:submit code="chef.recipe.button.publish" action="/chef/recipe/publish"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="chef.recipe.button.create" action="/chef/recipe/create"/>
		</jstl:when>		
	</jstl:choose>
</acme:form>