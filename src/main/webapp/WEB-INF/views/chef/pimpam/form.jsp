<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
<jstl:if test="${command != 'create'}">
	<acme:input-textbox code="pimpam.code" path="code"/>
	<acme:input-textbox code="pimpam.title" path="title"/>
	<acme:input-textbox code="pimpam.description" path="description"/>
	<acme:input-moment code="pimpam.instantiationMoment" path="instantiationMoment"/>
	<acme:input-moment code="pimpam.startDate" path="startDate"/>
	<acme:input-moment code="pimpam.finishDate" path="finishDate"/>
	<acme:input-money code="pimpam.budget" path="budget"/>
	<acme:input-url code="pimpam.link" path="link" placeholder="pimpam.link"/>
  	<acme:input-textbox code="chef.pimpam.element.name" path="elementName"/> 
	<acme:input-textbox code="chef.name" path="chefName"/>
</jstl:if>

<jstl:if test="${command == 'create'}">
	<acme:input-textbox code="pimpam.code" path="code"/>
	<acme:input-textbox code="pimpam.title" path="title"/>
	<acme:input-textbox code="pimpam.description" path="description"/>
	<acme:input-moment code="pimpam.startDate" path="startDate"/>
	<acme:input-moment code="pimpam.finishDate" path="finishDate"/>
	<acme:input-money code="pimpam.budget" path="budget"/>
	<acme:input-url code="pimpam.link" path="link" placeholder="chimpum.link"/>
	
	<acme:input-select code="chef.pimpam.element.name" path="elementId">
		<jstl:forEach items="${elements}" var = "element">
			<acme:input-option code="${element.getName()}" value="${element.getId()}" selected="${element.getId() == elementId}"/>
		</jstl:forEach>
	</acme:input-select>
</jstl:if>

	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete')}">
			<acme:submit code="pimpam.button.update" action="/chef/pimpam/update"/>
			<acme:submit code="pimpam.button.delete" action="/chef/pimpam/delete"/>
		</jstl:when>
		<jstl:when test="${acme:anyOf(command, 'create')}">
			<acme:submit code="pimpam.button.create" action="/chef/pimpam/create"/>
		</jstl:when>	
	</jstl:choose>
 
</acme:form>

