<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	<acme:input-textbox code="any.recipe.code" path="code"/>
	<acme:input-textbox code="any.recipe.heading" path="heading"/>
	<acme:input-textarea code="any.recipe.description" path="description"/>
	<acme:input-textarea code="any.recipe.preparationnotes" path="preparationNotes"/>
	<acme:input-url code="any.recipe.link" path="link"/>
	<acme:input-textbox code="any.recipe.retailprice" path="retailPrice"/>
	<acme:button code="any.recipe.button.elements" action="/any/quantity/list?recipeId=${id}"/>
</acme:form>
