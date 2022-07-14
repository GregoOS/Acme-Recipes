package acme.features.any.recipe;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.amount.Amount;
import acme.entities.element.Element;
import acme.entities.recipe.Recipe;
import acme.framework.repositories.AbstractRepository;


@Repository
public interface AnyRecipeRepository extends AbstractRepository{

	@Query("select r from Recipe r where r.draft = false")
	Collection<Recipe> findAllRecipes();

	@Query("select r from Recipe r where r.id = :id")
	Recipe findRecipeById(int id);

	@Query("select distinct(a.element) from Amount a where a.recipe.id = :id")
	List<Element> findElementsByRecipeId(int id);
	
	@Query("select a from Amount a where a.recipe.id = :id")
	List<Amount> findAmountsByRecipeId(int id);
	
	@Query("select distinct(a.element.retailPrice.currency) from Amount a where a.recipe.id = :id")
	String findRetailPriceCurrencyByRecipeId(int id);
}