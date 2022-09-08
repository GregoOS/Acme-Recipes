package acme.features.any.peep;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.peep.Peep;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyPeepRepository extends AbstractRepository {
	@Query("select p from Peep p where p.id = :id")
	Peep findById(int id);
	
	@Query("select p from Peep p where p.moment > :deadline")
	Collection<Peep> findRecent(Date deadline);

}