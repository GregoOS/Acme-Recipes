<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<acme:form>
	<acme:input-textbox code="chef.memorandum.sequencenumber" path="sequenceNumber" readonly="true"/>
	
	<jstl:if test="${command != 'create'}">
		<acme:input-moment code="chef.memorandum.creationtime" path="creationTime" readonly="true"/>
	</jstl:if>
	
	<acme:input-textarea code="chef.memorandum.report" path="report"/>
	<acme:input-url code="chef.memorandum.link" path="link"/>
	
	<jstl:choose>
		<jstl:when test="${!command == 'create'}">
			<h2>
				<acme:message code="chef.memorandum.finedish"/>
			</h2>
			<acme:input-textbox code="chef.memorandum.finedish.code" path="fineDishCode"/>
			<acme:input-textbox code="chef.memorandum.finedish.status" path="fineDishStatus"/>
			<acme:input-textbox code="chef.memorandum.finedish.request" path="fineDishRequest"/>
			<acme:input-money code="chef.memorandum.finedish.budget" path="fineDishBudget"/>
			<acme:input-moment code="chef.memorandum.finedish.creationdate" path="fineDishCreationDate"/>
			<acme:input-moment code="chef.memorandum.finedish.startdate" path="fineDishStartDate"/>
			<acme:input-moment code="chef.memorandum.finedish.enddate" path="fineDishEndDate"/>
			<acme:input-url code="chef.memorandum.finedish.link" path="fineDishLink"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:input-checkbox code="chef.memorandum.confirmation" path="confirmation"/>
			<acme:submit code="chef.memorandum.button.create" action="/chef/memorandum/create?masterId=${masterId}"/>
		</jstl:when>
	</jstl:choose>
</acme:form>