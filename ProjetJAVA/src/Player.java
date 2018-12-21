import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * Class that describe a player
 * @author Luka Moraiz et Clément Brandel
 * 
 */
public class Player implements Serializable{

	private String name;
	private ArrayList<Planet> planets = new ArrayList<Planet>();
	
	/**
	 * Create a player object with a name
	 * @param name player's name
	 */
	public Player(String name) {
		this.name = name;
	}
	/**
	 * Add a planet to player's planets list
	 * @param pl planet to add
	 */
	public void addPlanet(Planet pl) {
		planets.add(pl);
	}
	/**
	 * Test if the player control a planet
	 * @param p planet to test
	 * @return true if he control it false otherwise
	 */
	public boolean havePlanet(Planet p)
	{
		if(planets.contains(p)) return true;
		else return false;
	}
	/**
	 * Allow the IA to automaticly attack an other planet
	 * 
	 */
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
