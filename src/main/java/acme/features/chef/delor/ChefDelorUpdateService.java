package acme.features.chef.delor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.delor.Delor;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;

@Service
public class ChefDelorUpdateService implements AbstractUpdateService<Chef, Delor> {

	@Autowired
	protected ChefDelorRepository chefDelorRepository;
	
	@Autowired
	protected ChefDelorValidator chefDelorValidator;
	
	@Override
	public boolean authorise(final Request<Delor> request) {
		assert request != null;
		
		boolean res;
		int id;
		Delor delor;
		Chef chef;
		
		id = request.getModel().getInteger("id");
		delor = this.chefDelorRepository.findOneDelorById(id);
		chef = delor.getElement().getChef(); //supposing a one to one relation this should suffice
		res = request.isPrincipal(chef) && delor.getElement().isDraft();
		
		
		return res;
	}

	@Override
	public void bind(final Request<Delor> request, final Delor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "keylet", "instantiationMoment","subject", "explanation", "startPeriod", "finishPeriod", "income", "moreinfo");
	}

	@Override
	public void unbind(final Request<Delor> request, final Delor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "keylet", "instantiationMoment","subject", "explanation", "startPeriod", "finishPeriod", "income", "moreinfo");
		model.setAttribute("element", entity.getElement());	}
	
	@Override
	public Delor findOne(final Request<Delor> request) {
		assert request != null;

		Delor res;
		int id;

		id = request.getModel().getInteger("id");
		res = this.chefDelorRepository.findOneDelorById(id);

		return res;
	}

	@Override
	public void validate(final Request<Delor> request, final Delor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("keylet")) {
			Delor existing;
			Integer id;

			existing = this.chefDelorRepository.findDelorByKeylet(entity.getKeylet());
			id = request.getModel().getInteger("id");

			errors.state(request, existing == null || existing.getId() == id, "keylet",
					"chef.delor.keylet.duplicated");
		}
		
		this.chefDelorValidator.validateDelor(request, entity, errors);
	}

	@Override
	public void update(final Request<Delor> request, final Delor entity) {
		assert request != null;
		assert entity != null;

		this.chefDelorRepository.save(entity);
	}
	
	

}
