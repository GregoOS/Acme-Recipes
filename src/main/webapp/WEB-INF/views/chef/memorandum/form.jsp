<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<acme:form>
	<acme:input-textbox code="chef.memorandum.sequencenumber" path="sequenceNumber" readonly="true"/>
	
	<acme:input-moment code="chef.memorandum.creationtime" path="creationTime" readonly="true"/>
	<acme:input-textarea code="chef.memorandum.report" path="report"/>
	<acme:input-url code="chef.memorandum.link" path="link"/>
	
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
</acme:form>