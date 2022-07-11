package acme.features.any.amount;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.amount.Amount;
import acme.entities.element.Element;
import acme.entities.recipe.Recipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.helpers.CollectionHelper;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyAmountListService implements AbstractListService<Any, Amount> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyAmountRepository repository;

	@Override
	public boolean authorise(final Request<Amount> request) {
		assert request != null;

		int id;
		Recipe requested;

		id = request.getModel().getInteger("recipeId");
		requested = this.repository.findOneRecipeById(id);

		return !requested.isDraft();
	}

	@Override
	public Collection<Amount> findMany(final Request<Amount> request) {
		assert request != null;

		int id;
		Collection<Amount> result;

		id = request.getModel().getInteger("recipeId");
		result = this.repository.findManyAmountByRecipeId(id);

		return result;
	}

	@Override
	public void unbind(final Request<Amount> request, final Collection<Amount> entities,
			final Model model) {
		assert request != null;
		assert !CollectionHelper.someNull(entities);
		assert model != null;

		int id;

		id = request.getModel().getInteger("recipeId");

		model.setAttribute("recipeId", id);
	}

	@Override
	public void unbind(final Request<Amount> request, final Amount entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "number","unit");

		Element element;

		element = entity.getElement();
		model.setAttribute("name", element.getName());
		model.setAttribute("recipeTitle", entity.getRecipe().getHeading());
		model.setAttribute("code", element.getCode());
		model.setAttribute("retailPrice", element.getRetailPrice());
		model.setAttribute("type", element.getType());
	}
}
