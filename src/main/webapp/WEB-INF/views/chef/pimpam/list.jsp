<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="pimpam.code" path="code" width="10%"/>
	<acme:list-column code="pimpam.title" path="title" width="10%"/>
	<acme:list-column code="pimpam.description" path="description" width="10%"/>
	<acme:list-column code="pimpam.budget" path="budget" width="10%"/>
</acme:list>

<acme:button code="pimpam.button.create" action="/chef/pimpam/create"/>