import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Planet {

	private int diameter,defencePow, prodRate, typeProducted;
	private Sprite sprite;
	private Player owner;
	private int nbOfSpaceshipToSend;
	private boolean selectedAndValid;
	private int squadronReady;
	private List<Squadron> squadrons;
	
	public Planet(int diameter, int defencePow, int prodRate, int typeProducted, Sprite sprite, Player owner) {
		super();
		this.diameter = diameter;
		this.defencePow = defencePow;
		this.prodRate = prodRate;
		this.typeProducted = typeProducted;
		this.sprite = sprite;
		this.owner = owner;
		this.nbOfSpaceshipToSend = 0;
		this.selectedAndValid = false;
		this.squadronReady = -1;
		this.squadrons = new ArrayList<Squadron>();
	}
	public int getDefencePow() {
		return defencePow;
	}
	public Sprite getSprite() {
		return sprite;
	}
	
	public int getSquadronReady() {
		return squadronReady;
	}
	
	protected void setSquadronReady(int squadronReady) {
		this.squadronReady = squadronReady;
	}
	public List<Squadron> getSquadrons() {
		return squadrons;
	}
	public boolean isSelectedAndValid() {
		return selectedAndValid;
	}
	
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
	public void timeAugmentation()
	{
		this.defencePow++;
	}
	public boolean isSelected(double x, double y)
	{
		if(Math.sqrt(Math.pow(x-(this.getSprite().getX()+this.getSprite().width()/2), 2)+Math.pow(y-(this.getSprite().getY()+this.getSprite().width()/2), 2))<this.getSprite().width()/2) return true;
		else return false;
	}
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
	public boolean generateSpaceShips(Stage window, TextField field, Planet planetDestination)
	{
		try {
			
			this.nbOfSpaceshipToSend = Integer.parseInt(field.getText());
			if(this.nbOfSpaceshipToSend >= 0 && this.nbOfSpaceshipToSend<=100)
			{
			window.close();
			int j = 0;
			Squadron sq = new Squadron(planetDestination);
			for (int i = 0; i < (nbOfSpaceshipToSend*this.defencePow+18)/100; i++) {
				SpaceShip sp = new SpaceShip(this.typeProducted);
				double[] spaceShipPositions = generatePointInOrbit(j*20);
				sp.getSprite().setPosition(spaceShipPositions[0], spaceShipPositions[1]);
				//sp.getSprite().setPosition(200, 200);
		
				sq.addSpaceShip(sp);

				this.defencePow = this.defencePow - sp.getAttak();
				j++;
			}
			this.squadrons.add(sq);
			this.squadronReady =this.squadrons.indexOf(sq);
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
	public double[] generatePointInOrbit(double angleDeg) {
		double[] point = new double[2];
		double angleRad = Math.toRadians(angleDeg);
		double radius =  (this.getSprite().width())/2;
		
		double centerX = this.sprite.getX() + radius ;
		double centerY = this.sprite.getY() + radius ;
		double x = (centerX + radius * Math.cos(angleRad));
		double y = (centerY + radius * Math.sin(angleRad));
	
		point[0] = x;
		point[1] = y;
		return point;
	}
}
