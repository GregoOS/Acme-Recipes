package acme.features.chef.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.recipe.Recipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;

@Service
public class ChefRecipeCreateService implements AbstractCreateService<Chef, Recipe> {

	@Autowired
	protected ChefRecipeRepository repository;

	//@Autowired
	//protected SpamService spamService;

	@Override
	public boolean authorise(final Request<Recipe> request) {
		assert request != null;

		return true;
	}

	@Override
	public Recipe instantiate(final Request<Recipe> request) {
		assert request != null;

		Chef chef;
		Recipe res;

		chef = this.repository.findOneChefById(request.getPrincipal().getActiveRoleId());
		res = new Recipe();
		res.setDraft(true);
		res.setChef(chef);

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
	public void validate(final Request<Recipe> request, final Recipe entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("code")) {
			Recipe existing;

			existing = this.repository.findOneRecipeByCode(entity.getCode());
			errors.state(request, existing == null, "code", "chef.recipe.error.duplicated");
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
	public void create(final Request<Recipe> request, final Recipe entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
