
package acme.features.chef.recipe;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.quantity.Quantity;
import acme.entities.recipe.Recipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Chef;

@Service
public class ChefRecipeDeleteService implements AbstractDeleteService<Chef, Recipe> {

	@Autowired
	protected ChefRecipeRepository repository;

	@Override
	public boolean authorise(final Request<Recipe> request) {
		assert request != null;

		boolean res;
		int id;
		Recipe recipe;
		Chef chef;

		id = request.getModel().getInteger("id");
		recipe = this.repository.findOneRecipeById(id);
		chef = recipe.getChef();
		res = recipe.isDraft() && request.isPrincipal(chef);

		return res;
	}

	@Override
	public void bind(final Request<Recipe> request, final Recipe entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code", "heading", "description", "preparationNotes", "link");
	}

	@Override
	public void unbind(final Request<Recipe> request, final Recipe entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "heading", "description", "preparationNotes", "link", "draft");
	}

	@Override
	public Recipe findOne(final Request<Recipe> request) {
		assert request != null;

		int id;
		Recipe result;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneRecipeById(id);

		return result;
	}

	@Override
	public void validate(final Request<Recipe> request, final Recipe entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<Recipe> request, final Recipe entity) {
		assert request != null;
		assert entity != null;

		int id;
		Collection<Quantity> amounts;

		id = request.getModel().getInteger("id");
		amounts = this.repository.findAmountsByRecipeId(id);

		this.repository.deleteAll(amounts);
		this.repository.delete(entity);
	}
}
