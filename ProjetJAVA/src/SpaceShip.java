
public class SpaceShip {
	
	private int speed,prodTime,attak,type;
	private Sprite sprite;
	public SpaceShip(int speed, int prodTime, int attak, int type) {
		
		this.type=type;

		if(this.type==1) 
		{
			this.speed = 10;
			this.prodTime = 1;
			this.attak = 1;
			this.sprite = new Sprite("images/broche-triangle.jpg", 18, 22, 600, 600);
		}
		if(this.type==2)
		{
			this.speed = 7;
			this.prodTime = 5;
			this.attak = 50;
			this.sprite = new Sprite("images/autocollant-tableau-noir-figures-pentagone.jpg", 40, 40, 600, 600);
		}
	}
	
}
