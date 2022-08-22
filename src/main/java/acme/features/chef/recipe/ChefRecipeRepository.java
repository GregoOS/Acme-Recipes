package acme.features.chef.recipe;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import acme.entities.element.Element;
import acme.entities.quantity.Quantity;
import acme.entities.recipe.Recipe;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;

public interface ChefRecipeRepository extends AbstractRepository {

	@Query("select r from Recipe r where r.id = :id")
	Recipe findOneRecipeById(int id);

	@Query("select distinct(q.element.retailPrice.currency) from Quantity q")
	Collection<String> currencies();

	@Query("select distinct(r) from Recipe r where r.chef.id = :id")
	Collection<Recipe> findRecipesByChefId(int id);

	@Query("select distinct(q.element.retailPrice.currency) from Quantity q where q.recipe.id = :id")
	String findRetailPriceCurrencyByRecipeId(int id);

	@Query("select distinct(q.element) from Quantity q where q.recipe.id = :id")
	Collection<Element> findElementsByRecipeId(int id);

	@Query("select c from Chef c where c.id = :id")
	Chef findOneChefById(int id);

	@Query("select r from Recipe r where r.code = :code")
	Recipe findOneRecipeByCode(String code);

	@Query("select q from Quantity q where q.recipe.id = :id")
	Collection<Quantity> findAmountsByRecipeId(int id);
	
	@Query("select sum(q.amount*q.element.retailPrice.amount) from Quantity q where q.recipe.id = :id")
	Optional<Double> findRetailPriceAmountByRecipeId(int id);
}
