<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="epicure.memorandum.sequencenumber" path="sequenceNumber" width="40%"/>
	<acme:list-column code="epicure.memorandum.creationtime" path="creationTime" width="30%"/>
	<acme:list-column code="epicure.memorandum.link" path="link" width="30%"/>
</acme:list>

<jstl:if test="${masterId != null}">
        <acme:button code="epicure.memorandum.button.create" action="/epicure/memorandum/create?masterId=${masterId}"/>
</jstl:if>
