
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

/*import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;*/

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
/**
 * Class that manage animations
 * @author Luka Moraiz and Clément Brandel
 * @version 1
 */
public class Sprite implements Serializable{
	private String imagePath;
	private double x;
	private double y;
	private double xSpeed;
	private double ySpeed;
	private double width;
	private double height;
	private double maxX;
	private double maxY;

	
	/**
	 * 
	 * @param path Path of the image
	 * @param width Width of the image when use in the game
	 * @param height Height of the image when use in the game
	 * @param maxX Maximun range of the Sprite
	 * @param maxY	Maximun range of the Sprite
	 * @version 1
	 */
	public Sprite(String path, double width, double height, double maxX, double maxY) {
		this.imagePath = path;
		this.width = width;
		this.height = height;
		this.maxX = maxX;
		this.maxY = maxY;
	}
	public double width() {
		return width;
	}

	public double height() {
		return height;
	}
		public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public double getXspeed() {
 		return xSpeed;
 	}
 	public double getYspeed() {
 		return ySpeed;
 	}
	/**
	 * Validated the position of the sprite and correct if it's not valid
	 * @version 1
	 */
	public void validatePosition() {
		if (x + width >= maxX) {
			x = maxX - width;
			xSpeed *= -1;
		} else if (x < 0) {
			x = 0;
			xSpeed *= -1;
		}

		if (y + height >= maxY) {
			y = maxY - height;
			ySpeed *= -1;
		} else if (y < 0) {
			y = 0;
			ySpeed *= -1;
		}
	}

 	/**
 	 * Change the position of the sprite en then validate it
 	 * @param x position on x
 	 * @param y position on y
	 * @version 1
 	 */
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
		validatePosition();
	}
	/**
	 * Change the speed of the sprite
	 * @param xSpeed speed on x
	 * @param ySpeed speed on y
	 * @version 1
	 */
	public void setSpeed(double xSpeed, double ySpeed) {
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}
	/**
	 * Update the position of the sprite and validate it
	 * @version 1
	 */
	public void updatePosition() {
		x += xSpeed;
		y += ySpeed;
		validatePosition();
	}
	/** 
	 * Show the sprite
	 * @param gc Graphic context of the window
	 * @version 1
	 */
	public void render(GraphicsContext gc) {
		gc.drawImage(new Image(imagePath, width, height, false, false), x, y);
	}
	/**
	 * Test if 2 sprite intersects
	 * @param s second sprite 
	 * @return true if thier intersects flase otherwise
	 * @version 1
	 */
	public boolean intersects(Sprite s) {
		return ((x >= s.x && x <= s.x + s.width) || (s.x >= x && s.x <= x + width))
				&& ((y >= s.y && y <= s.y + s.height) || (s.y >= y && s.y <= y + height));
	}

	public String toString() {
		return "Sprite<" + x + ", " + y + ">";
	}
	/**
	 * Calcul the distance between 2 sprites
	 * @param x position in x 
	 * @param y position in y
	 * @return the distance between the sprite and the point (x,y)
	 * @version 1
	 */
	public double distance(double x, double y) {
 		return (Math.sqrt(Math.pow((this.getX() - x),2) + Math.pow((this.getY() - y),2)));
 	}
	/** 
	 * Reduce the vetor
	 * @param tab tab of vector(spaceship, planet)
	 * @return divided vector
	 * @version 1
	 */
 	public double[] vectorSpaceShipToPlanet(double tab[]) {
 
 		while (tab[0] > 5 && tab[1] > 5) {
 			if (tab[0]%2 == 0) {
 				tab[0] = tab[0]/2;
 			}
 			else {
 				tab[0] = (tab[0] + 1 )/2;
 			}
 			if (tab[1]%2 == 0) {
 				tab[1] = tab[1]/2;
 			}
 			else {
 				tab[1] = (tab[1] + 1 )/2;	
 			}
 		}
 		return tab;
 	}
}
