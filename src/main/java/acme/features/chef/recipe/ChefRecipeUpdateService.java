package acme.features.chef.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.recipe.Recipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;

@Service
public class ChefRecipeUpdateService implements AbstractUpdateService<Chef, Recipe> {

	@Autowired
	protected ChefRecipeRepository repository;

	//@Autowired
	//protected SpamService spamService;

	@Override
	public boolean authorise(final Request<Recipe> request) {
		assert request != null;

		boolean res;
		int id;
		Recipe recipe;

		id = request.getModel().getInteger("id");
		recipe = this.repository.findOneRecipeById(id);
		res = (recipe != null && recipe.isDraft() && request.isPrincipal(recipe.getChef()));

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
	public Recipe findOne(final Request<Recipe> request) {
		assert request != null;

		Recipe result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneRecipeById(id);

		return result;
	}

	@Override
	public void validate(final Request<Recipe> request, final Recipe entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("code")) {
			Recipe existing;
			Integer id;

			id = request.getModel().getInteger("id");
			existing = this.repository.findOneRecipeByCode(entity.getCode());

			errors.state(request, existing == null || existing.getId() == id, "code",
					"chef.recipe.error.duplicated");
		}
		//if (!errors.hasErrors("heading")) {
		//	errors.state(request, !this.spamService.isSpam(entity.getHeading()), "heading","chef.recipe.error.spam");
		//}
		//if (!errors.hasErrors("description")) {
		//	errors.state(request, !this.spamService.isSpam(entity.getDescription()), "description","chef.recipe.error.spam");
		//}
		//if (!errors.hasErrors("preparationNotes")) {
		//	errors.state(request, !this.spamService.isSpam(entity.getPreparationNotes()), "preparationNotes","chef.recipe.error.spam");
		//}
	}

	@Override
	public void unbind(final Request<Recipe> request, final Recipe entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "heading", "description", "preparationNotes", "link", "draft");
	}

	@Override
	public void update(final Request<Recipe> request, final Recipe entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
