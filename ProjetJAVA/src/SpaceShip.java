import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author Luka Moraiz and Clément Brandel
 *
 */
public class SpaceShip implements Serializable{
	
	private int speed,prodTime,attak,type;
	private Sprite sprite;
	public SpaceShip(int type) {
		
		this.type=type;

		if(this.type==1) 
		{
			this.speed = 10;
			this.prodTime = 1;
			this.attak = 1;
			this.sprite = new Sprite(Game.getRessourcePathByName("ressources/SpaceShipType1.png"), 18, 22, Game.getHeight(), Game.getHeight());
		}
		if(this.type==2)
		{
			this.speed = 7;
			this.prodTime = 5;
			this.attak = 5;
			this.sprite = new Sprite(Game.getRessourcePathByName("ressources/SpaceShipType2.png"), 40, 40, Game.getHeight(), Game.getHeight());
		}
		
	}
	/**
	 * 
	 * @return sprite of spaceship
	 */
	public Sprite getSprite() {
		return sprite;
	}
	/** 
	 * 
	 * @return attak of spaceship
	 */
	public int getAttak() {
		return attak;
	}
	/**
	 * 
	 * @return speed of spaceship
	 */
	public int getSpeed() {
		return speed;
	}
	/**
	 * 
	 * @param p ==> planet 
	 * @return vector(spaceship, planet p )
	 */
	public double[] vector(Planet p) {
		double tab[] = {0,0};
		tab[0]= p.getSprite().getX() - this.getSprite().getX();
		tab[1]= p.getSprite().getY() - this.getSprite().getY();
		return tab;
	}
	/**
	 * 
	 * @param tab ==> tab with vector
	 */
	public void spaceShipToPlanet (double[] tab) {
		this.getSprite().setSpeed(tab[0]/3, tab[1]/3);
	}
	/**
	 * 
	 * @param planetDestination ==> spaceship go there 
	 * @return true if spaceship is on the planetDestination, false otherwise
	 */
	public boolean spaceShipOnPlanet(Planet planetDestination) {
		if (this.getSprite().distance(planetDestination.getSprite().getX()+planetDestination.getSprite().width(), planetDestination.getSprite().getY() + planetDestination.getSprite().height())<30) {
			return true ;
		}
		return false;
	}
	/**
	 * center of planet  == (cx,cy)
	 * @param cx 
	 * @param cy
	 * @param angle ==> angle in degrees
	 * @param p ==> planet
	 */
	public void pointRotation(double cx,double cy, double angle, Planet p){ // centre de la planete : (cx,cy)      // angle en degré 
		double dirP1 = Math.atan2(cy - this.getSprite().getY(), cx-this.getSprite().getX()); 
	   
	 	double dirP2 = dirP1 + angle; 
	   
	 	double dist = this.getSprite().distance(cx, cy); 
	 	if (this.getSprite().distance(p.getSprite().getX(), p.getSprite().getY()) <  p.getSprite().distance(cx, cy)){
	 		this.spaceShipToPlanet(this.getSprite().vectorSpaceShipToPlanet(this.vector(p)));
	 	}
	 	else {
	 		/*this.getSprite().setPosition((cx + dist*Math.cos(dirP2)), (int)(cy + dist*Math.sin(dirP2)));*/
	 		this.getSprite().setPosition((cx + dist*Math.cos(dirP2)), (int)(cy + dist*Math.sin(dirP2)));
	 	}
	 
} 
	
}
