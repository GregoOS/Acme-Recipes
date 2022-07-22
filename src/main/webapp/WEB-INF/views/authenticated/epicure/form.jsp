<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="authenticated.epicure.organisation" path="organisation"/>
	<acme:input-textbox code="authenticated.epicure.assertion" path="assertion"/>
		<acme:input-textbox code="authenticated.epicure.link" path="link"/>

	<acme:submit test="${command == 'create'}" code="authenticated.epicure.button.create" action="/authenticated/epicure/create"/>
	<acme:submit test="${command == 'update'}" code="authenticated.epicure.button.update" action="/authenticated/epicure/update"/>
</acme:form>