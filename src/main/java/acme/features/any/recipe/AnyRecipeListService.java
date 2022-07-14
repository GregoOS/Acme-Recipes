package acme.features.any.recipe;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.element.Element;
import acme.entities.recipe.Recipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyRecipeListService implements AbstractListService<Any, Recipe>{

	@Autowired
	protected AnyRecipeRepository repository;

	@Override
	public boolean authorise(final Request<Recipe> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<Recipe> findMany(final Request<Recipe> request) {
		assert request != null;

		
		Collection<Recipe> result;
		result = this.repository.findAllRecipes();
		return result;
	}

	@Override
	public void unbind(final Request<Recipe> request, final Recipe entity, final Model model) {
		
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "heading", "description", "preparationNotes", "link", "draft");
		
		final int id = entity.getId();
		final List<Element> elements = this.repository.findElementsByRecipeId(id);
		
		final String payload = String.join(", ", elements.stream().map(x->x.getCode()+";"+x.getName()+";"+x.getType().name()).collect(Collectors.toList()));
		
		model.setAttribute("payload", payload);
	}
}
