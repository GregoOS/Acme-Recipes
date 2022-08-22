package acme.features.chef.quantity;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.element.Element;
import acme.entities.element.Type;
import acme.entities.quantity.Quantity;
import acme.entities.recipe.Recipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;

@Service
public class ChefQuantityCreateService implements AbstractCreateService<Chef, Quantity> {

	@Autowired
	protected ChefQuantityRepository repository;

	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;

		boolean res;
		int recipeId;
		Recipe recipe;

		recipeId = request.getModel().getInteger("recipeId");
		recipe = this.repository.findOneRecipeById(recipeId);
		res = (recipe != null && recipe.isDraft() && request.isPrincipal(recipe.getChef()));

		return res;
	}

	@Override
	public Quantity instantiate(final Request<Quantity> request) {
		assert request != null;

		Quantity res;
		int recipeId;
		Recipe recipe;

		recipeId = request.getModel().getInteger("recipeId");
		recipe = this.repository.findOneRecipeById(recipeId);

		res = new Quantity();
		res.setRecipe(recipe);

		return res;
	}

	@Override
	public void bind(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "recipe.heading", "amount");

		Model model;
		Element selectedElement;

		model = request.getModel();
		selectedElement = this.repository.findOneElementByCode(model.getString("elements"));

		entity.setElement(selectedElement);
	}

	@Override
	public void validate(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Model model;
		Integer amount;
		Element selectedElement;

		model = request.getModel();
		amount = model.getInteger("amount");
		selectedElement = entity.getElement();

		if (!errors.hasErrors("amount") && selectedElement.getType().equals(Type.UTENSIL)) {
			errors.state(request, amount==1, "amount",
					"chef.quantity.error.toomuchutensil");
		}

		if (!errors.hasErrors("elements")) {
			int recipeId;
			Collection<Element> elementsInRecipe;
			String newElementCurrency;

			newElementCurrency = selectedElement.getRetailPrice().getCurrency();

			recipeId = request.getModel().getInteger("recipeId");
			elementsInRecipe = this.repository.findManyElementByRecipeId(recipeId);

			errors.state(request,elementsInRecipe.isEmpty() || elementsInRecipe.iterator().next().getRetailPrice().getCurrency().equals(newElementCurrency),"*", "chef.quantity.error.wrongcurrency");

			errors.state(request, !elementsInRecipe.contains(selectedElement), "*","chef.quantity.error.repeatedelement");

			errors.state(request, !selectedElement.isDraft(), "*", "chef.quantity.error.draftmodeelement");
		}
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "recipe.heading", "amount");

		int recipeId;
		Collection<Element> elementsInRecipe;
		Collection<Element> elements;
		Element selectedElement;
		String recipeCurrency;

		selectedElement = entity.getElement();
		recipeId = request.getModel().getInteger("recipeId");
		elementsInRecipe = this.repository.findManyElementByRecipeId(recipeId);

		if (elementsInRecipe.isEmpty()) {
			elements = this.repository.findAllElements();
		} else {
			recipeCurrency = elementsInRecipe.iterator().next().getRetailPrice().getCurrency();
			elements = this.repository.findElementsByCurrency(recipeCurrency);
		}

		elements.removeAll(elementsInRecipe);

		model.setAttribute("elements", elements);
		model.setAttribute("selected", selectedElement);
		model.setAttribute("recipeId", recipeId);
	}

	@Override
	public void create(final Request<Quantity> request, final Quantity entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}