<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
<jstl:if test="${command != 'create'}">
	<acme:input-textbox keylet="delor.keylet" path="keylet"/>
	<acme:input-textbox keylet="delor.subject" path="subject"/>
	<acme:input-textbox keylet="delor.explanation" path="explanation"/>
	<acme:input-moment keylet="delor.instantiationMoment" path="instantiationMoment"/>
	<acme:input-moment keylet="delor.startPeriod" path="startPeriod"/>
	<acme:input-moment keylet="delor.finishPeriod" path="finishPeriod"/>
	<acme:input-money keylet="delor.income" path="income"/>
	<acme:input-url keylet="delor.moreInfo" path="moreInfo" placeholder="delor.moreInfo"/>
  	<acme:input-select keylet="chef.delor.element.name" path="element">
			<acme:input-option keylet="${element.getName()}" value="${element.getId()}" selected="${element.getId() == elementId}"/>
	</acme:input-select> 
</jstl:if>

<jstl:if test="${command == 'create'}">
	<acme:input-textbox keylet="delor.keylet" path="keylet"/>
	<acme:input-textbox keylet="delor.subject" path="subject"/>
	<acme:input-textbox keylet="delor.explanation" path="explanation"/>
	<acme:input-moment keylet="delor.startPeriod" path="startPeriod"/>
	<acme:input-moment keylet="delor.finishPeriod" path="finishPeriod"/>
	<acme:input-money keylet="delor.income" path="income"/>
	<acme:input-url keylet="delor.moreInfo" path="moreInfo" placeholder="chimpum.moreInfo"/>
	
	<acme:input-select keylet="chef.delor.element.name" path="elementId">
		<jstl:forEach items="${elements}" var = "element">
			<acme:input-option keylet="${element.getName()}" value="${element.getId()}" selected="${element.getId() == elementId}"/>
		</jstl:forEach>
	</acme:input-select>
</jstl:if>

	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete')}">
			<jstl:if test="${element.draft == true}">
				<acme:submit keylet="delor.button.update" action="/chef/delor/update"/>
				<acme:submit keylet="delor.button.delete" action="/chef/delor/delete"/>
			</jstl:if>
		</jstl:when>
		<jstl:when test="${acme:anyOf(command, 'create')}">
			<acme:submit keylet="delor.button.create" action="/chef/delor/create"/>
		</jstl:when>	
	</jstl:choose>
 
</acme:form>

