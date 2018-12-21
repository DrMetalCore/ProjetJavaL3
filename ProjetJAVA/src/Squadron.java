import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import sun.font.GraphicComponent;
/**
 * 
 * @author Luka Moraiz and Clément Brandel
 * @version 1
 */
public class Squadron implements Serializable{
	private ArrayList<SpaceShip> shipsList;
	private Planet planetDestination;
	/**
	 * Create a Squadron of object with a Planet of destination
	 * @param planetDestination  planet of destination
	 * @version 1
	 */
	public Squadron(Planet planetDestination) {
		this.shipsList = new ArrayList<SpaceShip>();
		this.planetDestination = planetDestination;
	}
	/**
	 * Add a spaceship to the squadron
	 * @param s  spaceship to add to the shipsList
	 * @version 1
	 */
	public void addSpaceShip(SpaceShip s)
	{
		this.shipsList.add(s);
	}
	/**
	 * Show all the spaceships of the squadron and they go on the planet of destination
	 * @param gc the graphics context of the window 
	 * @version 1
	 */
	public void showAllSpaceShip(GraphicsContext gc)
	{
		for (SpaceShip spaceShip : shipsList) {
			
			System.out.println(planetDestination);
			
			spaceShip.spaceShipToPlanet(spaceShip.getSprite().vectorSpaceShipToPlanet(spaceShip.vector(this.planetDestination)));
			if (spaceShip.spaceShipOnPlanet(planetDestination) == true ) {
				shipsList.remove(spaceShip);
			}
			spaceShip.getSprite().updatePosition();
			spaceShip.getSprite().render(gc);
}
	}
	
	
	
}
