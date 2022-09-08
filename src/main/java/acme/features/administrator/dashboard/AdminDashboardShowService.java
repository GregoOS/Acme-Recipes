package acme.features.administrator.dashboard;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.features.authenticated.systemConfiguration.AuthenticatedSystemConfigurationRepository;
import acme.forms.AdminDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdminDashboardShowService implements AbstractShowService<Administrator, AdminDashboard>{

	@Autowired
	protected AdminDashboardRepository repository;
	
	@Autowired
	protected AuthenticatedSystemConfigurationRepository configurationRepository;
	
	@Override
	public boolean authorise(final Request<AdminDashboard> request) {
		assert request!=null;
		
		return true;
	}

	@Override
	public AdminDashboard findOne(final Request<AdminDashboard> request) {
		final String[] currencies = this.configurationRepository.findSystemConfiguration().getAcceptedCurrencies().split(",");
		
		final AdminDashboard result;
		
		final Long totalNumberIngredients;
		final Map<String, Double> averagePriceIngredients = new HashMap<String, Double>();
		final Map<String, Double> deviationPriceIngredients = new HashMap<String, Double>();
		final Map<String, Double> minimumPriceIngredients = new HashMap<String, Double>();
		final Map<String, Double> maximumPriceIngredients = new HashMap<String, Double>();
		
		final Long totalNumberKitchenUtensils;
		final Map<String, Double> averagePriceKitchenUtensils = new HashMap<String, Double>();
		final Map<String, Double> deviationPriceKitchenUtensils = new HashMap<String, Double>();
		final Map<String, Double> minimumPriceKitchenUtensils = new HashMap<String, Double>();
		final Map<String, Double> maximumPriceKitchenUtensils = new HashMap<String, Double>();
		
		final Map<String, Long> totalNumberFineDishes;
		final Map<String, Map<String, Double>> averagePriceFineDishes = new HashMap<String, Map<String,Double>>();
		final Map<String, Map<String, Double>> deviationPriceFineDishes = new HashMap<String, Map<String,Double>>();
		final Map<String, Map<String, Double>> minimumPriceFineDishes = new HashMap<String, Map<String,Double>>();
		final Map<String, Map<String, Double>> maximumPriceFineDishes = new HashMap<String, Map<String,Double>>();
		
		result = new AdminDashboard();
		
		totalNumberIngredients = this.repository.totalIngredients();
		totalNumberKitchenUtensils = this.repository.totalUtensils();
		totalNumberFineDishes = this.repository.totalFineDishes().stream().collect(
			Collectors.toMap(t->t.get(0).toString(), t->(Long)t.get(1)));
		for(final String currency:currencies) {
			final String c = currency.replace('"', ' ').trim();
			averagePriceIngredients.put(c, this.repository.averageIngredients(c));
			deviationPriceIngredients.put(c, this.repository.deviationIngredients(c));
			minimumPriceIngredients.put(c, this.repository.minimumIngredients(c));
			maximumPriceIngredients.put(c, this.repository.maximunIngredients(c));
			
			averagePriceKitchenUtensils.put(c, this.repository.averageUtensils(c));
			deviationPriceKitchenUtensils.put(c, this.repository.deviationUtensils(c));
			minimumPriceKitchenUtensils.put(c, this.repository.minimumUtensils(c));
			maximumPriceKitchenUtensils.put(c, this.repository.maximumUtensils(c));
			
			averagePriceFineDishes.put(c, this.repository.averageFineDishes(c).stream()
				.collect(Collectors.toMap(t->t.get(0).toString(), t->(Double)t.get(1))));
			deviationPriceFineDishes.put(c, this.repository.deviationFineDishes(c).stream()
				.collect(Collectors.toMap(t->t.get(0).toString(), t->(Double)t.get(1))));
			minimumPriceFineDishes.put(c, this.repository.minimumFineDishes(c).stream()
				.collect(Collectors.toMap(t->t.get(0).toString(), t->(Double)t.get(1))));
			maximumPriceFineDishes.put(c, this.repository.maximumFineDishes(c).stream()
				.collect(Collectors.toMap(t->t.get(0).toString(), t->(Double)t.get(1))));
		}
		
		result.setTotalNumberIngredients(totalNumberIngredients);
		result.setAveragePriceIngredients(averagePriceIngredients);
		result.setDeviationPriceIngredients(deviationPriceIngredients);
		result.setMinimumPriceIngredients(minimumPriceIngredients);
		result.setMaximumPriceIngredients(maximumPriceIngredients);
		
		result.setTotalNumberKitchenUtensils(totalNumberKitchenUtensils);
		result.setAveragePriceKitchenUtensils(averagePriceKitchenUtensils);
		result.setDeviationPriceKitchenUtensils(deviationPriceKitchenUtensils);
		result.setMinimumPriceKitchenUtensils(minimumPriceKitchenUtensils);
		result.setMaximumPriceKitchenUtensils(maximumPriceKitchenUtensils);
		
		result.setTotalNumberFineDishes(totalNumberFineDishes);
		result.setAveragePriceFineDishes(averagePriceFineDishes);
		result.setDeviationPriceFineDishes(deviationPriceFineDishes);
		result.setMinimumPriceFineDishes(minimumPriceFineDishes);
		result.setMaximumPriceFineDishes(maximumPriceFineDishes);
		
		return result;
	}

	@Override
	public void unbind(final Request<AdminDashboard> request, final AdminDashboard entity, final Model model) {
		assert request!=null;
		assert entity!=null;
		assert model!=null;
		
		request.unbind(entity, model, "totalNumberIngredients",
		"averagePriceIngredients",
		"deviationPriceIngredients",
		"minimumPriceIngredients",
		"maximumPriceIngredients",
		"totalNumberKitchenUtensils",
		"averagePriceKitchenUtensils",
		"deviationPriceKitchenUtensils",
		"minimumPriceKitchenUtensils",
		"maximumPriceKitchenUtensils",
		"totalNumberFineDishes",
		"averagePriceFineDishes",
		"deviationPriceFineDishes",
		"minimumPriceFineDishes",
		"maximumPriceFineDishes");
		
	}

}
