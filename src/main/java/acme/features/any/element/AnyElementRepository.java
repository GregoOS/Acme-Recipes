package acme.features.any.element;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.element.Element;
import acme.entities.recipe.Recipe;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyElementRepository extends AbstractRepository {
	
	@Query("select r from Recipe r where r.id = :masterId")
	Recipe findOneRecipeById(int masterId);

	@Query("select e from Element e where e.draft = false")
	Collection<Element> findElements();

	@Query("select e from Element e where e.id = :id")
	Element findOneById(int id);
}