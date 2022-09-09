<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="delor.code" path="code" width="10%"/>
	<acme:list-column code="delor.title" path="title" width="10%"/>
	<acme:list-column code="delor.description" path="description" width="10%"/>
	<acme:list-column code="delor.budget" path="budget" width="10%"/>
</acme:list>

<acme:button code="delor.button.create" action="/chef/delor/create"/>