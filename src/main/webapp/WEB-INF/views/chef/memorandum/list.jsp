<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="chef.memorandum.sequencenumber" path="sequenceNumber" width="40%"/>
	<acme:list-column code="chef.memorandum.creationtime" path="creationTime" width="30%"/>
	<acme:list-column code="chef.memorandum.link" path="link" width="30%"/>
</acme:list>

<jstl:if test="${masterId != null}">
        <acme:button code="chef.memorandum.button.create" action="/chef/memorandum/create?masterId=${masterId}"/>
</jstl:if>

