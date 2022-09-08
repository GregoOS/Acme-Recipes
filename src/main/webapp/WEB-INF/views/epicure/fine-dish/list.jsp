<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="epicure.finedish.code" path="code" width="40%"/>
	<acme:list-column code="epicure.finedish.status" path="status" width="30%"/>
	<acme:list-column code="epicure.finedish.budget" path="budget" width="30%"/>
</acme:list>