package acme.features.chef.recipe;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.amount.Amount;
import acme.entities.element.Element;
import acme.entities.recipe.Recipe;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;

public interface ChefRecipeRepository extends AbstractRepository {

	@Query("select r from Recipe r where r.id = :id")
	Recipe findOneRecipeById(int id);

	@Query("select distinct(a.element.retailPrice.currency) from Amount a")
	Collection<String> currencies();

	@Query("select distinct(r) from Recipe r where r.chef.id = :id")
	Collection<Recipe> findRecipesByChefId(int id);

	@Query("select distinct(a.element.retailPrice.currency) from Amount a where a.recipe.id = :id")
	String findRetailPriceCurrencyByRecipeId(int id);

	@Query("select distinct(a.element) from Amount a where a.recipe.id = :id")
	Collection<Element> findElementsByRecipeId(int id);

	@Query("select c from Chef c where c.id = :id")
	Chef findOneChefById(int id);

	@Query("select r from Recipe r where r.code = :code")
	Recipe findOneRecipeByCode(String code);

	@Query("select a from Amount a where a.recipe.id = :id")
	Collection<Amount> findAmountsByRecipeId(int id);
}
