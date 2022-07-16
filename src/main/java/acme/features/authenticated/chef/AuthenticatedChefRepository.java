package acme.features.authenticated.chef;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;

@Repository
public interface AuthenticatedChefRepository extends AbstractRepository {

	@Query("select c from Chef c where c.userAccount.id = :id")
	Chef findOneChefByUserAccountId(int id);

	@Query("select u from UserAccount u where u.id = :id")
	UserAccount findOneUserAccountById(int id);

}
