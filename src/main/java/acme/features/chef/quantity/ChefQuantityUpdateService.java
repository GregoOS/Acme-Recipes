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
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;

@Service
public class ChefQuantityUpdateService implements AbstractUpdateService<Chef, Quantity> {

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
		res = (recipe != null && recipe.isDraft() && request.isPrincipal(recipe.getChef()));

		return res;
	}

	@Override
	public Quantity findOne(final Request<Quantity> request) {
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

		model.setAttribute("selected", selectedElement);

		if (!errors.hasErrors("amount")) {
			errors.state(request, !(selectedElement.getType().equals(Type.UTENSIL) || amount == 1), "amount",
					"chef.quantity.error.toomuchutensil");
		}

		if (!errors.hasErrors("elements")) {
			int recipeId;
			Collection<Element> elementsInRecipe;
			String newElementCurrency;
			Element previousElement;

			newElementCurrency = selectedElement.getRetailPrice().getCurrency();

			recipeId = entity.getRecipe().getId();
			elementsInRecipe = this.repository.findManyElementByRecipeId(recipeId);
			previousElement = this.repository.findOneElementByQuantityId(entity.getId());

			errors.state(
					request, elementsInRecipe.isEmpty() || elementsInRecipe.iterator().next().getRetailPrice()
							.getCurrency().equals(newElementCurrency),
					"retailPrice", "chef.quantity.error.wrongcurrency");

			errors.state(request,
					!elementsInRecipe.contains(selectedElement) || selectedElement.equals(previousElement), "*",
					"chef.quantity.error.repeated-Element");

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
		recipeId = entity.getRecipe().getId();
		elementsInRecipe = this.repository.findManyElementByRecipeId(recipeId);

		if (elementsInRecipe.isEmpty()) {
			elements = this.repository.findAllElements();
		} else {
			recipeCurrency = elementsInRecipe.iterator().next().getRetailPrice().getCurrency();
			elements = this.repository.findElementsByCurrency(recipeCurrency);
		}

		elements.removeAll(elementsInRecipe);

		elements.add(selectedElement);

		model.setAttribute("elements", elements);
		model.setAttribute("selected", selectedElement);
		model.setAttribute("draftMode", entity.getRecipe().isDraft());
	}

	@Override
	public void update(final Request<Quantity> request, final Quantity entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}