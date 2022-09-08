<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="chef.element.code" path="code" width="20%"/>
	<acme:list-column code="chef.element.retailprice" path="retailPrice" width="10%"/>
	<acme:list-column code="chef.element.type" path="type" width="10%"/>
	<acme:list-column code="chef.element.published" path="published" width="10%"/>
	<acme:list-column code="chef.element.name" path="name" width="50%"/>
</acme:list>
