<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<acme:form readonly="${readonly}">
	<acme:input-textbox code="administrator.systemconfiguration.form.label.currency" path="currency"/>
	<acme:input-textbox code="administrator.systemconfiguration.form.label.acceptedcurrencies" path="acceptedCurrencies"/>
	<acme:input-textbox code="administrator.systemconfiguration.form.label.spamThreshold" path="spamThreshold"/>
	<acme:input-textbox code="administrator.systemconfiguration.form.label.spamTerms" path="spamTerms"/>
	
	<jstl:choose>	 
		<jstl:when test="${acme:anyOf(command, 'show, update')}">
			<acme:submit code="administrator.system-configuration.form.button.update" action="/administrator/system-configuration/update"/>
		</jstl:when>
	</jstl:choose>	
</acme:form>