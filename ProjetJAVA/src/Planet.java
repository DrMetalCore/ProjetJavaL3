import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 
 * @author Luka Moraiz and Clément Brandel
 * @version 1
 */

public class Planet implements Serializable{

	private int diameter,defencePow, prodRate, typeProducted;
	private Sprite sprite;
	private Player owner;
	private int nbOfSpaceshipToSend;
	private boolean selectedAndValid;
	private ArrayList<Squadron> squadrons;
	
	/**
	 *  create a Planet object with property :
	 * @param diameter  	diameter of planet
	 * @param defencePow	hit point of planet
	 * @param prodRate		production's rate of spaceship
	 * @param typeProducted      Spaceship's type
	 * @param sprite
	 * @param owner			player
	 * @version 1
	 */
	public Planet(int diameter, int defencePow, int prodRate, int typeProducted, Sprite sprite, Player owner) {
		this.diameter = diameter;
		this.defencePow = defencePow;
		this.prodRate = prodRate;
		this.typeProducted = typeProducted;
		this.sprite = sprite;
		this.owner = owner;
		this.nbOfSpaceshipToSend = 0;
		this.selectedAndValid = false;
		this.squadrons = new ArrayList<Squadron>();
	}
	
	
	public int getDefencePow() {
		return defencePow;
	}
	public Sprite getSprite() {
		return sprite;
	}
	public ArrayList<Squadron> getSquadrons() {
		return squadrons;
	}
	/**
	 * 
	 * @return true if planet is selected and valid , false otherwise
	 * @version 1
	 */
	public boolean isSelectedAndValid() {
		return selectedAndValid;
	}
	/**
	 * 
	 * @param p2   planet
	 * @return true if no superposition between planets , false otherwise
	 * @version 1
	 */
	public boolean planetCollision(Planet p2)
	{
		
		double dx = this.getSprite().getX()- p2.getSprite().getX();
		double dy = this.getSprite().getY() - p2.getSprite().getY();
		double distance = Math.sqrt(dx * dx + dy * dy);
		if (distance < this.getSprite().height() + p2.getSprite().height()) {
			return true;
		}
		else return false;
	}
	/**
	* correct if there is a superposition between planets
	* @version 1
	*/
	public void correctCollision()
	{
		for (int i = 0; i<Game.getPlanetslist().size()-1; i++)
		{
			if(this.planetCollision(Game.getPlanetslist().get(i)))
			{
				this.getSprite().setPosition(Game.getWidth()*Math.random(), Game.getHeight()*Math.random());
				this.correctCollision();
			}
		}
	}
	/**
	* defencePow's planet increase
	* @version 1
	*/
	public void timeAugmentation()
	{
		this.defencePow++;
	}
	/**
	 * 
	 * @param x  abscissa
	 * @param y  ordinate 
	 * @return true if the point (x,y) is on a planet, false otherwise.
	 * @version 1
	 */
	public boolean isSelected(double x, double y)
	{
		if(Math.sqrt(Math.pow(x-(this.getSprite().getX()+this.getSprite().width()/2), 2)+Math.pow(y-(this.getSprite().getY()+this.getSprite().width()/2), 2))<this.getSprite().width()/2) return true;
		else return false;
	}
	
	/**
	* true if the planet selected can be selected to be attacked , false otherwise
	* @version 1
	*/
	
	public void isValid()
	{

		if(Game.getSelected().isEmpty() && Game.getPlayer().havePlanet(this)) {
			this.selectedAndValid = true;
			Game.getSelected().add(this);
		}
		else if(!Game.getSelected().isEmpty() && (this.owner==null || Game.getIa().havePlanet(this)) && !Game.getSelected().get(0).equals(this)) {
			Game.getSelected().get(0).selectedAndValid = false;
			Game.getSelected().add(this);
		}
	}
	/**
	 * 
	 * @param p   planet 
	 * @return true if the planet is equal to p, false otherwise.
	 * @version 1
	 */
	public boolean equals(Planet p)
	{
		if(this == p) return true;
		else if(this.diameter==p.diameter && 
				this.prodRate == p.prodRate &&
				this.typeProducted == p.typeProducted &&
				this.owner == p.owner &&
				this.sprite == p.sprite) return true;
		else return false;
	}
	
