package acme.features.authenticated.bulletin;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;

import acme.entities.bulletin.Bulletin;
import acme.framework.repositories.AbstractRepository;

public interface AuthenticatedBulletinRepository extends AbstractRepository {
	@Query("select b from Bulletin b where b.id = :id")
	Bulletin findOneById(int id);
	
	@Query("select b from Bulletin b where b.moment > :deadline")
	Collection<Bulletin> findRecent(Date deadline);
}
