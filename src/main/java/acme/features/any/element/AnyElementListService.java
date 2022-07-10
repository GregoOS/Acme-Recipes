package acme.features.any.element;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.element.Element;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyElementListService implements AbstractListService<Any, Element>{
	
	@Autowired
	protected AnyElementRepository repository;

	@Override
	public boolean authorise(final Request<Element> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public Collection<Element> findMany(final Request<Element> request) {
		assert request != null;
		

		Collection<Element> result;
		
		result = this.repository.findElements();
		
		return result;
	}

	@Override
	public void unbind(final Request<Element> request, final Element entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "code", "description", "retailPrice", "link", "type");
	}
}
