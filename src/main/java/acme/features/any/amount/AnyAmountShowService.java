package acme.features.any.amount;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.amount.Amount;
import acme.entities.element.Element;
import acme.entities.recipe.Recipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyAmountShowService implements AbstractShowService<Any, Amount> {

	@Autowired
	protected AnyAmountRepository repository;

	@Override
	public boolean authorise(final Request<Amount> request) {
		assert request != null;

		boolean result;
		int id;
		Recipe recipe;

		id = request.getModel().getInteger("id");

		recipe = this.repository.findOneAmountById(id).getRecipe();

		result = recipe != null && !recipe.isDraft();

		return result;
	}

	@Override
	public Amount findOne(final Request<Amount> request) {
		assert request != null;

		int id;
		Amount result;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneAmountById(id);

		return result;
	}

	@Override
	public void unbind(final Request<Amount> request, final Amount entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "number", "unit");

		Element element;

		model.setAttribute("recipeTitle", entity.getRecipe().getHeading());

		element = entity.getElement();
		model.setAttribute("elements", Arrays.asList(element));

	}
}
