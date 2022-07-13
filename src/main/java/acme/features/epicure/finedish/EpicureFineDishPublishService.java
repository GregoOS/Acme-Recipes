package acme.features.epicure.finedish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.finedish.FineDish;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Epicure;

@Service
public class EpicureFineDishPublishService implements AbstractUpdateService<Epicure, FineDish> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected EpicureFineDishRepository repository;

	@Override
	public boolean authorise(final Request<FineDish> request) {
		assert request != null;

		boolean result;
		final int fineDishId;
		final FineDish fineDish;
		final Epicure epicure;

		fineDishId = request.getModel().getInteger("id");
		fineDish = this.repository.findOneFineDishById(fineDishId);
		epicure = fineDish.getEpicure();
		result = fineDish.isDraft() && request.isPrincipal(epicure);

		return result;
	}

	@Override
	public FineDish findOne(final Request<FineDish> request) {
		assert request != null;

		FineDish res;
		int id;

		id = request.getModel().getInteger("id");
		res = this.repository.findOneFineDishById(id);

		return res;
	}

	@Override
	public void bind(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code", "request", "budget", "startDate", "endDate", "link");
	}

	@Override
	public void validate(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "status", "code", "request", "budget", "creationDate", "startDate", "endDate",
				"link", "draft");

		final int masterId = request.getModel().getInteger("id");
		model.setAttribute("masterId", masterId);

		model.setAttribute("chefName", entity.getChef().getIdentity().getName());
		model.setAttribute("chefSurname", entity.getChef().getIdentity().getSurname());
		model.setAttribute("chefEmail", entity.getChef().getIdentity().getEmail());
		model.setAttribute("chefOrganisation", entity.getChef().getOrganisation());
		model.setAttribute("chefAssertion", entity.getChef().getAssertion());
		model.setAttribute("chefLink", entity.getChef().getLink());
	}

	@Override
	public void update(final Request<FineDish> request, final FineDish entity) {
		assert request != null;
		assert entity != null;

		entity.setDraft(false);
		this.repository.save(entity);
	}

}
