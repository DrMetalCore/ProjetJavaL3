import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import sun.font.GraphicComponent;

public class Squadron {
	private List<SpaceShip> shipsList;
	private Planet planetDestination;
	private List<Planet> planetsList;
	
	public Squadron(List<SpaceShip> shipsList, Planet planetDestination, List<Planet> planetsList) {
		this.shipsList = shipsList;
		this.planetDestination = planetDestination;
		this.planetsList = planetsList;
	}
	public Squadron(Planet planetDestination,List<Planet> planetsList) {
		this.shipsList = new ArrayList<SpaceShip>();
		this.planetDestination = planetDestination;
		this.planetsList = planetsList;
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
			
			/*for ( Planet planet : planetsList ) {
				
				if  ((planet.getSprite().distance(spaceShip.getSprite().getX(), spaceShip.getSprite().getY())) < 100) {
					
					spaceShip.pointRotation(planet.getSprite().getX() , planet.getSprite().getY() , 12);
					
				}
			}*/
			
			if (spaceShip.spaceShipOnPlanet(planetDestination) == true ) {
				shipsList.remove(spaceShip);
			}
			spaceShip.getSprite().updatePosition();
			spaceShip.getSprite().render(gc);
		}
	}
	
	
	
}
