package acme.features.epicure.dashboard;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface EpicureDashboardRepository extends AbstractRepository{
	
	@Query("select fd.status, count(fd) from FineDish fd where fd.epicure.id =:epicureId group by fd.status")
	List<Tuple> totalFineDish(int epicureId);
	
	@Query("select fd.status, avg(fd.budget.amount) from FineDish fd where fd.epicure.id =:epicureId and fd.budget.currency =:currency group by fd.status")
	List<Tuple> averageFineDishBudgetPerStatus(int epicureId, String currency);
	
	@Query("select fd.status, min(fd.budget.amount) from FineDish fd where fd.epicure.id =:epicureId and fd.budget.currency =:currency group by fd.status")
	List<Tuple> minimumFineDishBudgetPerState(int epicureId, String currency);
	
	@Query("select fd.status, max(fd.budget.amount) from FineDish fd where fd.epicure.id =:epicureId and fd.budget.currency =:currency group by fd.status")
	List<Tuple> maximumFineDishBudgetPerState(int epicureId, String currency);
	
	@Query("select fd.status, stddev(fd.budget.amount) from FineDish fd where fd.epicure.id =:epicureId and fd.budget.currency =:currency group by fd.status")
	List<Tuple> deviationFineDishBudgetPerState(int epicureId, String currency);
}
