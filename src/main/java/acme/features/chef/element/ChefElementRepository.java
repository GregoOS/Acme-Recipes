package acme.features.chef.element;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.element.Element;
import acme.entities.quantity.Quantity;
import acme.entities.recipe.Recipe;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;

@Repository
public interface ChefElementRepository extends AbstractRepository {

	@Query("select e from Element e where e.chef.id = :id")
	Collection<Element> findElementsByChefId(int id);

	@Query("select e from Element e where e.id = :id")
	Element findElementById(int id);

	@Query("select r from Recipe r where r.id = :masterId")
	Recipe findOneRecipeById(int masterId);

	@Query("select distinct(q.element) from Quantity q where q.recipe.id = :masterId")
	Collection<Element> findElementsByRecipeId(int masterId);

	@Query("select q.recipe from Quantity q where q.element.id = :id")
	Recipe findOneRecipeByElementId(int id);

	@Query("select e from Element e where e.code = :code")
	Element findOneElementByCode(String code);

	@Query("select q from Quantity q where q.element.id = :elementId and q.recipe.id = :recipeId")
	Quantity findAmountInRecipe(int elementId, int recipeId);

	@Query("select sc.currency from SystemConfiguration sc")
	String findSystemCurrency();

	@Query("select c from Chef c where c.id = :id")
	Chef findOneChefById(int id);

	@Query("select count(sc) > 0 from SystemConfiguration sc where sc.acceptedCurrencies LIKE %:currency%")
	boolean isAcceptedCurrency(String currency);
}
