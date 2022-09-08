package acme.features.chef.quantity;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.element.Element;
import acme.entities.quantity.Quantity;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefQuantityShowService implements AbstractShowService<Chef, Quantity> {

	@Autowired
	protected ChefQuantityRepository repository;

	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;

		boolean res;
		int id;
		Element element;

		id = request.getModel().getInteger("id");

		element = this.repository.findOneQuantityById(id).getElement();

		res = element != null && (!element.isDraft() || request.isPrincipal(element.getChef()));

		return res;
	}

	@Override
	public Quantity findOne(final Request<Quantity> request) {
		assert request != null;

		int id;
		Quantity result;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneQuantityById(id);

		return result;
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "recipe.heading", "amount");

		int recipeId;
		Element selectedElement;
		String toolkitCurrency;
		Collection<Element> elements;
		Collection<Element> elementsInRecipe;
		boolean showUpdateElement;

		selectedElement = entity.getElement();
		recipeId = entity.getRecipe().getId();
		elementsInRecipe = this.repository.findManyElementByRecipeId(recipeId);
		showUpdateElement = (entity.getRecipe().isDraft() && request.isPrincipal(entity.getRecipe().getChef()));

 		
 		if (elementsInRecipe.isEmpty()) {
			elements = this.repository.findAllElements();
		} else {
			toolkitCurrency = elementsInRecipe.iterator().next().getRetailPrice().getCurrency();
			elements = this.repository.findElementsByCurrency(toolkitCurrency);
		}
		model.setAttribute("elements", elements);
		model.setAttribute("selected", selectedElement);
		model.setAttribute("showUpdateElement", showUpdateElement);
		
	}
}
