package acme.features.epicure.finedish;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.finedish.FineDish;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Epicure;

@Service
public class EpicureFineDishUpdateService implements AbstractUpdateService<Epicure, FineDish> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected EpicureFineDishRepository repository;

	@Override
	public boolean authorise(final Request<FineDish> request) {
		assert request != null;

		boolean result;
		int masterId;
		final FineDish fineDish;
		final Epicure epicure;

		masterId = request.getModel().getInteger("id");
		fineDish = this.repository.findOneFineDishById(masterId);
		epicure = fineDish.getEpicure();
		result = fineDish.isDraft() && request.isPrincipal(epicure);

		return result;
	}

	@Override
	public void validate(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("startDate")) {
			Calendar creationDate;
			Calendar startDate;

			creationDate = Calendar.getInstance();
			creationDate.setTime(entity.getCreationDate());

			startDate = Calendar.getInstance();
			startDate.setTime(entity.getStartDate());

			startDate.add(Calendar.MONTH, -1);

			errors.state(request, startDate.after(creationDate), "startDate",
					"epicure.finedish.error.startsearly");
		}

		if (!errors.hasErrors("startDate") && !errors.hasErrors("endDate")) {
			Calendar startDate;
			Calendar endDate;

			startDate = Calendar.getInstance();
			startDate.setTime(entity.getStartDate());

			endDate = Calendar.getInstance();
			endDate.setTime(entity.getEndDate());

			endDate.add(Calendar.MONTH, -1);

			errors.state(request, endDate.after(startDate), "endDate",
					"epicure.finedish.error.endsearly");
		}

		if (!errors.hasErrors("code")) {
			FineDish existing;

			existing = this.repository.findOneFineDishByCode(entity.getCode());
			errors.state(request, existing == null || existing.getId() == entity.getId(), "code",
					"epicure.finedish.error.codeduplicated");
		}

		if (!errors.hasErrors("budget")) {
			Double budget;
			String currency;

			budget = entity.getBudget().getAmount();
			currency = entity.getBudget().getCurrency();

			errors.state(request, this.repository.isAcceptedCurrency(currency), "budget",
					"epicure.finedish.error.notacceptedcurrency");
			errors.state(request, budget > 0.0, "budget", "epicure.finedish.error.negativebudget");
		}
	}

	@Override
	public void bind(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code", "request", "budget", "startDate", "endDate", "link");
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
	public FineDish findOne(final Request<FineDish> request) {
		assert request != null;

		FineDish res;
		int id;

		id = request.getModel().getInteger("id");
		res = this.repository.findOneFineDishById(id);

		return res;
	}

	@Override
	public void update(final Request<FineDish> request, final FineDish entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
}
