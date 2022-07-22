package acme.features.chef.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.recipe.Recipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefRecipeShowService implements AbstractShowService<Chef, Recipe> {

	@Autowired
	protected ChefRecipeRepository repository;

	@Override
	public boolean authorise(final Request<Recipe> request) {

		assert request != null;

		int id;
		final Recipe recipe;

		id = request.getModel().getInteger("id");
		recipe = this.repository.findOneRecipeById(id);
		return recipe != null && (request.isPrincipal(recipe.getChef()) || !recipe.isDraft());
	}

	@Override
	public Recipe findOne(final Request<Recipe> request) {
		assert request != null;

		final int id = request.getModel().getInteger("id");
		return this.repository.findOneRecipeById(id);
	}

	@Override
	public void unbind(final Request<Recipe> request, final Recipe entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		final int id = request.getModel().getInteger("id");
		request.unbind(entity, model, "code", "heading", "description", "preparationNotes", "link", "draft");

		String currency;
		double amount;

		amount = this.repository.findRetailPriceAmountByRecipeId(id);
		if (amount<=0.) {
			currency = "";
		}else {
			currency = this.repository.findRetailPriceCurrencyByRecipeId(id);
		}

		final Money retailPrice = new Money();
		retailPrice.setAmount(amount);
		retailPrice.setCurrency(currency);

		model.setAttribute("retailPrice", retailPrice);
	}

}
