package acme.features.chef.pimpam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.pimpam.Pimpam;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;

@Service
public class ChefPimpamUpdateService implements AbstractUpdateService<Chef, Pimpam> {

	@Autowired
	protected ChefPimpamRepository chefPimpamRepository;
	
	@Autowired
	protected ChefPimpamValidator chefPimpamValidator;
	
	@Override
	public boolean authorise(final Request<Pimpam> request) {
		assert request != null;
		
		boolean res;
		int id;
		Pimpam pimpam;
		Chef chef;
		
		id = request.getModel().getInteger("id");
		pimpam = this.chefPimpamRepository.findOnePimpamById(id);
		chef = pimpam.getElement().getChef(); //supposing a one to one relation this should suffice
		res = request.isPrincipal(chef);
		
		return res;
	}

	@Override
	public void bind(final Request<Pimpam> request, final Pimpam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "code", "instantiationMoment","title", "description", "startDate", "finishDate", "budget", "link");
	}

	@Override
	public void unbind(final Request<Pimpam> request, final Pimpam entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "instantiationMoment","title", "description", "startDate", "finishDate", "budget", "link");
	}
	
	@Override
	public Pimpam findOne(final Request<Pimpam> request) {
		assert request != null;

		Pimpam res;
		int id;

		id = request.getModel().getInteger("id");
		res = this.chefPimpamRepository.findOnePimpamById(id);

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
			errors.state(request, existing.equals(null), "code", "chef.pimpam.code.duplicated"); //TODO replace for a jsp valid thingy
		}
		
		this.chefPimpamValidator.validatePimpam(request, entity, errors);
	}

	@Override
	public void update(final Request<Pimpam> request, final Pimpam entity) {
		assert request != null;
		assert entity != null;

		this.chefPimpamRepository.save(entity);
	}
	
	

}
