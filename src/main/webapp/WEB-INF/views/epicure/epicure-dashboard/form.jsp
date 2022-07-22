<%@page language="java" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<h2>
	<acme:message code="epicure.epicure-dasboard.form.title"/>
</h2>
<div>
	<h2>
		<acme:message code="epicure.epicure-dashobard.form.average"/>
	</h2>
	<table class="table table-sm">
		<jstl:forEach var="averageState" items="${averageFineDishBudgetPerState.entrySet()}">
			<tr>
				<th scope="row">
					<acme:message code="epicure.epicure-dashobard.form.proposed"/>
					<jstl:out value="${averageState.getKey()}" />
				</th>
				<td>
					<jstl:out value="${averageState.getValue().get('Proposed')}" />
				</td>
			</tr>
			<tr>
				<th scope="row">
					<acme:message code="epicure.epicure-dashobard.form.accepted"/>
					<jstl:out value="${averageState.getKey()}" />
				</th>
				<td>
					<jstl:out value="${averageState.getValue().get('Accepted')}" />
				</td>
			</tr>
			<tr>
				<th scope="row">
					<acme:message code="epicure.epicure-dashobard.form.denied"/>
					<jstl:out value="${averageState.getKey()}" />
				</th>
				<td>
					<jstl:out value="${averageState.getValue().get('Denied')}" />
				</td>
			</tr>
		</jstl:forEach>
	</table>
	<h2>
		<acme:message code="epicure.epicure-dashobard.form.deviation"/>
	</h2>
	<table class="table table-sm">
		<jstl:forEach var="deviationState" items="${deviationFineDishBudgetPerState.entrySet()}">
			<tr>
				<th scope="row">
					<acme:message code="epicure.epicure-dashobard.form.proposed"/>
					<jstl:out value="${deviationState.getKey()}" />
				</th>
				<td>
					<jstl:out value="${deviationState.getValue().get('Proposed')}" />
				</td>
			</tr>
			<tr>
				<th scope="row">
					<acme:message code="epicure.epicure-dashobard.form.accepted"/>
					<jstl:out value="${deviationState.getKey()}" />
				</th>
				<td>
					<jstl:out value="${deviationState.getValue().get('Accepted')}" />
				</td>
			</tr>
			<tr>
				<th scope="row">
					<acme:message code="epicure.epicure-dashobard.form.denied"/>
					<jstl:out value="${deviationState.getKey()}" />
				</th>
				<td>
					<jstl:out value="${deviationState.getValue().get('Denied')}" />
				</td>
			</tr>
		</jstl:forEach>
	</table>
	<h2>
		<acme:message code="epicure.epicure-dashboard.max"/>
	</h2>
	<table class="table table-sm">
		<jstl:forEach var="maxState" items="${maximumFineDishBudgetPerState.entrySet()}">
			<tr>
				<th scope="row">
					<acme:message code="epicure.epicure-dashobard.form.proposed"/>
					<jstl:out value="${maxState.getKey()}" />
				</th>
				<td>
					<jstl:out value="${maxState.getValue().get('Proposed')}" />
				</td>
			</tr>
			<tr>
				<th scope="row">
					<acme:message code="epicure.epicure-dashobard.form.accepted"/>
					<jstl:out value="${maxState.getKey()}" />
				</th>
				<td>
					<jstl:out value="${maxState.getValue().get('Accepted')}" />
				</td>
			</tr>
			<tr>
				<th scope="row">
					<acme:message code="epicure.epicure-dashobard.form.denied"/>
					<jstl:out value="${maxState.getKey()}" />
				</th>
				<td>
					<jstl:out value="${maxState.getValue().get('Denied')}" />
				</td>
			</tr>
		</jstl:forEach>
	</table>
	<h2>
		<acme:message code="epicure.epicure-dashboard.min"/>
	</h2>
	<table class="table table-sm">
		<jstl:forEach var="minState" items="${minimumFineDishBudgetPerState.entrySet()}">
			<tr>
				<th scope="row">
					<acme:message code="epicure.epicure-dashobard.form.proposed"/>
					<jstl:out value="${minState.getKey()}" />
				</th>
				<td>
					<jstl:out value="${minState.getValue().get('Proposed')}" />
				</td>
			</tr>
			<tr>
				<th scope="row">
					<acme:message code="epicure.epicure-dashobard.form.accepted"/>
					<jstl:out value="${minState.getKey()}" />
				</th>
				<td>
					<jstl:out value="${minState.getValue().get('Accepted')}" />
				</td>
			</tr>
			<tr>
				<th scope="row">
					<acme:message code="epicure.epicure-dashobard.form.denied"/>
					<jstl:out value="${minState.getKey()}" />
				</th>
				<td>
					<jstl:out value="${minState.getValue().get('Denied')}" />
				</td>
			</tr>
		</jstl:forEach>
	</table>
</div>

<div>
	<h2>
		<acme:message code="epicure.epicure-dashobard.form.total"/>
	</h2>
	<canvas id="canvas1"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		var data = {
				labels: ["PROPOSED", "ACCEPTED", "DENIED"],
				datasets : [
					{
						data : [
							<jstl:out value="${totalFineDishes.get('Proposed')}"/>,
							<jstl:out value="${totalFineDishes.get('Accepted')}"/>,
							<jstl:out value="${totalFineDishes.get('Denied')}"/>
						]
					}
				]
		};
		var options = {
				scales: {
					yAxes:[{
						ticks:{
							suggestedMin : 0.0,
							suggestedMax : 1.0
						}
					}]
				},
				legend : {
					display : false
				}
			};
		var canvas1, context1;
		
		canvas1 = document.getElementById("canvas1");
		context1 = canvas1.getContext("2d");
		new Chart(context1, {
			type : "bar",
			data : data,
			options : options
		});
	});
</script>

<acme:return/>