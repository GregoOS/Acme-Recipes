package acme.features.administrator.systemConfiguration;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.systemconfiguration.SystemConfiguration;
import acme.features.any.useraccount.AnyUserAccountRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.entities.UserAccount;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorSystemConfigurationUpdateService  implements AbstractUpdateService<Administrator, SystemConfiguration>{
	
	@Autowired
	protected AdministratorSystemConfigurationRepository administratorSystemConfigurationRepository;
	
	@Autowired
	AnyUserAccountRepository anyUserAccountRepository;

	@Override
	public boolean authorise(final Request<SystemConfiguration> request) {
		assert request != null;
		final Integer id = request.getPrincipal().getAccountId();
		final UserAccount user = this.anyUserAccountRepository.findUserAccountById(id);
		if(user.hasRole(Administrator.class)) return true;
		else return false;
	}

	@Override
	public void bind(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "currency", "acceptedCurrencies","spamTerms",	"spamThreshold");
	}

	@Override
	public void unbind(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "currency", "acceptedCurrencies","spamTerms",	"spamThreshold");		
	}

	@Override
	public SystemConfiguration findOne(final Request<SystemConfiguration> request) {
		assert request != null;

		return this.administratorSystemConfigurationRepository.findSystemConfiguration();
	}

	@Override
	public void validate(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		final Model model = request.getModel();
		
		if (!errors.hasErrors("currency")) {
			final String acceptedCurrencies = (String) model.getAttribute("acceptedCurrencies");
			final List<String> acceptedCurrenciesList = Arrays.asList(acceptedCurrencies.split(","));
			errors.state(request, acceptedCurrenciesList.contains(entity.getCurrency()), "currency",
					"administrator.system-configuration.form.error.currency-not-supported");
		}
	}

	@Override
	public void update(final Request<SystemConfiguration> request, final SystemConfiguration entity) {
		assert request != null;
		assert entity != null;

		this.administratorSystemConfigurationRepository.save(entity);
	}
	
	

}
