package acme.features.chef.delor;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.delor.Delor;
import acme.features.chef.element.ChefElementRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;

@Service
public class ChefDelorCreateService implements AbstractCreateService<Chef, Delor> {

	@Autowired
	protected ChefDelorRepository chefDelorRepository;
	
	@Autowired
	protected ChefDelorValidator chefDelorValidator;
	
	@Autowired
	protected ChefElementRepository chefElementRepository;
	
	@Override
	public boolean authorise(final Request<Delor> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public void bind(final Request<Delor> request, final Delor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		entity.setElement(this.chefElementRepository.findElementById(request.getModel().getInteger("elementId")));
		request.bind(entity, errors, "keylet","subject", "explanation", "startDate", "finishDate", "income", "moreInfo");
	}

	@Override
	public void unbind(final Request<Delor> request, final Delor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "keylet", "instantiationMoment","subject", "explanation", "startDate", "finishDate", "income", "moreInfo");
		model.setAttribute("elements", this.chefDelorRepository.findAllIngredientsByChef(request.getPrincipal().getActiveRoleId()));
	}

	@Override
	public Delor instantiate(final Request<Delor> request) {
		assert request != null;
		
		Delor res;
		final Date instantiationMoment = new Date(System.currentTimeMillis()-1);
		
		res = new Delor();
		res.setInstantiationMoment(instantiationMoment);
		
		return res;
	}

	@Override
	public void validate(final Request<Delor> request, final Delor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("keylet")) {
			Delor existing;

			existing = this.chefDelorRepository.findDelorByKeylet(entity.getKeylet());
			errors.state(request, existing == null, "keylet", "chef.element.error.duplicated");
		}
		
		this.chefDelorValidator.validateDelor(request, entity, errors);
	}

	@Override
	public void create(final Request<Delor> request, final Delor entity) {
		assert request != null;
		assert entity != null;

		this.chefDelorRepository.save(entity);
	}
	
	

}