	/**
	 * 
	 * @param window   create a window
	 * @param field    text
	 * @param planetDestination  destination's planet
	 * @return true if squadron of spaceships are created, false otherwise
	 * @version 1
	 */
	public boolean generateSpaceShips(Stage window, TextField field, Planet planetDestination)
	{
		try {
			
			this.nbOfSpaceshipToSend = Integer.parseInt(field.getText());
			if(this.nbOfSpaceshipToSend >= 0 && this.nbOfSpaceshipToSend<=100)
			{
			window.close();
			int j = 0;
			Squadron sq = new Squadron(planetDestination);
			int realNumOfSpaceShipToSend = (nbOfSpaceshipToSend*this.defencePow)/100;
			System.out.println(realNumOfSpaceShipToSend);
			for (int i = 0; i < realNumOfSpaceShipToSend; i++) {
				
				SpaceShip sp = generatePointInOrbit(j*18);
				
				sq.addSpaceShip(sp);

				this.defencePow = this.defencePow - sp.getAttak();
				j++;
			}
			this.squadrons.add(sq);
			return true;
			}
			else {
				Exception e = null;
				throw e;
			} 
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * the function generate spaceship 
	 * @param numberSpaceShip     the number of spaceship
	 * @param planetDestination   planet of destination
	 * 
	 */
	public void generateSpaceShips(int numberSpaceShip, Planet planetDestination)
	{
		this.nbOfSpaceshipToSend = numberSpaceShip;
			if(this.nbOfSpaceshipToSend >= 0 && this.nbOfSpaceshipToSend<=100)
			{
			int j = 0;
			Squadron sq = new Squadron(planetDestination);
			int realNumOfSpaceShipToSend = (nbOfSpaceshipToSend*this.defencePow)/100;
			for (int i = 0; i < realNumOfSpaceShipToSend; i++) {
				SpaceShip sp = generatePointInOrbit(j*18);
				
				sq.addSpaceShip(sp);
				this.defencePow = this.defencePow - sp.getAttak();
				j++;
			}
			this.squadrons.add(sq);
			
			}
			
		
	}
	/**
	 * ask the number of spaceship to deploy on other planet 
	 * @param primaryStage    
	 * @param planetDestination   planet of destination
	 * @version 1 
	 */
	public void nbOfSpaceShipBox(Stage primaryStage, Planet planetDestination)
	{
		
		Label secondLabel = new Label("Combien de vaisseaux voulez vous envoyer ? (en %)");
		TextField nbOfSpaceShip = new TextField ();
		Button buttonV = new Button("Valider");

        VBox secondaryLayout = new VBox(10);
        secondaryLayout.getChildren().addAll(secondLabel,nbOfSpaceShip,buttonV);
        
        Scene secondScene = new Scene(secondaryLayout, 350, 125);
        
        
        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Invasion");
        newWindow.setScene(secondScene);
        
        buttonV.setOnAction( e -> this.generateSpaceShips( newWindow, nbOfSpaceShip, planetDestination));
        // Set position of second window, related to primary window.
        newWindow.setX(primaryStage.getX() + 200);
        newWindow.setY(primaryStage.getY() + 100);

        newWindow.show();
	}
	/**
	 * 
	 * @param angleDeg angles in degrees
	 * @return point in orbit of planet
	 * @version 1
	 */
	public SpaceShip generatePointInOrbit(double angleDeg) {
		
		double angleRad = Math.toRadians(angleDeg);
		double radius =  (this.getSprite().width())/2;
		
		double centerX = this.sprite.getX() + radius ;
		double centerY = this.sprite.getY() + radius ;
		double x = centerX + (radius + 20) * Math.cos(angleRad);
		double y = centerY + (radius + 20) * Math.sin(angleRad);
		SpaceShip s = new SpaceShip(this.typeProducted);
		s.getSprite().setPosition(x-s.getSprite().height()/2, y-s.getSprite().height()/2);
		s.getSprite().setSpeed(1, 1);
		return s;
	}
}
