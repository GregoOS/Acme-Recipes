package acme.features.epicure.memorandum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.memorandum.Memorandum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Epicure;

@Service
public class EpicureMemorandumShowService implements AbstractShowService<Epicure, Memorandum> {

	@Autowired
	protected EpicureMemorandumRepository repository;

	@Override
	public boolean authorise(final Request<Memorandum> request) {
		assert request != null;

		final int epicureId = request.getPrincipal().getActiveRoleId();
		final int memorandumId = request.getModel().getInteger("id");
		final int fineDishEpicureId = this.repository.findEpicureIdByMemorandumId(memorandumId);

		return epicureId == fineDishEpicureId;
	}

	@Override
	public Memorandum findOne(final Request<Memorandum> request) {
		assert request != null;

		final int id = request.getModel().getInteger("id");
		return this.repository.findOneMemorandumById(id);
	}

	@Override
	public void unbind(final Request<Memorandum> request, final Memorandum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "sequenceNumber", "creationTime", "report", "link");

		final int masterId = request.getModel().getInteger("id");
		model.setAttribute("masterId", masterId);

		model.setAttribute("fineDishStatus", entity.getFineDish().getStatus());
		model.setAttribute("fineDishRequest", entity.getFineDish().getRequest());
		model.setAttribute("fineDishBudget", entity.getFineDish().getBudget());
		model.setAttribute("fineDishCreationDate", entity.getFineDish().getCreationDate());
		model.setAttribute("fineDishStartDate", entity.getFineDish().getStartDate());
		model.setAttribute("fineDishEndDate", entity.getFineDish().getEndDate());
		model.setAttribute("fineDishLink", entity.getFineDish().getLink());
		model.setAttribute("fineDishCode", entity.getFineDish().getCode());
	}

}
