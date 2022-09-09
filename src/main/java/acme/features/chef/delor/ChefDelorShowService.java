package acme.features.chef.delor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.delor.Delor;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefDelorShowService implements AbstractShowService<Chef, Delor> {

	@Autowired
	protected ChefDelorRepository chefDelorRepository;
	
	@Override
	public boolean authorise(final Request<Delor> request) {
		assert request != null;
		
		boolean res;
		int delorId;
		Delor delor;
		
		delorId = request.getModel().getInteger("id");
		delor = this.chefDelorRepository.findOneDelorById(delorId);
		res = delor != null && request.isPrincipal(delor.getElement().getChef());
		
		return res;
	}

	@Override
	public Delor findOne(final Request<Delor> request) {
		assert request != null;
		
		Delor res;
		int id;
		
		id=request.getModel().getInteger("id");
        res=this.chefDelorRepository.findOneDelorById(id);
		
		return res;
	}

	@Override
	public void unbind(final Request<Delor> request, final Delor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "keylet", "instantiationMoment", "subject", "explanation", "startPeriod", "finishPeriod", "income", "moreInfo");
		model.setAttribute("element", entity.getElement());
	}
}