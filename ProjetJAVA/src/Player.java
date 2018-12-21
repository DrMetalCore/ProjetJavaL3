import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player implements Serializable{

	private String name;
	private ArrayList<Planet> planets = new ArrayList<Planet>();
	/**
	 * 
	 * @param name ==> player's name
	 */
	public Player(String name) {
		this.name = name;
	}
	/**
	 * 
	 * @param pl ==> player's planet
	 */
	public void addPlanet(Planet pl) {
		planets.add(pl);
	}
	/**
	 * 
	 * @param p
	 * @return
	 */
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
		Planet attakingPlanet = this.planets.get(r.nextInt(numMax-numMin)+numMin);
		int numberSpaceShip = r.nextInt(30-10)+10;
		
		numMin= 0;
		numMax = Game.getPlanetslist().size();
		Planet planetToAttak = Game.getPlanetslist().get(r.nextInt(numMax-numMin)+numMin);
		while(planets.contains(planetToAttak))  planetToAttak = Game.getPlanetslist().get(r.nextInt(numMax-numMin)+numMin);

		attakingPlanet.generateSpaceShips(numberSpaceShip, planetToAttak);
		
	}
}
