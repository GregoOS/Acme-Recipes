<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<acme:form>
	<acme:input-moment code="authenticated.bulletin.moment" path="moment"/>
	<acme:input-textbox code="authenticated.bulletin.heading" path="heading"/>
	<acme:input-textarea code="authenticated.bulletin.text" path="text"/>
	<acme:input-select code="authenticated.bulletin.critical" path="critical">
			<acme:input-option code="authenticated.bulletin.critical.true" value="true" selected="${critical}"/>
			<acme:input-option code="authenticated.bulletin.critical.false" value="false" selected="${!critical}"/>
	</acme:input-select>
	<acme:input-url code="authenticated.bulletin.link" path="link"/>
</acme:form>
