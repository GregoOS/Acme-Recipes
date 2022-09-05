package acme.features.chef.pimpam;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.pimpam.Pimpam;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ChefPimpamRepository extends AbstractRepository {
	
	@Query("SELECT p FROM Pimpam p WHERE p.element.chef.id = :id") //Change for COMPONENT if needed
	Collection<Pimpam> findPimpamByChefId(int id);
	
	@Query("SELECT p FROM Pimpam p WHERE p.id = :id")
	Pimpam findOnePimpamById(int id);

}
