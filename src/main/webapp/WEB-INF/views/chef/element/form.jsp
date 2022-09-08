<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="chef.element.name" path="name"/>
	<acme:input-textbox code="chef.element.code" path="code" placeholder="AA:AAA-111"/>
	<acme:input-textarea code="chef.element.description" path="description"/>
	<acme:input-money code="chef.element.retailprice" path="retailPrice"/>	
	<acme:input-textbox code="chef.element.unit" path="amountUnit"/>
	<acme:input-url code="chef.element.link" path="link"/>
	<acme:input-select code="chef.element.type" path="type">
		<acme:input-option code="chef.element.type.ingredient" value="INGREDIENT" selected="${type == 'INGREDIENT'}"/>
		<acme:input-option code="chef.element.type.utensil" value="UTENSIL" selected="${type == 'UTENSIL'}"/>
	</acme:input-select>
</acme:form>
