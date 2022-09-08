<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>

	<acme:input-textbox code="chef.quantity.recipe" path="recipe.heading" readonly="true"/>
	
	<acme:input-select code="chef.quantity.elements" path="elements">

		<jstl:forEach items="${elements}" var="element">
			<acme:input-option code="${element.code} | ${element.name} (${element.type})" value="${element.code}" selected="${element.code.equals(selected.code)}"/>
		</jstl:forEach>
	</acme:input-select>
	
	<acme:input-integer code="chef.quantity.amount" path="amount"/>

</acme:form>
