package acme.features.chef.quantity;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.element.Element;
import acme.entities.quantity.Quantity;
import acme.entities.recipe.Recipe;
import acme.framework.repositories.AbstractRepository;

public interface ChefQuantityRepository extends AbstractRepository {

	@Query("select r from Recipe r where r.id = :id")
	Recipe findOneRecipeById(int id);

	@Query("select e from Element e where e.draft = false")
	Collection<Element> findAllElements();

	@Query("select d from Element d where d.draft = false and d.retailPrice.currency = :currency")
	Collection<Element> findElementsByCurrency(String currency);

	@Query("select q from Quantity q where q.recipe.id = :id")
	Collection<Quantity> findManyQuantityByRecipeId(int id);

	@Query("select distinct(q.element) from Quantity q where q.recipe.id = :id")
	Collection<Element> findManyElementByRecipeId(int id);

	@Query("select e from Element e where e.code = :code and e.draft = false")
	Element findOneElementByCode(String code);

	@Query("select q from Quantity q where q.id = :id")
	Quantity findOneQuantityById(int id);

	@Query("select q.element from Quantity q where q.id = :id")
	Element findOneElementByQuantityId(int id);
}
