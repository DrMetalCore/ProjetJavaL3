import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import sun.font.GraphicComponent;

public class Squadron {
	private List<SpaceShip> shipsList;
	public Squadron(List<SpaceShip> shipsList) {
		this.shipsList = shipsList;
	}
	public Squadron() {
		this.shipsList = new ArrayList<SpaceShip>();
	}
	
	public void addSpaceShip(SpaceShip s)
	{
		this.shipsList.add(s);
	}
	
	public void showAllSpaceShip(GraphicsContext gc)
	{
		for (SpaceShip spaceShip : shipsList) {
			
			spaceShip.getSprite().updatePosition();
			spaceShip.getSprite().render(gc);
		}
	}
	
	
	
}
