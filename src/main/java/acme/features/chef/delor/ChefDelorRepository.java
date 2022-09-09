package acme.features.chef.delor;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.delor.Delor;
import acme.entities.element.Element;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ChefDelorRepository extends AbstractRepository {
	
	@Query("SELECT p FROM Delor p WHERE p.element.chef.id = :id") //Change for COMPONENT if needed
	Collection<Delor> findDelorByChefId(int id);
	
	@Query("SELECT p FROM Delor p WHERE p.id = :id")
	Delor findOneDelorById(int id);
	
	@Query("SELECT e FROM Element e WHERE e.chef.id = :id and e.type = 0 and e.draft = 1")
	Collection<Element> findAllIngredientsByChef(int id);
	
	@Query("SELECT p FROM Delor p WHERE p.keylet = :keylet")
	Delor findDelorByKeylet(String keylet);

}
