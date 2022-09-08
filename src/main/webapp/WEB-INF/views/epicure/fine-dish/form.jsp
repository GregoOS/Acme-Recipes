<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	
	<jstl:if test="${!draft}">
		<acme:input-textbox code="epicure.finedish.status" path="status" readonly="true"/>		
	</jstl:if>
	
	<acme:input-textbox code="epicure.finedish.code" path="code" placeholder="AA:AAA-111"/>
	<acme:input-textarea code="epicure.finedish.request" path="request"/>
	<acme:input-money code="epicure.finedish.budget" path="budget"/>
	
	<acme:input-moment code="epicure.finedish.creation-date" path="creationDate" readonly="true"/>	

	<acme:input-moment code="epicure.finedish.startdate" path="startDate"/>
	<acme:input-moment code="epicure.finedish.enddate" path="endDate"/>
	<acme:input-url code="epicure.finedish.link" path="link"/>
	
	<acme:input-select code="epicure.finedish.chefs" path="chefs">

		<jstl:forEach items="${chefs}" var="chef">
			<acme:input-option code="${chef.userAccount.identity.name} ${chef.userAccount.identity.surname}" value="${chef.id}" selected="${chef.id.equals(selected.id)}"/>
		</jstl:forEach>
	</acme:input-select>
	
	<jstl:choose>	 
		<jstl:when test="${command == 'show' && !draft}">
			<acme:button code="epicure.finedish.memorandums" action="/epicure/memorandum/list-by-finedish?masterId=${masterId}"/>			
		</jstl:when>		
	</jstl:choose>
</acme:form>
