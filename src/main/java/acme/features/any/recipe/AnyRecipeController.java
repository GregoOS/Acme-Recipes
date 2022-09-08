package acme.features.any.recipe;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.recipe.Recipe;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;


@Controller
public class AnyRecipeController extends AbstractController<Any, Recipe> {

	@Autowired
	protected AnyRecipeListService list;

	@Autowired
	protected AnyRecipeShowService show;

	@PostConstruct
	protected void initialize() {
		super.addCommand("list", this.list);
		super.addCommand("show", this.show);
	}
}