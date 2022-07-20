<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	
	<jstl:if test="${command != 'create' && !draft}">
		<acme:input-textbox code="epicure.finedish.status" path="status" readonly="true"/>		
	</jstl:if>
	
	<acme:input-textbox code="epicure.finedish.code" path="code" placeholder="AA:AAA-111"/>
	<acme:input-textarea code="epicure.finedish.request" path="request"/>
	<acme:input-money code="epicure.finedish.budget" path="budget"/>
	
	<jstl:if test="${command != 'create'}">
		<acme:input-moment code="epicure.finedish.creation-date" path="creationDate" readonly="true"/>	
	</jstl:if>

	<acme:input-moment code="epicure.finedish.startdate" path="startDate"/>
	<acme:input-moment code="epicure.finedish.enddate" path="endDate"/>
	<acme:input-url code="epicure.finedish.link" path="link"/>
	
	<jstl:if test="${command != 'create'}">
	<h2>
		<acme:message code="epicure.finedish.chefdata"/>
	</h2>
	<acme:input-textbox code="epicure.finedish.chef.name" path="chefName" readonly="true"/>
	<acme:input-textbox code="epicure.finedish.chef.surname" path="chefSurname" readonly="true"/>
	<acme:input-textbox code="epicure.finedish.chef.email" path="chefEmail" readonly="true"/>
	<acme:input-textbox code="epicure.finedish.chef.organisation" path="chefOrganisation" readonly="true"/>
	<acme:input-textbox code="epicure.finedish.chef.assertion" path="chefAssertion" readonly="true"/>
	<acme:input-url code="epicure.finedish.chef.link" path="chefLink" readonly="true"/>
	</jstl:if>
	
	<jstl:choose>	 
		<jstl:when test="${command == 'show' && !draftMode}">
			<acme:button code="epicure.finedish.memorandums" action="/epicure/memorandum/list-by-fine-dish?masterId=${masterId}"/>			
		</jstl:when>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish') && draft}">
			<acme:submit code="epicure.finedish.button.update" action="/epicure/fine-dish/update"/>
			<acme:submit code="epicure.finedish.button.delete" action="/epicure/fine-dish/delete"/>
			<acme:submit code="epicure.finedish.button.publish" action="/epicure/fine-dish/publish"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="epicure.finedish.button.create" action="/epicure/fine-dish/create?chefId=${chefId}"/>
		</jstl:when>		
	</jstl:choose>
</acme:form>
