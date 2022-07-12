package acme.features.chef.memorandum;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.finedish.FineDish;
import acme.entities.memorandum.Memorandum;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ChefMemorandumRepository extends AbstractRepository {

	@Query("select f from FineDish f where f.id = :id")
	FineDish findOneFineDishById(int id);

	@Query("select m from Memorandum m where m.id = :id")
	Memorandum findOneMemorandumById(int id);

	@Query("select m from Memorandum m where m.fineDish.chef.id = :id")
	Collection<Memorandum> findFineDishesByChefId(int id);

	@Query("select m from Memorandum m where m.fineDish.id = :masterId")
	Collection<Memorandum> findMemorandumsByMasterId(int masterId);

	@Query("select count(m) from Memorandum m where m.fineDish.id = :id")
	int countMemorandumsInFineDishById(int id);

	@Query("select m.fineDish.chef.id from Memorandum m where m.id = :id")
	int findChefIdByMemorandumId(int id);
}
