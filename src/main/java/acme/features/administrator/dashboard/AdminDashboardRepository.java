package acme.features.administrator.dashboard;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdminDashboardRepository extends AbstractRepository{

	@Query("select count(ing) from Element ing where ing.type=acme.entities.element.Type.INGREDIENT")
	Long totalIngredients();
	
	@Query("select avg(ing.retailPrice.amount) from Element ing where ing.type=acme.entities.element.Type.INGREDIENT and ing.retailPrice.currency =:currency")
	Double averageIngredients(String currency);
	
	@Query("select stddev(ing.retailPrice.amount) from Element ing where ing.type=acme.entities.element.Type.INGREDIENT and ing.retailPrice.currency =:currency")
	Double deviationIngredients(String currency);
	
	@Query("select min(ing.retailPrice.amount) from Element ing where ing.type=acme.entities.element.Type.INGREDIENT and ing.retailPrice.currency =:currency")
	Double minimumIngredients(String currency);
	
	@Query("select max(ing.retailPrice.amount) from Element ing where ing.type=acme.entities.element.Type.INGREDIENT and ing.retailPrice.currency =:currency")
	Double maximunIngredients(String currency);
	
	@Query("select count(ut) from Element ut where ut.type=acme.entities.element.Type.UTENSIL")
	Long totalUtensils();
	
	@Query("select avg(ut.retailPrice.amount) from Element ut where ut.type=acme.entities.element.Type.UTENSIL and ut.retailPrice.currency =:currency")
	Double averageUtensils(String currency);
	
	@Query("select stddev(ut.retailPrice.amount) from Element ut where ut.type=acme.entities.element.Type.UTENSIL and ut.retailPrice.currency =:currency")
	Double deviationUtensils(String currency);
	
	@Query("select min(ut.retailPrice.amount) from Element ut where ut.type=acme.entities.element.Type.UTENSIL and ut.retailPrice.currency =:currency")
	Double minimumUtensils(String currency);
	
	@Query("select max(ut.retailPrice.amount) from Element ut where ut.type=acme.entities.element.Type.UTENSIL and ut.retailPrice.currency =:currency")
	Double maximumUtensils(String currency);
	
	@Query("select fd.status, count(fd) from FineDish fd group by fd.status")
	List<Tuple> totalFineDishes();
	
	@Query("select fd.status, avg(fd.budget.amount) from FineDish fd where fd.budget.currency=:currency group by fd.status")
	List<Tuple> averageFineDishes(String currency);
	
	@Query("select fd.status, stddev(fd.budget.amount) from FineDish fd where fd.budget.currency=:currency group by fd.status")
	List<Tuple> deviationFineDishes(String currency);
	
	@Query("select fd.status, min(fd.budget.amount) from FineDish fd where fd.budget.currency=:currency group by fd.status")
	List<Tuple> minimumFineDishes(String currency);
	
	@Query("select fd.status, max(fd.budget.amount) from FineDish fd where fd.budget.currency=:currency group by fd.status")
	List<Tuple> maximumFineDishes(String currency);
	
	///////////////////
	
	@Query("select p.element.name, 1.0 * count(p)/(select count(pp) from Delor pp) from Delor p group by p.element")
	List<Tuple> getRatioOfDelorIngredients();
	
	@Query("select avg(p.budget.amount) from Delor p where p.budget.currency =:currency")
	Long getAverageBudget(String currency);
	
	@Query("select stddev(p.budget.amount) from Delor p where p.budget.currency =:currency")
	Long getDeviationBudget(String currency);
	
	@Query("select min(p.budget.amount) from Delor p where p.budget.currency =:currency")
	Long getMinimumBudget(String currency);
	
	@Query("select max(p.budget.amount) from Delor p where p.budget.currency =:currency")
	Long getMaximumBudget(String currency);
	
	
}
