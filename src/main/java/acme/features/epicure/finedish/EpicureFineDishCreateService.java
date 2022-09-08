package acme.features.epicure.finedish;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.finedish.FineDish;
import acme.entities.finedish.Status;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;
import acme.roles.Epicure;

@Service
public class EpicureFineDishCreateService implements AbstractCreateService<Epicure, FineDish> {

	@Autowired
	protected EpicureFineDishRepository repository;

	@Override
	public boolean authorise(final Request<FineDish> request) {
		assert request != null;

		return true;
	}

	@Override
	public FineDish instantiate(final Request<FineDish> request) {
		assert request != null;

		FineDish res;
		Epicure epicure;
		Date creationDate;
		
		creationDate = new Date(System.currentTimeMillis() - 1);
		epicure = this.repository.findOneEpicureById(request.getPrincipal().getActiveRoleId());

		res = new FineDish();
		res.setDraft(true);
		res.setStatus(Status.PROPOSED);
		res.setEpicure(epicure);
		res.setCreationDate(creationDate);

		return res;
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
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		Collection<Chef> chefs;
		
		chefs=this.repository.findAllChefs();

		request.unbind(entity, model, "status", "code", "request", "budget", "creationDate", "startDate", "endDate", "link", "draft");

		model.setAttribute("chefs", chefs);
	}
	
	@Override
	public void bind(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code", "request", "budget", "startDate", "endDate", "link");
		
		Model model;
		Chef selectedChef;

		model = request.getModel();
		selectedChef = this.repository.findOneChefById(Integer.parseInt(model.getString("chefs")));

		entity.setChef(selectedChef);
		
		entity.setDraft(true);
	}

	@Override
	public void create(final Request<FineDish> request, final FineDish entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
