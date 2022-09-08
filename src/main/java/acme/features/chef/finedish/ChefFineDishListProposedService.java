package acme.features.chef.finedish;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.finedish.FineDish;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;



@Service
public class ChefFineDishListProposedService implements AbstractListService<Chef, FineDish> {
	
	@Autowired
	protected ChefFineDishRepository repository;

	@Override
	public boolean authorise(final Request<FineDish> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<FineDish> findMany(final Request<FineDish> request) {
		final int id = request.getPrincipal().getActiveRoleId();
		
		Collection<FineDish> fineDishs;
		
		fineDishs = this.repository.findProposedFineDishesByChefId(id);
		
		return fineDishs;
	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "status", "code", "request", "budget", "creationDate", "startDate", "endDate", "link");
	}

}
