<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>

	<acme:input-textbox code="any.amount.recipe" path="recipeTitle"/>

	<acme:input-select code="any.amount.elements" path="elements">
		<jstl:forEach items="${elements}" var="element">
			<acme:input-option code="${element.code} | ${element.name} (${element.retailPrice.currency})" value="${element.code}"/>
		</jstl:forEach>
	</acme:input-select>

	<acme:input-integer code="any.amount.number" path="number"/>
	<acme:input-integer code="any.amount.unit" path="unit"/>
</acme:form>
