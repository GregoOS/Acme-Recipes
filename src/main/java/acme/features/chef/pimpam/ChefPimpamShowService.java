package acme.features.chef.pimpam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.pimpam.Pimpam;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefPimpamShowService implements AbstractShowService<Chef, Pimpam> {

	@Autowired
	protected ChefPimpamRepository chefPimpamRepository;
	
	@Override
	public boolean authorise(final Request<Pimpam> request) {
		assert request != null;
		
		boolean res;
		int pimpamId;
		Pimpam pimpam;
		
		pimpamId = request.getModel().getInteger("id");
		pimpam = this.chefPimpamRepository.findOnePimpamById(pimpamId);
		final Chef chef = pimpam.getElement().getChef();
		res = pimpam != null && request.isPrincipal(chef);
		
		return res;
	}

	@Override
	public Pimpam findOne(final Request<Pimpam> request) {
		assert request != null;
		
		Pimpam res;
		int id;
		
		id=request.getModel().getInteger("id");
        res=this.chefPimpamRepository.findOnePimpamById(id);
		
		return res;
	}

	@Override
	public void unbind(final Request<Pimpam> request, final Pimpam entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "instantiationMoment", "title", "description", "startDate", "finishDate", "budget", "link");
		model.setAttribute("elementName", entity.getElement().getName());
		model.setAttribute("chefName", entity.getElement().getChef().getIdentity().getFullName());
	}
}