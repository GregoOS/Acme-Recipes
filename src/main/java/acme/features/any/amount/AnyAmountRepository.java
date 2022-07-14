package acme.features.any.amount;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.amount.Amount;
import acme.entities.element.Element;
import acme.entities.recipe.Recipe;
import acme.framework.repositories.AbstractRepository;

public interface AnyAmountRepository extends AbstractRepository {

	@Query("select r from Recipe r where r.id = :id")
	Recipe findOneRecipeById(int id);

	@Query("select e from Element e")
	Collection<Element> findAllElements();

	@Query("select a from Amount a where a.Recipe.id = :id")
	Collection<Amount> findManyAmountByRecipeId(int id);

	@Query("select distinct(a.Element) from Amount a where a.recipe.id = :id")
	Collection<Element> findManyElementByRecipeId(int id);

	@Query("select e from Element e where e.code = :code")
	Element findOneElementByCode(String code);

	@Query("select a from Amount a where a.id = :id")
	Amount findOneAmountById(int id);

}