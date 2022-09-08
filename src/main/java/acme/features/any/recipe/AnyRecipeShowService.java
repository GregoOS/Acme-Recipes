package acme.features.any.recipe;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.recipe.Recipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;



@Service
public class AnyRecipeShowService implements AbstractShowService<Any, Recipe>{

	@Autowired
	protected AnyRecipeRepository repository;

	@Override
	public boolean authorise(final Request<Recipe> request) {
		assert request != null;
		
		int id;
		Recipe Recipe;
		boolean res;
		
		id = request.getModel().getInteger("id");
		Recipe = this.repository.findRecipeById(id);
		res=!Recipe.isDraft();
		return res;
	}
	
	@Override
	public Recipe findOne(final Request<Recipe> request) {
	
		assert request != null;

		int id;
		
		id = request.getModel().getInteger("id");

		return this.repository.findRecipeById(id);
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