package acme.forms;

import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EpicureDashboard implements Serializable{

	protected static final long	serialVersionUID	= 1L;

	Map<String, Long> totalFineDishes;
	Map<String, Map<String, Double>> averageFineDishBudgetPerState;
	Map<String, Map<String, Double>> deviationFineDishBudgetPerState;
	Map<String, Map<String, Double>> minimumFineDishBudgetPerState;
	Map<String, Map<String, Double>> maximumFineDishBudgetPerState;
}
