
package acme.features.chef.recipe;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.recipe.Recipe;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;

@Controller
public class ChefRecipeController extends AbstractController<Chef, Recipe> {

	@Autowired
	protected ChefRecipeListService list;

	@Autowired
	protected ChefRecipeShowService show;

	@Autowired
	protected ChefRecipeCreateService create;

	@Autowired
	protected ChefRecipeUpdateService update;

	@Autowired
	protected ChefRecipePublishService publish;

	@Autowired
	protected ChefRecipeDeleteService delete;

	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.list);
		super.addCommand("show", this.show);
		super.addCommand("create", this.create);
		super.addCommand("update", this.update);
		super.addCommand("delete", this.delete);
		super.addCommand("publish", "update", this.publish);

	}
}
