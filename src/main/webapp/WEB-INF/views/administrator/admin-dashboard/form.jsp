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
					<jstl:out value="${totalNumberFineDishes.get('PROPOSED')}"/>
					<acme:message code="administrator.admin-dashboard.form.proposed"/>
				</p>
				<p>
					<jstl:out value="${totalNumberFineDishes.get('ACCEPTED')}"/>
					<acme:message code="administrator.admin-dashboard.form.accepted"/>
				</p>
				<p>
					<jstl:out value="${totalNumberFineDishes.get('DENIED')}"/>
					<acme:message code="administrator.admin-dashboard.form.denied"/>
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
					<jstl:out value="${avg.getValue().get('PROPOSED')}"></jstl:out>
					<jstl:out value="${avg.getKey()}"></jstl:out>
				</td>
			</tr>
			<tr>
				<th scope="row">
					<acme:message code="administrator.admin-dashboard.form.accepted"/>
				</th>
				<td>
					<jstl:out value="${avg.getValue().get('ACCEPTED')}"></jstl:out>
					<jstl:out value="${avg.getKey()}"></jstl:out>
				</td>
			</tr>
			<tr>
				<th scope="row">
					<acme:message code="administrator.admin-dashboard.form.denied"/>
				</th>
				<td>
					<jstl:out value="${avg.getValue().get('DENIED')}"></jstl:out>
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
					<jstl:out value="${stddev.getValue().get('PROPOSED')}"></jstl:out>
					<jstl:out value="${stddev.getKey()}"></jstl:out>
				</td>
			</tr>
			<tr>
				<th scope="row">
					<acme:message code="administrator.admin-dashboard.form.accepted"/>
				</th>
				<td>
					<jstl:out value="${stddev.getValue().get('ACCEPTED')}"></jstl:out>
					<jstl:out value="${stddev.getKey()}"></jstl:out>
				</td>
			</tr>
			<tr>
				<th scope="row">
					<acme:message code="administrator.admin-dashboard.form.denied"/>
				</th>
				<td>
					<jstl:out value="${stddev.getValue().get('DENIED')}"></jstl:out>
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
					<jstl:out value="${min.getValue().get('PROPOSED')}"></jstl:out>
					<jstl:out value="${min.getKey()}"></jstl:out>
				</td>
			</tr>
			<tr>
				<th scope="row">
					<acme:message code="administrator.admin-dashboard.form.accepted"/>
				</th>
				<td>
					<jstl:out value="${min.getValue().get('ACCEPTED')}"></jstl:out>
					<jstl:out value="${min.getKey()}"></jstl:out>
				</td>
			</tr>
			<tr>
				<th scope="row">
					<acme:message code="administrator.admin-dashboard.form.denied"/>
				</th>
				<td>
					<jstl:out value="${min.getValue().get('DENIED')}"></jstl:out>
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
					<jstl:out value="${max.getValue().get('PROPOSED')}"></jstl:out>
					<jstl:out value="${max.getKey()}"></jstl:out>
				</td>
			</tr>
			<tr>
				<th scope="row">
					<acme:message code="administrator.admin-dashboard.form.accepted"/>
				</th>
				<td>
					<jstl:out value="${max.getValue().get('ACCEPTED')}"></jstl:out>
					<jstl:out value="${max.getKey()}"></jstl:out>
				</td>
			</tr>
			<tr>
				<th scope="row">
					<acme:message code="administrator.admin-dashboard.form.denied"/>
				</th>
				<td>
					<jstl:out value="${max.getValue().get('DENIED')}"></jstl:out>
					<jstl:out value="${max.getKey()}"></jstl:out>
				</td>
			</tr>
		</jstl:forEach>
	</table>
	
	<h2>
		<acme:message code="administrator.admin-dashboard.form.ratioPimpamIngredients"/>
	</h2>
	<table class="table table-sm">
		<jstl:forEach var="ratio" items="${ratioPimpamIngredients}">
			<tr>
				<th scope="row">
					<jstl:out value="${ratio.getKey()}"/>
				</th>
				<td>
					<jstl:out value="${ratio.getValue()}"/>
				</td>
			</tr>
		</jstl:forEach>
	</table>
	
	<h2>
		<acme:message code="administrator.admin-dashboard.form.averageBudgetOfPimpam"/>
	</h2>
	<table class="table table-sm">
		<jstl:forEach var="average" items="${averageBudgetOfPimpam}">
			<tr>
				<th scope="row">
					<jstl:out value="${average.getKey()}"/>
				</th>
				<td>
					<jstl:out value="${average.getValue()}"/>
				</td>
			</tr>
		</jstl:forEach>
	</table>
	
	<h2>
		<acme:message code="administrator.admin-dashboard.form.deviationBudgetOfPimpam"/>
	</h2>	
	<table class="table table-sm">
		<jstl:forEach var="deviation" items="${deviationBudgetOfPimpam}">
			<tr>
				<th scope="row">
					<jstl:out value="${deviation.getKey()}"/>
				</th>
				<td>
					<jstl:out value="${deviation.getValue()}"/>
				</td>
			</tr>
		</jstl:forEach>
	</table>
	
	<h2>
		<acme:message code="administrator.admin-dashboard.form.minimumBudgetOfPimpam"/>
	</h2>	
	<table class="table table-sm">
		<jstl:forEach var="minimum" items="${minimumBudgetOfPimpam}">
			<tr>
				<th scope="row">
					<jstl:out value="${minimum.getKey()}"/>
				</th>
				<td>
					<jstl:out value="${minimum.getValue()}"/>
				</td>
			</tr>
		</jstl:forEach>
	</table>
	
	<h2>
		<acme:message code="administrator.admin-dashboard.form.maximumBudgetOfPimpam"/>
	</h2>
	<table class="table table-sm">
		<jstl:forEach var="maximum" items="${maximumBudgetOfChinpum}">
			<tr>
				<th scope="row">
					<jstl:out value="${maximum.getKey()}"/>
				</th>
				<td>
					<jstl:out value="${maximum.getValue()}"/>
				</td>
			</tr>
		</jstl:forEach>
	</table>
	
</div>