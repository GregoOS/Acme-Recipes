<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
<jstl:if test="${command != 'create'}">
	<acme:input-textbox code="delor.code" path="code"/>
	<acme:input-textbox code="delor.title" path="title"/>
	<acme:input-textbox code="delor.description" path="description"/>
	<acme:input-moment code="delor.instantiationMoment" path="instantiationMoment"/>
	<acme:input-moment code="delor.startDate" path="startDate"/>
	<acme:input-moment code="delor.finishDate" path="finishDate"/>
	<acme:input-money code="delor.budget" path="budget"/>
	<acme:input-url code="delor.link" path="link" placeholder="delor.link"/>
  	<acme:input-select code="chef.delor.element.name" path="element">
			<acme:input-option code="${element.getName()}" value="${element.getId()}" selected="${element.getId() == elementId}"/>
	</acme:input-select> 
</jstl:if>

<jstl:if test="${command == 'create'}">
	<acme:input-textbox code="delor.code" path="code"/>
	<acme:input-textbox code="delor.title" path="title"/>
	<acme:input-textbox code="delor.description" path="description"/>
	<acme:input-moment code="delor.startDate" path="startDate"/>
	<acme:input-moment code="delor.finishDate" path="finishDate"/>
	<acme:input-money code="delor.budget" path="budget"/>
	<acme:input-url code="delor.link" path="link" placeholder="chimpum.link"/>
	
	<acme:input-select code="chef.delor.element.name" path="elementId">
		<jstl:forEach items="${elements}" var = "element">
			<acme:input-option code="${element.getName()}" value="${element.getId()}" selected="${element.getId() == elementId}"/>
		</jstl:forEach>
	</acme:input-select>
</jstl:if>

	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete')}">
			<jstl:if test="${element.draft == true}">
				<acme:submit code="delor.button.update" action="/chef/delor/update"/>
				<acme:submit code="delor.button.delete" action="/chef/delor/delete"/>
			</jstl:if>
		</jstl:when>
		<jstl:when test="${acme:anyOf(command, 'create')}">
			<acme:submit code="delor.button.create" action="/chef/delor/create"/>
		</jstl:when>	
	</jstl:choose>
 
</acme:form>

