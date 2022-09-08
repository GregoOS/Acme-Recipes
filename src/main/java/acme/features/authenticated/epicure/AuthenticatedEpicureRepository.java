package acme.features.authenticated.epicure;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Epicure;

@Repository
public interface AuthenticatedEpicureRepository extends AbstractRepository {

	@Query("select e from Epicure e where e.userAccount.id = :id")
	Epicure findOneEpicureByUserAccountId(int id);

	@Query("select u from UserAccount u where u.id = :id")
	UserAccount findOneUserAccountById(int id);

}
