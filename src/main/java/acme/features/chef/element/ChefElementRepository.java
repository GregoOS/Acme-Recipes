package acme.features.chef.element;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.element.Element;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ChefElementRepository extends AbstractRepository {
	
	@Query("select e from Element e where e.id = :id")
	Element findElementById(int id);
	
	@Query("select e from Element e where e.inventor.id = :id")
	Collection<Element> findElementsByChefId(int id);

}
