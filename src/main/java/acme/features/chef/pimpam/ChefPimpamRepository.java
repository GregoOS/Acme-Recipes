package acme.features.chef.pimpam;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.element.Element;
import acme.entities.pimpam.Pimpam;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ChefPimpamRepository extends AbstractRepository {
	
	@Query("SELECT p FROM Pimpam p WHERE p.element.chef.id = :id") //Change for COMPONENT if needed
	Collection<Pimpam> findPimpamByChefId(int id);
	
	@Query("SELECT p FROM Pimpam p WHERE p.id = :id")
	Pimpam findOnePimpamById(int id);
	
	@Query("SELECT e FROM Element e WHERE e.chef.id = :id and e.type = 1 and e.draft = 1")
	Collection<Element> findAllUtensilsByChef(int id);
	
	@Query("SELECT p FROM Pimpam p WHERE p.code = :code")
	Pimpam findPimpamByCode(String code);

}
