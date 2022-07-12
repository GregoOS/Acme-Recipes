package acme.features.chef.finedish;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.finedish.FineDish;
import acme.entities.memorandum.Memorandum;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ChefFineDishRepository extends AbstractRepository {

	@Query("select f from FineDish f where f.id = :id")
	FineDish findOneFineDishById(int id);

	@Query("select f from FineDish f where f.chef.id = :id and f.draft = false")
	Collection<FineDish> findFineDishesByChefId(int id);

	@Query("select f from FineDish f where f.chef.id = :id and f.draft = false and f.status = 0")
	Collection<FineDish> findProposedFineDishesByChefId(int id);

	@Query("select m from Memorandum m where m.fineDish.code = :code")
	Collection<Memorandum> findMemorandumsByFineDishCode(String code);

}