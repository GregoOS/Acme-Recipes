package acme.features.any.element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.element.Element;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyElementShowService implements AbstractShowService<Any, Element> {

	@Autowired
	protected AnyElementRepository repository;

	@Override
	public boolean authorise(final Request<Element> request) {
		assert request != null;

		int id;
		Element element;
		boolean res;

		id = request.getModel().getInteger("id");
		element = this.repository.findOneById(id);
		
		res=!element.isDraft();

		return res;
	}

	@Override
	public Element findOne(final Request<Element> request) {
		assert request != null;

		int id;

		id = request.getModel().getInteger("id");

		return this.repository.findOneById(id);
	}

	@Override
	public void unbind(final Request<Element> request, final Element entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "code", "description", "retailPrice", "link", "type");
	}
}
