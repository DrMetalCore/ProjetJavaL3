
public class SpaceShip {
	
	private int speed,prodTime,attak,type;
	private Sprite sprite;
	public SpaceShip(int type) {
		
		this.type=type;

		if(this.type==1) 
		{
			this.speed = 10;
			this.prodTime = 1;
			this.attak = 1;
			this.sprite = new Sprite(Game.getRessourcePathByName("ressources/SpaceShipType1.png"), 18, 22, 600, 600);
		}
		if(this.type==2)
		{
			this.speed = 7;
			this.prodTime = 5;
			this.attak = 50;
			this.sprite = new Sprite(Game.getRessourcePathByName("ressources/SpaceShipType2.png"), 40, 40, 600, 600);
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
	
	public double[] vector(Planet p) {
		double tab[] = {0,0};
		tab[0]= p.getSprite().getX() - this.getSprite().getX();
		tab[1]= p.getSprite().getY() - this.getSprite().getY();
		return tab;
	}
	
	public void spaceShipToPlanet (double[] tab) {
		this.getSprite().setSpeed(tab[0]/3, tab[1]/3);
	}
	
	public boolean spaceShipOnPlanet(Planet planetDestination) {
		if (this.getSprite().distance(planetDestination.getSprite().getX(), planetDestination.getSprite().getY())<100) {
			return true ;
		}
		return false;
	}
	
	public void dodgePlanet(List<Planet> planetList) {
		for ( Planet planet : planetList) {
			if ((planet.getSprite().distance(this.getSprite().getX(), this.getSprite().getY())) == 100) {
				System.out.println(planet);
				this.getSprite().setSpeed(this.getSprite().getX() - this.getSprite().getXspeed()*Math.sin(Math.toRadians(180)), this.getSprite().getY() - this.getSprite().getYspeed()*Math.cos(Math.toRadians(180))); // cos et sinus..
				
			}
		}
		
		
	}// marche pas encore
	
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
