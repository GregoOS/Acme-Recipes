package acme.features.any.quantity;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.element.Element;
import acme.entities.quantity.Quantity;
import acme.entities.recipe.Recipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyQuantityShowService implements AbstractShowService<Any, Quantity> {

	@Autowired
	protected AnyQuantityRepository repository;

	@Override
	public boolean authorise(final Request<Quantity> request) {
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
	public Quantity findOne(final Request<Quantity> request) {
		assert request != null;

		int id;
		Quantity result;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneAmountById(id);

		return result;
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "amount");

		Element element;

		model.setAttribute("recipeHeading", entity.getRecipe().getHeading());

		element = entity.getElement();
		model.setAttribute("elements", Arrays.asList(element));

	}
}
