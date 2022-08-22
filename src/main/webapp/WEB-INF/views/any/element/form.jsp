<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<acme:form>
	<acme:input-textbox code="any.element.name" path="name"/>
	<acme:input-textbox code="any.element.code" path="code" placeholder="AA:AAA-111"/>
	<acme:input-textarea code="any.element.description" path="description"/>
	<acme:input-money code="any.element.price" path="retailPrice"/>
	<acme:input-textbox code="chef.element.unit" path="amountUnit"/>
	<acme:input-url code="any.element.link" path="link"/>
	<acme:input-select code="any.element.type" path="type">
		<acme:input-option code="any.element.type.ingredient" value="INGREDIENT" selected="${type == 'INGREDIENT'}"/>
		<acme:input-option code="any.element.type.utensil" value="UTENSIL" selected="${type == 'UTENSIL'}"/>
	</acme:input-select>
</acme:form>
