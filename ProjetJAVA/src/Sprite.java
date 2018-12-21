
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
 * 
 * @author Luka Moraiz and Clément Brandel
 *
 */
public class Sprite implements Serializable{
	private String imagePath;
	//private String base64Image;
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
	 * @param path	
	 * @param width ==> width of window
	 * @param height == > height of window
	 * @param maxX	
	 * @param maxY
	 */
	public Sprite(String path, double width, double height, double maxX, double maxY) {
		this.imagePath = path;
		//this.base64Image = encoder();
		this.width = width;
		this.height = height;
		this.maxX = maxX;
		this.maxY = maxY;
	}
	/*
	public Sprite(Sprite s) {
		image = s.image;
		width = s.width;
		height = s.height;
		maxX = s.maxX;
		maxY = s.maxY;
	}
	*/
	/**
	 * 
	 * @return width of window
	 */
	public double width() {
		return width;
	}
	/**
	 * 
	 * @return height of window
	 */
	public double height() {
		return height;
	}

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
	 * 
	 * @return x
	 */
	public double getX() {
		return x;
	}
	/**
	 * 
	 * @return y
	 */
	public double getY() {
		return y;
	}
	/**
	 * 
	 * @return vitesse d'abscisse
	 */
	public double getXspeed() {
 		return xSpeed;
 	}
	/**
	 * 
	 * @return vitesse de l'ordonnée
	 */
 	public double getYspeed() {
 		return ySpeed;
 	}
 	/**
 	 * 
 	 * @param x ==> position on abscissa
 	 * @param y ==> position on ordinate
 	 */
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
		//validatePosition();
	}
	/**
	 * 
	 * @param xSpeed ==> vitesse de l'abscisse
	 * @param ySpeed ==> vitesse de l'ordonnée
	 */
	public void setSpeed(double xSpeed, double ySpeed) {
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}
	/*
	public  String encoder() {
		File file = new File(this.imagePath);
		try (FileInputStream imageInFile = new FileInputStream(file)) {
			// Reading a Image file from file system
			byte imageData[] = new byte[(int) file.length()];
			imageInFile.read(imageData);
			this.base64Image = Base64.encode(imageData);
		} catch (FileNotFoundException e) {
			System.out.println("Image not found" + e);
		} catch (IOException ioe) {
			System.out.println("Exception while reading the Image " + ioe);
		}
		return base64Image;
	}
	
	public static void decoder() {
		try (FileOutputStream imageOutFile = new FileOutputStream(pathFile)) {
			// Converting a Base64 String into Image byte array
			byte[] imageByteArray = Base64.getDecoder().decode(base64Image);
			imageOutFile.write(imageByteArray);
		} catch (FileNotFoundException e) {
			System.out.println("Image not found" + e);
		} catch (IOException ioe) {
			System.out.println("Exception while reading the Image " + ioe);
		}
	}
	*/
	public void changeSpeed(KeyCode code) {
		switch (code) {
		case LEFT:
			xSpeed--;
			break;
		case RIGHT:
			xSpeed++;
			break;
		case UP:
			ySpeed--;
			break;
		case DOWN:
			ySpeed++;
			break;
		case SPACE:
			ySpeed = xSpeed = 0;
			break;
		default:
		}
	}
	
	public void updatePosition() {
		x += xSpeed;
		y += ySpeed;
		validatePosition();
	}
	/** 
	 * 
	 * @param gc
	 */
	public void render(GraphicsContext gc) {
		gc.drawImage(new Image(imagePath, width, height, false, false), x, y);
	}
	/**
	 * 
	 * @param s ==> 
	 * @return
	 */
	public boolean intersects(Sprite s) {
		return ((x >= s.x && x <= s.x + s.width) || (s.x >= x && s.x <= x + width))
				&& ((y >= s.y && y <= s.y + s.height) || (s.y >= y && s.y <= y + height));
	}

	public String toString() {
		return "Sprite<" + x + ", " + y + ">";
	}
	/**
	 * 
	 * @param x ==> abscissa 
	 * @param y ==> ordinate
	 * @return the distance between the sprite and the point (x,y)
	 */
	public double distance(double x, double y) {
 		/*this.x = x;
 		this.y = y;*/
 		return (Math.sqrt(Math.pow((this.getX() - x),2) + Math.pow((this.getY() - y),2)));
 	}
	/** 
	 * 
	 * @param tab ==> tab of vector(spaceship, planet)
	 * @return vector divide
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
