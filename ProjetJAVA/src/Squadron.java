import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import sun.font.GraphicComponent;

public class Squadron implements Serializable{
	private ArrayList<SpaceShip> shipsList;
	public Squadron(ArrayList<SpaceShip> shipsList) {
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
		for (int i = 0; i<this.shipsList.size();i++) {

			//this.shipsList.get(i).getSprite().updatePosition();
			this.shipsList.get(i).getSprite().render(gc);
		}
	}
	
	
	
}
