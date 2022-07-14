package acme.features.chef.element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.element.Element;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;

@Service
public class ChefElementPublishService implements AbstractUpdateService<Chef, Element> {

	@Autowired
	protected ChefElementRepository repository;

	// @Autowired
	// protected SpamService spamService;

	@Override
	public boolean authorise(final Request<Element> request) {
		assert request != null;

		boolean res;
		int id;
		Element element;
		Chef chef;

		id = request.getModel().getInteger("id");
		element = this.repository.findElementById(id);
		chef = element.getChef();
		res = element.isDraft() && request.isPrincipal(chef);

		return res;
	}

	@Override
	public Element findOne(final Request<Element> request) {
		assert request != null;

		Element result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findElementById(id);

		return result;
	}

	@Override
	public void bind(final Request<Element> request, final Element entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void validate(final Request<Element> request, final Element entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void unbind(final Request<Element> request, final Element entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "code", "description", "retailPrice", "link", "type", "draft");
	}

	@Override
	public void update(final Request<Element> request, final Element entity) {
		assert request != null;
		assert entity != null;

		entity.setDraft(false);
		this.repository.save(entity);
	}
}
