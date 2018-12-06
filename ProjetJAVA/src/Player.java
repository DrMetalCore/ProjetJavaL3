import java.util.ArrayList;
import java.util.List;

public class Player {
	
	private String name;
	private List<Planet> planets = new ArrayList<Planet>();;
	
	public Player(String name) {
		this.name = name;
	}
	
	public void addPlanet(Planet pl) {
		planets.add(pl);
	}
	public boolean havePlanet(Planet p)
	{
		if(planets.contains(p)) return true;
		else return false;
	}
}
