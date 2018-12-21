import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import sun.font.GraphicComponent;
/**
 * 
 * @author Luka Moraiz and Clément Brandel
 * 
 */
public class Squadron implements Serializable{
	private ArrayList<SpaceShip> shipsList;
	private Planet planetDestination;
	/**
	 * 
	 * @param shipsList  list of spaceship
	 * create a Squadron object
	 * @version 1
	 */
	public Squadron(ArrayList<SpaceShip> shipsList) {
		this.shipsList = shipsList;
	}
	/**
	 * 
	 * @param planetDestination  planet of destination
	 * create a Squadron of object with a Planet of destination
	 * @version 1
	 */
	public Squadron(Planet planetDestination) {
		this.shipsList = new ArrayList<SpaceShip>();
		this.planetDestination = planetDestination;
	}
	/**
	 * 
	 * @param s  spaceship for add to shipsList
	 * @version 1
	 */
	public void addSpaceShip(SpaceShip s)
	{
		this.shipsList.add(s);
	}
	/**
	 * create all spaceship of squadron and they go on the planet of destination
	 * @param gc the context graphics of the window 
	 * @version 1
	 */
	public void showAllSpaceShip(GraphicsContext gc)
	{
		for (SpaceShip spaceShip : shipsList) {
			
			System.out.println(planetDestination);
			
			spaceShip.spaceShipToPlanet(spaceShip.getSprite().vectorSpaceShipToPlanet(spaceShip.vector(this.planetDestination)));
			/*
			for ( Planet planet : Game.getPlanetslist()) {
				
				if  ((planet.getSprite().distance(spaceShip.getSprite().getX(), spaceShip.getSprite().getY())) < 100) {
					
					spaceShip.pointRotation(planet.getSprite().getX() , planet.getSprite().getY() , 12, planet);
					
				}
			}
			*/
			if (spaceShip.spaceShipOnPlanet(planetDestination) == true ) {
				shipsList.remove(spaceShip);
			}
			spaceShip.getSprite().updatePosition();
			spaceShip.getSprite().render(gc);
}
	}
	
	
	
}
