import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class that describe a SpaceShip
 * @author Luka Moraiz and Clément Brandel
 * @version 1
 */
public class SpaceShip implements Serializable{
	
	private int speed,prodTime,attak,type;
	private Sprite sprite;
	/** create a SpaceShip object with the type
	* @param type  type of the spaceship, the type define some property like speed , attak , production's time and sprite.
	* @version 1
	*/
	public SpaceShip(int type) {
		
		this.type=type;

		if(this.type==1) 
		{
			this.speed = 10;
			this.prodTime = 1;
			this.attak = 1;
			this.sprite = new Sprite(Game.getRessourcePathByName("ressources/SpaceShipType1.png"), 65, 65, Game.getHeight(), Game.getHeight());
		}
		if(this.type==2)
		{
			this.speed = 7;
			this.prodTime = 5;
			this.attak = 5;
			this.sprite = new Sprite(Game.getRessourcePathByName("ressources/SpaceShipType2.png"), 40, 40, Game.getHeight(), Game.getHeight());
		}
		
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public int getAttak() {
		return attak;
	}
	
	public int getSpeed() {
		return speed;
	}
	/**
	 * Create a vector
	 * @param p  Planet of destination    
	 * @return  a vector(spaceship to planet)
	 * @version 1
	 */
	public double[] vector(Planet p) {
		double tab[] = {0,0};
		tab[0]= p.getSprite().getX() - this.getSprite().getX();
		tab[1]= p.getSprite().getY() - this.getSprite().getY();
		return tab;
	}
	/**
	 * Set the speed to spaceship
	 * @param tab  tab include vector in tab[0] and tab[1]
	 * @version 1
	 */
	public void spaceShipToPlanet (double[] tab) {
		this.getSprite().setSpeed(tab[0]/3, tab[1]/3);
	}
	/**
	 * Test if the spaceship has arrived to destination
	 * @param planetDestination destination of the spaceship
	 * @return true if spaceship is on the planetDestination, false otherwise
	 * @version 1
	 */
	public boolean spaceShipOnPlanet(Planet planetDestination) {
		if (this.getSprite().distance(planetDestination.getSprite().getX()+planetDestination.getSprite().width(), planetDestination.getSprite().getY() + planetDestination.getSprite().height())<30) {
			return true ;
		}
		return false;
	}
	
}
