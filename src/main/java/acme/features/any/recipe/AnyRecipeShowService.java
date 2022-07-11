package acme.features.any.recipe;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.amount.Amount;
import acme.entities.element.Type;
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

		String currency = this.repository.findRetailPriceCurrencyByRecipeId(id);
		double amount;

		if (currency == null) {
			amount = 0.0;
			currency = "";
		} else {
			amount = this.getRetailPriceAmountByRecipeId(id);
		}

		final Money retailPrice = new Money();
		retailPrice.setAmount(amount);
		retailPrice.setCurrency(currency);

		model.setAttribute("retailPrice", retailPrice);
	}
	
	public Double getRetailPriceAmountByRecipeId(final int id) {
		final List<Amount> amounts=this.repository.findAmountsByRecipeId(id);
		Double res=0.;
		for(final Amount amount:amounts) {
			if(amount.getElement().getType()==Type.INGREDIENT) {
				res=res+amount.getElement().getRetailPrice().getAmount();
			}else {
				res=res+amount.getElement().getRetailPrice().getAmount()*amount.getNumber();
			}
		}
		
		
		return res;
	}
}