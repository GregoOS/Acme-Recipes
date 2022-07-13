<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="chef.finedish.code" path="code" width="40%"/>
	<acme:list-column code="chef.finedish.status" path="status" width="30%"/>
	<acme:list-column code="chef.finedish.budget" path="budget" width="30%"/>
</acme:list>
