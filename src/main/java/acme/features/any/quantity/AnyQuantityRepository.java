package acme.features.any.quantity;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.element.Element;
import acme.entities.quantity.Quantity;
import acme.entities.recipe.Recipe;
import acme.framework.repositories.AbstractRepository;

public interface AnyQuantityRepository extends AbstractRepository {

	@Query("select r from Recipe r where r.id = :id")
	Recipe findOneRecipeById(int id);

	@Query("select e from Element e")
	Collection<Element> findAllElements();

	@Query("select q from Quantity q where q.recipe.id = :id")
	Collection<Quantity> findManyAmountByRecipeId(int id);

	@Query("select distinct(q.element) from Quantity q where q.recipe.id = :id")
	Collection<Element> findManyElementByRecipeId(int id);

	@Query("select e from Element e where e.code = :code")
	Element findOneElementByCode(String code);

	@Query("select q from Quantity q where q.id = :id")
	Quantity findOneAmountById(int id);

}