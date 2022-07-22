package acme.features.chef.quantity;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.element.Element;
import acme.entities.quantity.Quantity;
import acme.entities.recipe.Recipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Chef;

@Service
public class ChefQuantityDeleteService implements AbstractDeleteService<Chef, Quantity> {

	@Autowired
	protected ChefQuantityRepository repository;

	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;

		boolean res;
		int id;
		Recipe recipe;

		id = request.getModel().getInteger("id");
		recipe = this.repository.findOneQuantityById(id).getRecipe();
		res = recipe.isDraft() && request.isPrincipal(recipe.getChef());

		return res;
	}

	@Override
	public Quantity findOne(final Request<Quantity> request) {
		assert request != null;

		Quantity res;
		int id;

		id = request.getModel().getInteger("id");
		res = this.repository.findOneQuantityById(id);

		return res;
	}

	@Override
	public void bind(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "recipe.heading", "amount", "elements");
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "recipe.heading", "amount");

		Collection<Element> allElements;

		allElements = this.repository.findAllElements();

		model.setAttribute("elements", allElements);
	}

	@Override
	public void validate(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<Quantity> request, final Quantity entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);
	}
}
