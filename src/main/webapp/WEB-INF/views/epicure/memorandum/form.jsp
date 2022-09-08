<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<acme:form>
	<acme:input-textbox code="epicure.memorandum.sequencenumber" path="sequenceNumber" readonly="true"/>
	
	<acme:input-moment code="epicure.memorandum.creationtime" path="creationTime" readonly="true"/>
	
	<acme:input-textarea code="epicure.memorandum.report" path="report"/>
	<acme:input-url code="epicure.memorandum.link" path="link"/>
	
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
</acme:form>