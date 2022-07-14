<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="any.chirp.heading" path="title" width="60%"/>
	<acme:list-column code="any.chirp.writer" path="author" width="20%"/>
	<acme:list-column code="any.chirp.moment" path="moment" width="20%"/>
	
</acme:list>

<acme:button code="any.peep.button.create" action="/any/peep/create"/>
