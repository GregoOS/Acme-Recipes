package acme.features.chef.finedish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.finedish.FineDish;
import acme.entities.finedish.Status;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;

@Service
public class ChefFineDishAcceptService implements AbstractUpdateService<Chef, FineDish> {

	@Autowired
	protected ChefFineDishRepository repository;

	@Override
	public boolean authorise(final Request<FineDish> request) {
		assert request != null;

		final int patronId = request.getPrincipal().getActiveRoleId();
		final int fineDishId = request.getModel().getInteger("id");
		final FineDish fineDish = this.repository.findOneFineDishById(fineDishId);
		final int fineDishChefId = fineDish.getChef().getId();

		return patronId == fineDishChefId && !fineDish.isDraft()
				&& fineDish.getStatus().equals(Status.PROPOSED);
	}

	@Override
	public void bind(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model);
	}

	@Override
	public FineDish findOne(final Request<FineDish> request) {
		assert request != null;

		final int id = request.getModel().getInteger("id");
		return this.repository.findOneFineDishById(id);
	}

	@Override
	public void validate(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void update(final Request<FineDish> request, final FineDish entity) {
		assert request != null;
		assert entity != null;

		entity.setStatus(Status.ACCEPTED);
		this.repository.save(entity);
	}
}