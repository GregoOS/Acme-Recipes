package acme.features.any.quantity;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.element.Element;
import acme.entities.quantity.Quantity;
import acme.entities.recipe.Recipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.helpers.CollectionHelper;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyQuantityListService implements AbstractListService<Any, Quantity> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyQuantityRepository repository;

	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;

		int id;
		Recipe requested;

		id = request.getModel().getInteger("recipeId");
		requested = this.repository.findOneRecipeById(id);

		return !requested.isDraft();
	}

	@Override
	public Collection<Quantity> findMany(final Request<Quantity> request) {
		assert request != null;

		int id;
		Collection<Quantity> result;

		id = request.getModel().getInteger("recipeId");
		result = this.repository.findManyAmountByRecipeId(id);

		return result;
	}

	@Override
	public void unbind(final Request<Quantity> request, final Collection<Quantity> entities,
			final Model model) {
		assert request != null;
		assert !CollectionHelper.someNull(entities);
		assert model != null;

		int id;

		id = request.getModel().getInteger("recipeId");

		model.setAttribute("recipeId", id);
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "amount");

		Element element;

		element = entity.getElement();
		model.setAttribute("name", element.getName());
		model.setAttribute("recipeHeading", entity.getRecipe().getHeading());
		model.setAttribute("code", element.getCode());
		model.setAttribute("retailPrice", element.getRetailPrice());
		model.setAttribute("type", element.getType());
	}
}
