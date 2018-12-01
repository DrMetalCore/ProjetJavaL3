import java.util.ArrayList;
import java.util.List;

public class Planet {

	private int diameter,defencePow, prodRate, typeProducted;
	private Sprite sprite;
	private Player owner;
	private List<SpaceShip> ships = new ArrayList<SpaceShip>();
	
	public Planet(int diameter, int defencePow, int prodRate, int typeProducted, Sprite sprite, Player owner) {
		super();
		this.diameter = diameter;
		this.defencePow = defencePow;
		this.prodRate = prodRate;
		this.typeProducted = typeProducted;
		this.sprite = sprite;
		this.owner = owner;
	}
	public Boolean planetCollision(Planet p2)
	{
		
		double dx = this.getSprite().getX()- p2.getSprite().getX();
		double dy = this.getSprite().getY() - p2.getSprite().getY();
		double distance = Math.sqrt(dx * dx + dy * dy);
		if (distance < this.getSprite().height() + p2.getSprite().height()) {
			return true;
		}
		return false;
	}
	public void correctionCollision()
	{
		for (int i = 0; i<Game.getPlanetslist().size()-1; i++)
		{
			if(this.planetCollision(Game.getPlanetslist().get(i)))
				{
					this.getSprite().setPosition(Game.getWidth()*Math.random(), Game.getHeight()*Math.random());
					this.correctionCollision();
				}
		}
	}
	public int getDefencePow() {
		return defencePow;
	}

	public void setDefencePow(int defencePow) {
		this.defencePow = defencePow;
	}
	public Sprite getSprite() {
		return sprite;
	}
}
