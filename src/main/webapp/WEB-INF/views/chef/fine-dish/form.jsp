<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:input-select code="chef.finedish.status" path="status">
		<acme:input-option code="chef.finedish.proposed" value="PROPOSED" selected="${status == 'PROPOSED'}"/>
		<acme:input-option code="chef.finedish.accepted" value="ACCEPTED" selected="${status == 'ACCEPTED'}"/>
		<acme:input-option code="chef.finedish.denied" value="DENIED" selected="${status == 'DENIED'}"/>
	</acme:input-select>

	<acme:input-textbox code="chef.finedish.code" path="code" placeholder="AA:AAA-111"/>
	<acme:input-textarea code="chef.finedish.request" path="request"/>
	<acme:input-money code="chef.finedish.budget" path="budget"/>
	<acme:input-moment code="chef.finedish.creationdate" path="creationDate"/>
	<acme:input-moment code="chef.finedish.startdate" path="startDate"/>
	<acme:input-moment code="chef.finedish.enddate" path="endDate"/>
	<acme:input-url code="chef.finedish.link" path="link"/>
     <h2>
	    <acme:message code="chef.finedish.epicuredata"/>
    </h2>
	<acme:input-textbox code="chef.finedish.epicure.name" path="epicureName"/>
	<acme:input-textbox code="chef.finedish.epicure.surname" path="epicureSurname"/>
	<acme:input-textbox code="chef.finedish.epicure.email" path="epicureEmail"/>
	<acme:input-textbox code="chef.finedish.epicure.organisation" path="epicureOrganisation"/>
	<acme:input-textbox code="chef.finedish.epicure.assertion" path="epicureAssertion"/>
	<acme:input-textbox code="chef.finedish.epicure.link" path="epicureLink"/>

	<jstl:if test="${status == 'PROPOSED'}">
		<acme:submit code="chef.finedish.button.accept" action="/chef/fine-dish/accept"/>
		<acme:submit code="chef.finedish.button.deny" action="/chef/fine-dish/deny"/>
	</jstl:if>
	<jstl:if test="${status == 'ACCEPTED'}">
		<acme:button code="chef.finedish.memorandums" action="/chef/memorandum/list-by-finedish?masterId=${id}"/>
	</jstl:if>
</acme:form>




