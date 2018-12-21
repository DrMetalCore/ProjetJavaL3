import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player implements Serializable{

	private String name;
	private ArrayList<Planet> planets = new ArrayList<Planet>();
	
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
	public void automaticAttak()
	{
		Random r = new Random();
		int numMin= 0;
		int numMax = this.planets.size();
		int attakingPlanet = r.nextInt(numMax-numMin)+numMin;
		int numberSpaceShip = r.nextInt(30-10)+10;
		this.planets.get(attakingPlanet).generateSpaceShips(numberSpaceShip);
		
	}
}
