
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;



public class Game extends Application {
	private int score;
	private final static int WIDTH = 1600;
	private final static int HEIGHT = 900;
	private final static int NBPLANETS = 6;
	private final static List<Planet> PLANETSLIST = new ArrayList<Planet>();
	private static Player player;
	private static Player ia;
	
	public static String getRessourcePathByName(String name) {
		return Game.class.getResource('/' + name).toString();
	}
	public static void main(String [] args) {
		Application.launch(Game.class,args);
	}
	
	public static Boolean planetCollision(Planet p1, Planet p2)
	{
		if (p1.getSprite().getX() < p2.getSprite().getX() + p2.getSprite().width() &&
				   p1.getSprite().getX() + p1.getSprite().width()> p2.getSprite().getX() &&
				   p1.getSprite().getY() < p2.getSprite().getY() + p2.getSprite().height() &&
				   p1.getSprite().height() + p1.getSprite().getY() > p2.getSprite().getY()) {
				    return true;
				}
		return false;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		// TODO Auto-generated method stub
		primaryStage.setTitle("IA vs HUMAN");
		primaryStage.setResizable(false);

		
		Random r = new Random();
		int low = 160;
		int high = 261;
		int defLow =200;
		int defHight =401;
		int prodLow =1;
		int prodHight =6;
		int type1= 1;
		int type2 = 3;
		int planetR;
		int defR;
		int prodR;
		int typeR;
		
		
		Group root = new Group();
		Scene scene = new Scene(root,WIDTH,HEIGHT,Color.LIGHTBLUE);
		
		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		root.getChildren().add(canvas);
		
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFont(Font.font("Helvetica", FontWeight.BOLD, 24));
		gc.setFill(Color.BISQUE);
		gc.setStroke(Color.RED);
		gc.setLineWidth(1);
		
		Image space = new Image(getRessourcePathByName("ressources/space.png"), WIDTH, HEIGHT, false, false);

		for (int i = 0; i < NBPLANETS-2; i++) {
			planetR = r.nextInt(high-low) + low;
			defR = r.nextInt(defHight-defLow)+defLow;
			prodR = r.nextInt(prodHight-prodLow)+prodLow;
			typeR = r.nextInt(type2-type1)-type2;
			PLANETSLIST.add(new Planet(planetR, defR, prodR, typeR, new Sprite(getRessourcePathByName("ressources/PlanetNeutral.png"), planetR, planetR, WIDTH, HEIGHT), null));
			if (i==0) PLANETSLIST.get(i).getSprite().setPosition(WIDTH * Math.random(), HEIGHT * Math.random());
			else {
				PLANETSLIST.get(i).getSprite().setPosition(WIDTH * Math.random(), HEIGHT * Math.random());
				while(planetCollision(PLANETSLIST.get(i-1), PLANETSLIST.get(i)))
				{
					PLANETSLIST.get(i).getSprite().setPosition(WIDTH * Math.random(), HEIGHT * Math.random());
				}
			}
	
		}
		
		planetR = r.nextInt(high-low) + low;
		defR = r.nextInt(defHight-defLow)+defLow;
		prodR = r.nextInt(prodHight-prodLow)+prodLow;
		typeR = r.nextInt(type2-type1)-type2;
		PLANETSLIST.add(new Planet(planetR, defR, prodR, typeR, new Sprite(getRessourcePathByName("ressources/PlanetPlayer.png"), planetR, planetR,  WIDTH, HEIGHT), player));
		player = new Player("Player", PLANETSLIST.get(NBPLANETS-2));
		PLANETSLIST.get(NBPLANETS-2).getSprite().setPosition(WIDTH * Math.random(), HEIGHT * Math.random());
			
			
		planetR = r.nextInt(high-low) + low;
		defR = r.nextInt(defHight-defLow)+defLow;
		prodR = r.nextInt(prodHight-prodLow)+prodLow;
		typeR = r.nextInt(type2-type1)-type2;
		PLANETSLIST.add(new Planet(planetR, defR, prodR, typeR, new Sprite(getRessourcePathByName("ressources/PlanetIA.png"), planetR, planetR,  WIDTH, HEIGHT), ia));
		player = new Player("Player", PLANETSLIST.get(NBPLANETS-1));
		PLANETSLIST.get(NBPLANETS-1).getSprite().setPosition(WIDTH * Math.random(), HEIGHT * Math.random());

		
		primaryStage.setScene(scene);
		primaryStage.show();
		
		new AnimationTimer() {
			public void handle(long arg0) {
				gc.drawImage(space, 0, 0);

				/*
				spaceship.updatePosition();

				Iterator<Sprite> it = pinapples.iterator();
				while (it.hasNext()) {
					Sprite pinapple = it.next();
					pinapple.updatePosition();
					if (pinapple.intersects(spaceship)) {
						it.remove();
						if (mediaPlayerBoomFinalCopy != null) {
							mediaPlayerBoomFinalCopy.stop();
							mediaPlayerBoomFinalCopy.play();
						}
						score += 100;
					} else {
						pinapple.render(gc);
						if (Math.random() > 0.995) {
							changeSpeed(pinapple);
						}
					}
				}

				spaceship.render(gc);
  				*/
				Iterator<Planet> it = PLANETSLIST.iterator();
				while (it.hasNext()) {
					Sprite planet = it.next().getSprite();
					planet.updatePosition();
					planet.render(gc);
				}
				String txt = "Score: " + score;
				gc.fillText(txt, WIDTH - 36, 36);
				gc.strokeText(txt, WIDTH - 36, 36);
				gc.setTextAlign(TextAlignment.RIGHT);
			}
		}.start();
		
		}

		
	}
