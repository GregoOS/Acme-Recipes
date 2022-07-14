<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<acme:form>
	<acme:input-textbox code="authenticated.bulletin.heading" path="heading"/>
	<acme:input-textarea code="authenticated.bulletin.text" path="text"/>
	<acme:input-checkbox code="authenticated.bulletin.critical" path="critical"/>
	<acme:input-url code="authenticated.bulletin.link" path="link"/>
	<acme:input-checkbox code="administrator.bulletin.confirmation" path="confirmation"/>
	<acme:submit code="administrator.bulletin.button.create" action="/administrator/announcement/create"/>
</acme:form>