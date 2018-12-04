
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

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
	private final static int WIDTH = 1600;
	private final static int HEIGHT = 900;
	private final static int NBPLANETS = 6;
	private final static List<Planet> PLANETSLIST = new ArrayList<Planet>();
	private final static List<Planet> SELECTED = new ArrayList<Planet>();
	private static Player player;
	private static Player ia;
	
	public static String getRessourcePathByName(String name) {
		return Game.class.getResource('/' + name).toString();
	}
	public static void main(String [] args) {
		Application.launch(Game.class,args);
	}
	
	public static int getWidth() {
		return WIDTH;
	}
	public static int getHeight() {
		return HEIGHT;
	}
	public static int getNbplanets() {
		return NBPLANETS;
	}
	public static List<Planet> getPlanetslist() {
		return PLANETSLIST;
	}
	@Override
	public void start(Stage primaryStage) throws Exception{
		// TODO Auto-generated method stub
		primaryStage.setTitle("IA vs HUMAN");
		primaryStage.setResizable(false);

		
		Random r = new Random();
		int low = 125;
		int high = 225;
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

		
		for (int i = 0; i < NBPLANETS; i++) {
			planetR = r.nextInt(high-low) + low;
			defR = r.nextInt(defHight-defLow)+defLow;
			prodR = r.nextInt(prodHight-prodLow)+prodLow;
			typeR = r.nextInt(type2-type1)-type2;
			if(i==0)
			{
				PLANETSLIST.add(new Planet(planetR, defR, prodR, typeR, new Sprite(getRessourcePathByName("ressources/PlanetPlayer.png"), planetR, planetR,  WIDTH, HEIGHT), player));
				player = new Player("Player", PLANETSLIST.get(0));
				PLANETSLIST.get(i).getSprite().setPosition(WIDTH * Math.random(), HEIGHT * Math.random());
			}
			else if(i==1)
			{
				PLANETSLIST.add(new Planet(planetR, defR, prodR, typeR, new Sprite(getRessourcePathByName("ressources/PlanetIA.png"), planetR, planetR,  WIDTH, HEIGHT), ia));
				player = new Player("Player", PLANETSLIST.get(1));
				PLANETSLIST.get(i).getSprite().setPosition(WIDTH * Math.random(), HEIGHT * Math.random());
				PLANETSLIST.get(i).correctCollision();
			}
			else
			{
				PLANETSLIST.add(new Planet(planetR, defR, prodR, typeR, new Sprite(getRessourcePathByName("ressources/PlanetNeutral.png"), planetR, planetR, WIDTH, HEIGHT), null));
				PLANETSLIST.get(i).getSprite().setPosition(WIDTH * Math.random(), HEIGHT * Math.random());
				PLANETSLIST.get(i).correctCollision();
			}
		}
			TimerTask increaseDefPow = new TimerTask() {
				
				@Override
				public void run() {
					for (Planet planet : PLANETSLIST) {
						planet.setDefencePow(planet.getDefencePow()+1);
					}
					
				}
			};
			
			
			Timer timer = new Timer();
			timer.schedule(increaseDefPow,0, 1000);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		//EVENTS
		
		//Mouse event
		EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				for (Planet planet : PLANETSLIST) {
					if(SELECTED.size()<2 && planet.isSelected(e.getX(),e.getY()) && (SELECTED.isEmpty()||!SELECTED.get(0).equals(planet))) 
						{
							
							SELECTED.add(planet);
							System.out.println(SELECTED.get(0).getDefencePow());
							if(SELECTED.size()==2) System.out.println("Connected");
							System.err.println(SELECTED.size());
							
						}
					else if (SELECTED.size()>=2) SELECTED.clear();
				}
			}
		};

		scene.setOnMouseDragged(mouseHandler);
		scene.setOnMousePressed(mouseHandler);
		
		
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
					Planet planet = it.next();
					planet.getSprite().updatePosition();
					planet.getSprite().render(gc);
					double radius =  (planet.getSprite().width())/2;

					gc.fillText(planet.getDefencePow()+"", planet.getSprite().getX() + radius ,planet.getSprite().getY() + radius+10);
					gc.strokeText(planet.getDefencePow()+"", planet.getSprite().getX() + radius ,planet.getSprite().getY() + radius+10);
					gc.setTextAlign(TextAlignment.CENTER);
					
					
					
				}
			}
		}
		
		.start();
	
		}

		
	}

