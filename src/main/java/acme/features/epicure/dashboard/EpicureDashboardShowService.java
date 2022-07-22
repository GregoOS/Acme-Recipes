package acme.features.epicure.dashboard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.features.authenticated.systemConfiguration.AuthenticatedSystemConfigurationRepository;
import acme.forms.EpicureDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Epicure;

@Service
public class EpicureDashboardShowService implements AbstractShowService<Epicure, EpicureDashboard>{

	@Autowired
	protected EpicureDashboardRepository repository;
	
	@Autowired
	protected AuthenticatedSystemConfigurationRepository configurationRepository;
	
	@Override
	public boolean authorise(final Request<EpicureDashboard> request) {
		assert request!=null;
		
		return true;
	}

	@Override
	public EpicureDashboard findOne(final Request<EpicureDashboard> request) {
		assert request!=null;
		
		final String[] currencies = this.configurationRepository.findSystemConfiguration().getAcceptedCurrencies().split(",");
		
		EpicureDashboard res;
		final Map<String, Long> totalFineDishes;
		final Map<String, Map<String, Double>> averageFineDishBudgetPerState = new HashMap<String, Map<String, Double>>();
		final Map<String, Map<String, Double>> deviationFineDishBudgetPerState = new HashMap<String, Map<String,Double>>();
		final Map<String, Map<String, Double>> minimumFineDishBudgetPerState = new HashMap<String, Map<String,Double>>();
		final Map<String, Map<String, Double>> maximumFineDishBudgetPerState = new HashMap<String, Map<String,Double>>();
		
		res = new EpicureDashboard();
		
		totalFineDishes = this.repository.totalFineDish(request.getPrincipal().getActiveRoleId()).stream().collect(Collectors.toMap(p->p.get(0).toString(), p->(Long)p.get(1)));
		for(final String currency: currencies) {
			final String c = currency.replace('"', ' ').trim();
			final List<Tuple> averageFineDishes = this.repository.averageFineDishBudgetPerStatus(request.getPrincipal().getActiveRoleId(), c);
			averageFineDishBudgetPerState.put(c, 
				averageFineDishes.stream().collect(Collectors.toMap(p->p.get(0).toString(), p->(Double)p.get(1))));
			final List<Tuple> deviationFineDishs = this.repository.deviationFineDishBudgetPerState(request.getPrincipal().getActiveRoleId(), c);
			deviationFineDishBudgetPerState.put(c, 
				deviationFineDishs.stream().collect(Collectors.toMap(p->p.get(0).toString(), p->(Double)p.get(1))));
			final List<Tuple> maximumFineDishs = this.repository.maximumFineDishBudgetPerState(request.getPrincipal().getActiveRoleId(), c);
			maximumFineDishBudgetPerState.put(c, 
				maximumFineDishs.stream().collect(Collectors.toMap(p->p.get(0).toString(), p->(Double)p.get(1))));
			final List<Tuple> minimumFineDishes = this.repository.minimumFineDishBudgetPerState(request.getPrincipal().getActiveRoleId(), c);
			minimumFineDishBudgetPerState.put(c, 
				minimumFineDishes.stream().collect(Collectors.toMap(p->p.get(0).toString(), p->(Double)p.get(1))));
		}
		
		res.setTotalFineDishes(totalFineDishes);
		res.setAverageFineDishBudgetPerState(averageFineDishBudgetPerState);
		res.setDeviationFineDishBudgetPerState(deviationFineDishBudgetPerState);
		res.setMinimumFineDishBudgetPerState(minimumFineDishBudgetPerState);
		res.setMaximumFineDishBudgetPerState(maximumFineDishBudgetPerState);
		
		return res;
	}

	@Override
	public void unbind(final Request<EpicureDashboard> request, final EpicureDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, 
			"totalFineDishes",
		"averageFineDishBudgetPerState",
		"deviationFineDishBudgetPerState",
		"minimumFineDishBudgetPerState",
		"maximumFineDishBudgetPerState");
		
	}

}
