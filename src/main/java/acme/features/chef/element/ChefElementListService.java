package acme.features.chef.element;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.element.Element;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;

@Service
public class ChefElementListService implements AbstractListService<Chef, Element> {

	@Autowired
	protected ChefElementRepository repository;

	@Override
	public boolean authorise(final Request<Element> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<Element> findMany(final Request<Element> request) {
		assert request != null;

		Integer id;
		Collection<Element> result;

		id = request.getPrincipal().getActiveRoleId();
		result = this.repository.findElementsByChefId(id);

		return result;
	}

	@Override
	public void unbind(final Request<Element> request, final Element entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "code", "description", "retailPrice", "link", "type");

		model.setAttribute("published", !entity.isDraft());
	}
}
