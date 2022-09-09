package acme.features.chef.delor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.delor.Delor;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Chef;

@Service
public class ChefDelorDeleteService implements AbstractDeleteService<Chef, Delor> {

	@Autowired
	protected ChefDelorRepository chefDelorRepository;
	
	@Override
	public boolean authorise(final Request<Delor> request) {
		assert request != null;

		boolean res;
		int id;
		Delor delor;
		Chef chef;

		id = request.getModel().getInteger("id");
		delor = this.chefDelorRepository.findOneDelorById(id);
		chef = delor.getElement().getChef();
		res = request.isPrincipal(chef) && delor.getElement().isDraft();

		return res;
	}

	@Override
	public void bind(final Request<Delor> request, final Delor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code", "instantiationMoment", "title", "description", "startDate", "finishDate", "budget", "link");
	}

	@Override
	public void unbind(final Request<Delor> request, final Delor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "instantiationMoment", "title", "description", "startDate", "finishDate", "budget", "link");
	}

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
	}

	@Override
	public void delete(final Request<Delor> request, final Delor entity) {
		assert request != null;
		assert entity != null;
				
		this.chefDelorRepository.delete(entity);
	}
	
	

}
