package A3;

/**
 * Class that represents a food. Ex. bread, cereal or ham.
 * 
 * @author Oscar Strandmark
 */
public class FoodItem {

	private String name;
	private double weight;
	private double volume;
	
	public FoodItem(String name, double weight, double volume) {
		this.name = name;
		this.weight = weight;
		this.volume = volume;
	}

	public String getName() {
		return name;
	}

	public double getWeight() {
		return weight;
	}

	public double getVolume() {
		return volume;
	}
}
