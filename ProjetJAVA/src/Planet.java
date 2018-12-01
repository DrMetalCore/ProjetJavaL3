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

	public Sprite getSprite() {
		return sprite;
	}
}
