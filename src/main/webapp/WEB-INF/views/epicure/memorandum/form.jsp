<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<acme:form>
	<acme:input-textbox code="epicure.memorandum.sequencenumber" path="sequenceNumber" readonly="true"/>
	
	<jstl:if test="${command != 'create'}">
		<acme:input-moment code="epicure.memorandum.creationtime" path="creationTime" readonly="true"/>
	</jstl:if>
	
	<acme:input-textarea code="epicure.memorandum.report" path="report"/>
	<acme:input-url code="epicure.memorandum.link" path="link"/>
	
	<jstl:choose>
		<jstl:when test="${!command == 'create'}">
			<h2>
				<acme:message code="epicure.memorandum.finedish"/>
			</h2>
			<acme:input-textbox code="epicure.memorandum.finedish.code" path="fineDishCode"/>
			<acme:input-textbox code="epicure.memorandum.finedish.status" path="fineDishStatus"/>
			<acme:input-textbox code="epicure.memorandum.finedish.request" path="fineDishRequest"/>
			<acme:input-money code="epicure.memorandum.finedish.budget" path="fineDishBudget"/>
			<acme:input-moment code="epicure.memorandum.finedish.creationdate" path="fineDishCreationDate"/>
			<acme:input-moment code="epicure.memorandum.finedish.startdate" path="fineDishStartDate"/>
			<acme:input-moment code="epicure.memorandum.finedish.enddate" path="fineDishEndDate"/>
			<acme:input-url code="epicure.memorandum.finedish.link" path="fineDishLink"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:input-checkbox code="epicure.memorandum.confirmation" path="confirmation"/>
			<acme:submit code="epicure.memorandum.button.create" action="/epicure/memorandum/create?masterId=${masterId}"/>
		</jstl:when>
	</jstl:choose>
</acme:form>