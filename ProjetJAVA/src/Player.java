import java.util.ArrayList;
import java.util.List;

public class Player {
	
	private String name;
	private List<Planet> planets = new ArrayList<Planet>();;
	public Player(String name, Planet firstPlanet) {
		this.name = name;
		this.planets.add(firstPlanet);
		
	}

}
