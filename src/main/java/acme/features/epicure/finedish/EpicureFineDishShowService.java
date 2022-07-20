package acme.features.epicure.finedish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.finedish.FineDish;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Epicure;

@Service
public class EpicureFineDishShowService implements AbstractShowService<Epicure, FineDish> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected EpicureFineDishRepository repository;

	@Override
	public boolean authorise(final Request<FineDish> request) {
		assert request != null;
		boolean res;

		final int epicureId = request.getPrincipal().getActiveRoleId();
		final int fineDishId = request.getModel().getInteger("id");
		final int fineDishChefId = this.repository.findOneFineDishById(fineDishId).getEpicure().getId();

		res = epicureId == fineDishChefId;
		return res;
	}

	@Override
	public FineDish findOne(final Request<FineDish> request) {
		assert request != null;

		final int id = request.getModel().getInteger("id");
		return this.repository.findOneFineDishById(id);
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
}
