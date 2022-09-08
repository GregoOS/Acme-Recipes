package acme.features.chef.pimpam;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.pimpam.Pimpam;
import acme.features.chef.element.ChefElementRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;

@Service
public class ChefPimpamCreateService implements AbstractCreateService<Chef, Pimpam> {

	@Autowired
	protected ChefPimpamRepository chefPimpamRepository;
	
	@Autowired
	protected ChefPimpamValidator chefPimpamValidator;
	
	@Autowired
	protected ChefElementRepository chefElementRepository;
	
	@Override
	public boolean authorise(final Request<Pimpam> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public void bind(final Request<Pimpam> request, final Pimpam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		entity.setElement(this.chefElementRepository.findElementById(request.getModel().getInteger("elementId")));
		request.bind(entity, errors, "code","title", "description", "startDate", "finishDate", "budget", "link");
	}

	@Override
	public void unbind(final Request<Pimpam> request, final Pimpam entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "instantiationMoment","title", "description", "startDate", "finishDate", "budget", "link");
		model.setAttribute("elements", this.chefPimpamRepository.findAllElementsByChef(request.getPrincipal().getActiveRoleId()));
	}

	@Override
	public Pimpam instantiate(final Request<Pimpam> request) {
		assert request != null;
		
		Pimpam res;
		final Date instantiationMoment = new Date(System.currentTimeMillis()-1);
		
		res = new Pimpam();
		res.setInstantiationMoment(instantiationMoment);
		
		return res;
	}

	@Override
	public void validate(final Request<Pimpam> request, final Pimpam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("code")) {
			Pimpam existing;
			existing = this.chefPimpamRepository.findPimpamByCode(entity.getCode());
			errors.state(request, existing == null, "code", "chef.pimpam.code.duplicated"); //TODO replace for a jsp valid thingy
		}
		
		this.chefPimpamValidator.validatePimpam(request, entity, errors);
	}

	@Override
	public void create(final Request<Pimpam> request, final Pimpam entity) {
		assert request != null;
		assert entity != null;

		this.chefPimpamRepository.save(entity);
	}
	
	

}
