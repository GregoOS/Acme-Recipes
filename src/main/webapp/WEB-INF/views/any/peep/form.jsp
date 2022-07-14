<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<acme:form>
	<acme:input-textbox code="any.peep.heading" path="heading"/>
	<acme:input-textbox code="any.peep.writer" path="writer"/>
	<acme:input-textbox code="any.peep.text" path="text"/>
	<acme:input-email code="any.peep.email" path="email"/>
	
	<jstl:if test="${command=='create'}">
		<acme:input-checkbox code="any.peep.confirmation" path="confirmation"/>
		<acme:submit code="any.peep.button.create" action="/any/peep/create"/>
	</jstl:if>
</acme:form>

