package acme.features.chef.element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.element.Element;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Chef;

@Service
public class ChefElementDeleteService implements AbstractDeleteService<Chef, Element> {

	@Autowired
	protected ChefElementRepository repository;

	@Override
	public boolean authorise(final Request<Element> request) {
		assert request != null;

		boolean res;
		int id;
		Element element;

		id = request.getModel().getInteger("id");
		element = this.repository.findElementById(id);

		res = (element.isDraft() && request.isPrincipal(element.getChef()));

		return res;
	}

	@Override
	public Element findOne(final Request<Element> request) {
		assert request != null;

		Element res;
		int id;

		id = request.getModel().getInteger("id");
		res = this.repository.findElementById(id);

		return res;
	}

	@Override
	public void bind(final Request<Element> request, final Element entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "name", "code", "description", "retailPrice", "link", "type","amountUnit");
	}

	@Override
	public void unbind(final Request<Element> request, final Element entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "code", "description", "retailPrice", "link", "type", "draft","amountUnit");
	}

	@Override
	public void validate(final Request<Element> request, final Element entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<Element> request, final Element entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);
	}
}
