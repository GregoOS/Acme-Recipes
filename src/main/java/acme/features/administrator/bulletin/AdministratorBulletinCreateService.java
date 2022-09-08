package acme.features.administrator.bulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletin.Bulletin;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractCreateService;
import acme.utility.TextValidator;

@Service
public class AdministratorBulletinCreateService implements AbstractCreateService<Administrator, Bulletin> {

	@Autowired
	AdministratorBulletinRepository repository;

	@Autowired
	TextValidator textValidator;

	@Override
	public boolean authorise(final Request<Bulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Bulletin> request, final Bulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "heading", "text", "critical", "link");

	}

	@Override
	public void unbind(final Request<Bulletin> request, final Bulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "moment", "heading", "text", "critical", "link");

	}

	@Override
	public Bulletin instantiate(final Request<Bulletin> request) {
		assert request != null;

		Bulletin res;
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		
		res = new Bulletin();
		res.setMoment(moment);
		
		return res;
	}

	@Override
	public void validate(final Request<Bulletin> request, final Bulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean confirmation;

		confirmation = request.getModel().getBoolean("confirmation");

		errors.state(request, confirmation, "confirmation", 
			"administrator.bulletin.error.confirmation");

		if (!errors.hasErrors("heading")) {
			String heading;
			heading = entity.getHeading();
			errors.state(request, !this.textValidator.spamChecker(heading), 
				"heading","administrator.bulletin.error.spam");
		}
		if (!errors.hasErrors("text")) {
			String text;
			text = entity.getText();
			errors.state(request, !this.textValidator.spamChecker(text), 
				"text", "administrator.bulletin.error.spam");
		}

	}

	@Override
	public void create(final Request<Bulletin> request, final Bulletin entity) {
		assert request != null;
		assert entity != null;

		Boolean critical;

		critical = request.getModel().getBoolean("critical");

		
		entity.setCritical(critical);
		this.repository.save(entity);

	}

}
