<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="delor.code" path="code" width="10%"/>
	<acme:list-column code="delor.subject" path="subject" width="10%"/>
	<acme:list-column code="delor.explanation" path="explanation" width="10%"/>
	<acme:list-column code="delor.income" path="income" width="10%"/>
</acme:list>

<acme:button code="delor.button.create" action="/chef/delor/create"/>