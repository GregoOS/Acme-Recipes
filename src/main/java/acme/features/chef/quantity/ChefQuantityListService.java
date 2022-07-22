package acme.features.chef.quantity;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.element.Element;
import acme.entities.quantity.Quantity;
import acme.entities.recipe.Recipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.helpers.CollectionHelper;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;

@Service
public class ChefQuantityListService implements AbstractListService<Chef, Quantity> {

	@Autowired
	protected ChefQuantityRepository repository;

	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;

		int id;
		Recipe requested;

		id = request.getModel().getInteger("recipeId");
		requested = this.repository.findOneRecipeById(id);

		return !requested.isDraft() || request.isPrincipal(requested.getChef());
	}

	@Override
	public Collection<Quantity> findMany(final Request<Quantity> request) {
		assert request != null;

		int id;
		Collection<Quantity> res;

		id = request.getModel().getInteger("recipeId");
		res = this.repository.findManyQuantityByRecipeId(id);

		return res;
	}

	@Override
	public void unbind(final Request<Quantity> request, final Collection<Quantity> entities, final Model model) {
		assert request != null;
		assert !CollectionHelper.someNull(entities);
		assert model != null;

		int id;
		Recipe recipe;
		boolean showAddItem;

		id = request.getModel().getInteger("recipeId");
		recipe = this.repository.findOneRecipeById(id);
		showAddItem = (recipe.isDraft() && request.isPrincipal(recipe.getChef()));

		model.setAttribute("recipeId", id);
		model.setAttribute("showAddItem", showAddItem);
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
		model.setAttribute("code", element.getCode());
		model.setAttribute("description", element.getDescription());
		model.setAttribute("retailPrice", element.getRetailPrice());
		model.setAttribute("amountUnit", element.getAmountUnit());
		model.setAttribute("link", element.getLink());
		model.setAttribute("type", element.getType());
	}
}
