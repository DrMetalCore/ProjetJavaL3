import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import sun.font.GraphicComponent;

public class Squadron implements Serializable{
	private ArrayList<SpaceShip> shipsList;
	private Planet planetDestination;
	public Squadron(ArrayList<SpaceShip> shipsList) {
		this.shipsList = shipsList;
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
