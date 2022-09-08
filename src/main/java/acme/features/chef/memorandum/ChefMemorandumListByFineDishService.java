package acme.features.chef.memorandum;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.finedish.FineDish;
import acme.entities.memorandum.Memorandum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;

@Service
public class ChefMemorandumListByFineDishService implements AbstractListService<Chef, Memorandum> {

	@Autowired
	protected ChefMemorandumRepository repository;

	@Override
	public boolean authorise(final Request<Memorandum> request) {
		assert request != null;

		final int chefId = request.getPrincipal().getActiveRoleId();
		final int fineDishId = request.getModel().getInteger("masterId");
		final FineDish fineDish = this.repository.findOneFineDishById(fineDishId);
		final int fineDishChefId = fineDish.getChef().getId();
		
		return chefId == fineDishChefId && !fineDish.isDraft(); 
	}

	@Override
	public Collection<Memorandum> findMany(final Request<Memorandum> request) {
		assert request != null;

		final int masterId;

		masterId = request.getModel().getInteger("masterId");
		
		return this.repository.findMemorandumsByMasterId(masterId);
	}

	@Override
	public void unbind(final Request<Memorandum> request, final Collection<Memorandum> entities, final Model model) {
		assert request != null;
		assert entities != null;
		assert model != null;

		final int masterId;
		
		masterId = request.getModel().getInteger("masterId");
		
		model.setAttribute("masterId", masterId);
	}
	
	@Override
	public void unbind(final Request<Memorandum> request, final Memorandum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "sequenceNumber", "creationTime", "report", "link");
	}
}