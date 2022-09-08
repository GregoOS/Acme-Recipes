package acme.forms;

import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDashboard implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Long totalNumberIngredients;
	Map<String, Double> averagePriceIngredients;
	Map<String, Double> deviationPriceIngredients;
	Map<String, Double> minimumPriceIngredients;
	Map<String, Double> maximumPriceIngredients;
	
	Long totalNumberKitchenUtensils;
	Map<String, Double> averagePriceKitchenUtensils;
	Map<String, Double> deviationPriceKitchenUtensils;
	Map<String, Double> minimumPriceKitchenUtensils;
	Map<String, Double> maximumPriceKitchenUtensils;
	
	Map<String, Long> totalNumberFineDishes;
	Map<String, Map<String, Double>> averagePriceFineDishes;
	Map<String, Map<String, Double>> deviationPriceFineDishes;
	Map<String, Map<String, Double>> minimumPriceFineDishes;
	Map<String, Map<String, Double>> maximumPriceFineDishes;
	
}
