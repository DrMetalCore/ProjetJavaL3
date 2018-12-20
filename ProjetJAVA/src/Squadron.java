import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import sun.font.GraphicComponent;

public class Squadron {
	private List<SpaceShip> shipsList;
	private Planet planetDestination;
	
	public Squadron(List<SpaceShip> shipsList, Planet planetDestination) {
		this.shipsList = shipsList;
		this.planetDestination = planetDestination;
	}
	public Squadron(Planet planetDestination) {
		this.shipsList = new ArrayList<SpaceShip>();
		this.planetDestination = planetDestination;
	}
	
	public void addSpaceShip(SpaceShip s)
	{
		this.shipsList.add(s);
	}
	
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
