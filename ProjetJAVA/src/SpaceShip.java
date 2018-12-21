import java.io.Serializable;

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
	public Sprite getSprite() {
		return sprite;
	}
	public int getAttak() {
		return attak;
	}
	public int getSpeed() {
		return speed;
	}
	
	
	
}
