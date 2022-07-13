package acme.features.epicure.finedish;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.finedish.FineDish;
import acme.entities.memorandum.Memorandum;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;
import acme.roles.Epicure;

@Repository
public interface EpicureFineDishRepository extends AbstractRepository {

	@Query("select f from FineDish f where f.id = :id")
	FineDish findOneFineDishById(int id);

	@Query("select f from FineDish f where f.epicure.id = :id")
	Collection<FineDish> findFineDishesByEpicureId(int id);

	@Query("select m from Memorandum m where m.fineDish.code = :code")
	Collection<Memorandum> findMemorandumsByFineDishCode(String code);

	@Query("select f from FineDish f where f.code = :code")
	FineDish findOneFineDishByCode(String code);

	@Query("select e from Epicure e where e.id = :id")
	Epicure findOneEpicureById(int id);

	@Query("select c from Chef c where c.id = :id")
	Chef findOneChefById(int id);

	@Query("select count(sc) > 0 from SystemConfiguration sc where sc.acceptedCurrencies LIKE %:currency%")
	boolean isAcceptedCurrency(String currency);
}
