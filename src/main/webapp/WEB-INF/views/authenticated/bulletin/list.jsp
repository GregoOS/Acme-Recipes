<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.bulletin.heading" path="heading" width="60%"/>	
	<acme:list-column code="authenticated.bulletin.moment" path="moment" width="30%"/>
	<acme:list-column code="authenticated.bulletin.critical" path="critical" width="10%"/>
</acme:list>
