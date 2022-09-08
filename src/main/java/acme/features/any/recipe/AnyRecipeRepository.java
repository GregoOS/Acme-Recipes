package acme.features.any.recipe;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.element.Element;
import acme.entities.quantity.Quantity;
import acme.entities.recipe.Recipe;
import acme.framework.repositories.AbstractRepository;


@Repository
public interface AnyRecipeRepository extends AbstractRepository{

	@Query("select r from Recipe r where r.draft = false")
	Collection<Recipe> findAllRecipes();

	@Query("select r from Recipe r where r.id = :id")
	Recipe findRecipeById(int id);

	@Query("select distinct(q.element) from Quantity q where q.recipe.id = :id")
	List<Element> findElementsByRecipeId(int id);
	
	@Query("select q from Quantity q where q.recipe.id = :id")
	List<Quantity> findAmountsByRecipeId(int id);
	
	@Query("select distinct(q.element.retailPrice.currency) from Quantity q where q.recipe.id = :id")
	String findRetailPriceCurrencyByRecipeId(int id);
	
	@Query("select sum(q.amount*q.element.retailPrice.amount) from Quantity q where q.recipe.id = :id")
	Optional<Double> findRetailPriceAmountByRecipeId(int id);
}