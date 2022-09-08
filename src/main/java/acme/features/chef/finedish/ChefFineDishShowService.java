package acme.features.chef.finedish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.finedish.FineDish;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefFineDishShowService implements AbstractShowService<Chef, FineDish> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefFineDishRepository repository;

	@Override
	public boolean authorise(final Request<FineDish> request) {
		assert request != null;

		final int epicureId = request.getPrincipal().getActiveRoleId();
		final int fineDishId = request.getModel().getInteger("id");
		final FineDish fineDish = this.repository.findOneFineDishById(fineDishId);
		final int fineDishChefId = fineDish.getChef().getId();

		return epicureId == fineDishChefId && !fineDish.isDraft();
	}

	@Override
	public FineDish findOne(final Request<FineDish> request) {
		assert request != null;

		int id;

		id = request.getModel().getInteger("id");

		return this.repository.findOneFineDishById(id);
	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "status", "code", "request", "budget", "creationDate", "startDate", "endDate", "link");

		final int masterId = request.getModel().getInteger("id");
		model.setAttribute("masterId", masterId);

		model.setAttribute("epicureName", entity.getEpicure().getIdentity().getName());
		model.setAttribute("epicureSurname", entity.getEpicure().getIdentity().getSurname());
		model.setAttribute("epicureEmail", entity.getEpicure().getIdentity().getEmail());
		model.setAttribute("epicureOrganisation", entity.getEpicure().getOrganisation());
		model.setAttribute("epicureAssertion", entity.getEpicure().getAssertion());
		model.setAttribute("epicureLink", entity.getEpicure().getLink());
	}
}
