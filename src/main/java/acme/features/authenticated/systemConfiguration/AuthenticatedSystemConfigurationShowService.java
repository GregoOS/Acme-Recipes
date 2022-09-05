package acme.features.authenticated.systemConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.systemconfiguration.SystemConfiguration;
import acme.features.any.useraccount.AnyUserAccountRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedSystemConfigurationShowService implements AbstractShowService<Authenticated, SystemConfiguration>{
	
	@Autowired
	AuthenticatedSystemConfigurationRepository authenticatedSystemConfigurationRepository;
	
	@Autowired
	AnyUserAccountRepository anyUserAccountRepository;

	@Override
	public boolean authorise(final Request<SystemConfiguration> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public SystemConfiguration findOne(final Request<SystemConfiguration> request) {
		assert request != null;
		
		return this.authenticatedSystemConfigurationRepository.findSystemConfiguration();
	}

	@Override
	public void unbind(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "currency", "acceptedCurrencies");
	}

}
