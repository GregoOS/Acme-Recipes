<%@page language="java" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<h2>
	<acme:message code="administrator.admin-dashboard.form.title"/>
</h2>
<div>
	<h2>
		<acme:message code="administrator.admin-dashboard.form.ingredients"/>
	</h2>
	<table class="table table-sm">
		<tr>
			<th scope="row">
				<acme:message code="administrator.dashboard.form.total"/>
			</th>
			<td>
				<jstl:out value="${totalNumberIngredients}"></jstl:out>
			</td>
		</tr>
		<tr>
			<th scope="row">
				<acme:message code="administrator.dashboard.form.average"/>
			</th>
			<td>
				<jstl:forEach var="avg" items="${averagePriceIngredients.entrySet()}">
					<p>
						<jstl:out value="${avg.getValue()}"/>
						<jstl:out value="${avg.getKey()}"/>
					</p>
				</jstl:forEach>
			</td>
		</tr>
		<tr>
			<th scope="row">
				<acme:message code="administrator.dashboard.form.deviation"/>
			</th>
			<td>
				<jstl:forEach var="stddev" items="${deviationPriceIngredients.entrySet()}">
					<p>
						<jstl:out value="${stddev.getValue()}"/>
						<jstl:out value="${stddev.getKey()}"/>
					</p>
				</jstl:forEach>
			</td>
		</tr>
		<tr>
			<th scope="row">
				<acme:message code="administrator.dashboard.form.minimum"/>
			</th>
			<td>
				<jstl:forEach var="min" items="${minimumPriceIngredients.entrySet()}">
					<p>
						<jstl:out value="${min.getValue()}"/>
						<jstl:out value="${min.getKey()}"/>
					</p>
				</jstl:forEach>
			</td>
		</tr>
		<tr>
			<th scope="row">
				<acme:message code="administrator.dashboard.form.maximum"/>
			</th>
			<td>
				<jstl:forEach var="max" items="${maximumPriceIngredients.entrySet()}">
					<p>
						<jstl:out value="${max.getValue()}"/>
						<jstl:out value="${max.getKey()}"/>
					</p>
				</jstl:forEach>
			</td>
		</tr>
	</table>
	<h2>
		<acme:message code="administrator.admin-dashboard.form.utensils"/>
	</h2>
	<table class="table table-sm">
		<tr>
			<th scope="row">
				<acme:message code="administrator.dashboard.form.total"/>
			</th>
			<td>
				<jstl:out value="${totalNumberKitchenUtensils}"></jstl:out>
			</td>
		</tr>
		<tr>
			<th scope="row">
				<acme:message code="administrator.dashboard.form.average"/>
			</th>
			<td>
				<jstl:forEach var="avg" items="${averagePriceKitchenUtensils.entrySet()}">
					<p>
						<jstl:out value="${avg.getValue()}"/>
						<jstl:out value="${avg.getKey()}"/>
					</p>
				</jstl:forEach>
			</td>
		</tr>
		<tr>
			<th scope="row">
				<acme:message code="administrator.dashboard.form.deviation"/>
			</th>
			<td>
				<jstl:forEach var="stddev" items="${deviationPriceKitchenUtensils.entrySet()}">
					<p>
						<jstl:out value="${stddev.getValue()}"/>
						<jstl:out value="${stddev.getKey()}"/>
					</p>
				</jstl:forEach>
			</td>
		</tr>
		<tr>
			<th scope="row">
				<acme:message code="administrator.dashboard.form.minimum"/>
			</th>
			<td>
				<jstl:forEach var="min" items="${minimumPriceKitchenUtensils.entrySet()}">
					<p>
						<jstl:out value="${min.getValue()}"/>
						<jstl:out value="${min.getKey()}"/>
					</p>
				</jstl:forEach>
			</td>
		</tr>
		<tr>
			<th scope="row">
				<acme:message code="administrator.dashboard.form.maximum"/>
			</th>
			<td>
				<jstl:forEach var="max" items="${maximumPriceKitchenUtensils.entrySet()}">
					<p>
						<jstl:out value="${max.getValue()}"/>
						<jstl:out value="${max.getKey()}"/>
					</p>
				</jstl:forEach>
			</td>
		</tr>
	</table>
	<h2>
		<acme:message code="administrator.dashboard.form.dishes"/>
	</h2>
	<table class="table table-sm">
		<tr>
			<th scope="row">
				<acme:message code="administrator.dashboard.form.total"/>
			</th>
			<td>
				<p>
					<jstl:out value="${totalNumberIngredients.get('Proposed')}"/>
					<acme:message code="administrator.dashboard.form.proposed"/>
				</p>
				<p>
					<jstl:out value="${totalNumberIngredients.get('Accepted')}"/>
					<acme:message code="administrator.dashboard.form.accepted"/>
				</p>
				<p>
					<jstl:out value="${totalNumberIngredients.get('Denied')}"/>
					<acme:message code="administrator.dashboard.form.denied"/>
				</p>
			</td>
		</tr>
	</table>
	<h2>
		<acme:message code="administrator.admin-dashboard.form.average-dished"/>
	</h2>
	<table class="table table-sm">
		<jstl:forEach var="avg" items="${averagePriceFineDishes.entrySet()}">
			<tr>
				<th scope="row">
					<acme:message code="administrator.admin-dashboard.form.proposed"/>
				</th>
				<td>
					<jstl:out value="${avg.getValue().get('Proposed')}"></jstl:out>
					<jstl:out value="${avg.getKey()}"></jstl:out>
				</td>
			</tr>
			<tr>
				<th scope="row">
					<acme:message code="administrator.admin-dashboard.form.accepted"/>
				</th>
				<td>
					<jstl:out value="${avg.getValue().get('Accepted')}"></jstl:out>
					<jstl:out value="${avg.getKey()}"></jstl:out>
				</td>
			</tr>
			<tr>
				<th scope="row">
					<acme:message code="administrator.admin-dashboard.form.denied"/>
				</th>
				<td>
					<jstl:out value="${avg.getValue().get('Denied')}"></jstl:out>
					<jstl:out value="${avg.getKey()}"></jstl:out>
				</td>
			</tr>
		</jstl:forEach>
	</table>
	<h2>
		<acme:message code="administrator.admin-dashboard.form.deviation-dishes"/>
	</h2>
	<table class="table table-sm">
		<jstl:forEach var="stddev" items="${deviationPriceFineDishes.entrySet()}">
			<tr>
				<th scope="row">
					<acme:message code="administrator.admin-dashboard.form.proposed"/>
				</th>
				<td>
					<jstl:out value="${stddev.getValue().get('Proposed')}"></jstl:out>
					<jstl:out value="${stddev.getKey()}"></jstl:out>
				</td>
			</tr>
			<tr>
				<th scope="row">
					<acme:message code="administrator.admin-dashboard.form.accepted"/>
				</th>
				<td>
					<jstl:out value="${stddev.getValue().get('Accepted')}"></jstl:out>
					<jstl:out value="${stddev.getKey()}"></jstl:out>
				</td>
			</tr>
			<tr>
				<th scope="row">
					<acme:message code="administrator.admin-dashboard.form.denied"/>
				</th>
				<td>
					<jstl:out value="${stddev.getValue().get('Denied')}"></jstl:out>
					<jstl:out value="${stddev.getKey()}"></jstl:out>
				</td>
			</tr>
		</jstl:forEach>
	</table>
	<h2>
		<acme:message code="administrator.admin-dashboard.form.minimum-dishes"/>
	</h2>
	<table class="table table-sm">
		<jstl:forEach var="min" items="${minimumPriceFineDishes.entrySet()}">
			<tr>
				<th scope="row">
					<acme:message code="administrator.admin-dashboard.form.proposed"/>
				</th>
				<td>
					<jstl:out value="${min.getValue().get('Proposed')}"></jstl:out>
					<jstl:out value="${min.getKey()}"></jstl:out>
				</td>
			</tr>
			<tr>
				<th scope="row">
					<acme:message code="administrator.admin-dashboard.form.accepted"/>
				</th>
				<td>
					<jstl:out value="${min.getValue().get('Accepted')}"></jstl:out>
					<jstl:out value="${min.getKey()}"></jstl:out>
				</td>
			</tr>
			<tr>
				<th scope="row">
					<acme:message code="administrator.admin-dashboard.form.denied"/>
				</th>
				<td>
					<jstl:out value="${min.getValue().get('Denied')}"></jstl:out>
					<jstl:out value="${min.getKey()}"></jstl:out>
				</td>
			</tr>
		</jstl:forEach>
	</table>
	<h2>
		<acme:message code="administrator.admin-dashboard.form.maximum-dishes"/>
	</h2>
	<table class="table table-sm">
		<jstl:forEach var="max" items="${maximumPriceFineDishes.entrySet()}">
			<tr>
				<th scope="row">
					<acme:message code="administrator.admin-dashboard.form.proposed"/>
				</th>
				<td>
					<jstl:out value="${max.getValue().get('Proposed')}"></jstl:out>
					<jstl:out value="${max.getKey()}"></jstl:out>
				</td>
			</tr>
			<tr>
				<th scope="row">
					<acme:message code="administrator.admin-dashboard.form.accepted"/>
				</th>
				<td>
					<jstl:out value="${max.getValue().get('Accepted')}"></jstl:out>
					<jstl:out value="${max.getKey()}"></jstl:out>
				</td>
			</tr>
			<tr>
				<th scope="row">
					<acme:message code="administrator.admin-dashboard.form.denied"/>
				</th>
				<td>
					<jstl:out value="${max.getValue().get('Denied')}"></jstl:out>
					<jstl:out value="${max.getKey()}"></jstl:out>
				</td>
			</tr>
		</jstl:forEach>
	</table>
</div>