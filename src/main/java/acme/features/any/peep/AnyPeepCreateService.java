package acme.features.any.peep;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.peep.Peep;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractCreateService;

@Service
public class AnyPeepCreateService implements AbstractCreateService<Any, Peep>{

	@Autowired
	AnyPeepRepository repository;
	
	//@Autowired
	//protected SpamService spamService;

	@Override
	public boolean authorise(final Request<Peep> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Peep> request, final Peep entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "heading","writer","text","email");
	}

	@Override
	public void unbind(final Request<Peep> request, final Peep entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "moment","heading","writer","text","email");
	}

	@Override
	public Peep instantiate(final Request<Peep> request) {
		assert request != null;

		Peep res;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		res = new Peep();
		res.setMoment(moment);

		return res;
	}

	@Override
	public void validate(final Request<Peep> request, final Peep entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean confirmation;

		confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");
		
		if (!errors.hasErrors("heading")) {
			//errors.state(request, !this.spamService.isSpam(entity.getTitle()), "title","any.peep.form.spam");
		}
		if (!errors.hasErrors("writer")) {
			//errors.state(request, !this.spamService.isSpam(entity.getAuthor()), "author","any.peep.form.spam");
		}
		if (!errors.hasErrors("text")) {
			//errors.state(request, !this.spamService.isSpam(entity.getBody()), "body","any.peep.form.spam");
		}
		if (!errors.hasErrors("email")) {
			//errors.state(request, !this.spamService.isSpam(entity.getEmail()), "email","any.peep.form.spam");
		}
		
	}

	@Override
	public void create(final Request<Peep> request, final Peep entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);
	}

}