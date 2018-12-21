
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/*import com.sun.glass.events.WindowEvent;
import com.sun.org.apache.bcel.internal.generic.FNEG;*/

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * Main class that run the program
 * @author Luka Moraiz et Clément Brandel
 * @version 1
 *
 */

public class Game extends Application {
	private final static int WIDTH = 1600;
	private final static int HEIGHT = 900;
	private final static int NBPLANETS = 6;
	private static ArrayList<Planet> PLANETSLIST = new ArrayList<Planet>();
	private final static ArrayList<Planet> SELECTED = new ArrayList<Planet>();
	private static Player player = new Player("Player");
	private static Player ia = new Player("IA");
	
	/**
	 * 
	 * @param name
	 * @return the path of the ressource
	 * @version 1
	 */
	public static String getRessourcePathByName(String name) {
		return Game.class.getResource('/' + name).toString();
	}
	
	/**
	 * Lauch the program
	 * @param args arguments of the program
	 * @version 1
	 */
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
	public static ArrayList<Planet> getPlanetslist() {
		return PLANETSLIST;
	}
	
	public static ArrayList<Planet> getSelected() {
		return SELECTED;
	}
	
	public static Player getPlayer() {
		return player;
	}
	public static Player getIa() {
		return ia;
	}
	/**
	 * Create a planet for a player
	 * @param p   player that will onw the planet
	 * @param i   position of the planet i the arrayList PLANETSLIST
	 * @version 1
	 */
	public void generatePlanet(Player p, int i)
	{
		Random r = new Random();
		int low = 125;
		int high = 225;
		int defLow =25;
		int defHight =75;
		int prodLow =1;
		int prodHight =6;
		//int type1= 1;
		//int type2 = 2;
		int planetR;
		int defR;
		int prodR;
		int typeR;
		
		planetR = r.nextInt(high-low) + low;
		defR = r.nextInt(defHight-defLow)+defLow;
		prodR = r.nextInt(prodHight-prodLow)+prodLow;
		//typeR = r.nextInt(type2-type1)+type1;
		String imagePath = "";
		if(i==0) imagePath = "ressources/PlanetPlayer.png";
		else if(i==1) imagePath = "ressources/PlanetIA.png";
		else imagePath = "ressources/PlanetNeutral.png";
		PLANETSLIST.add(new Planet(planetR, defR, prodR, 1, new Sprite(getRessourcePathByName(imagePath), planetR, planetR,  WIDTH, HEIGHT), p));
		PLANETSLIST.get(i).getSprite().setPosition(WIDTH* Math.random(), HEIGHT * Math.random());
		if(i<2) p.addPlanet(PLANETSLIST.get(i));
		if(i!=0) PLANETSLIST.get(i).correctCollision();
		
	}
	/**
	 * Create a dialogue box that demand to the user if he want to load a save
	 * @param primaryStage main stage of the program
	 * @version 1
	 */
	public void LoadASaveBox(Stage primaryStage)
	{
		
		Label Label = new Label("Voulez-vous charger une ancienne sauvegarde ?");
		Button yesButton = new Button("Oui");
		Button noButton = new Button("Non");

        VBox secondaryLayout = new VBox(10);
        secondaryLayout.getChildren().addAll(Label,yesButton,noButton);
        
        Scene secondScene = new Scene(secondaryLayout, 350, 125);
        
        
        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Veux tu ");
        newWindow.setScene(secondScene);
        
        yesButton.setOnAction( e -> this.loadASave( newWindow));
        noButton.setOnAction( e -> this.noLoadASave( newWindow));

        // Set position of second window, related to primary window.
        newWindow.setX(primaryStage.getX() + 200);
        newWindow.setY(primaryStage.getY() + 100);

        newWindow.show();
	}
	/**
	 * function use when the user don't load a save
	 * @param newWindow dialogue box that demand to the user if he want to load a save
	 * @version 1
	 */
	private void noLoadASave(Stage newWindow) {
		newWindow.close();
	}
	/**
	 * function use when the user load a save
	 * @param newWindow dialogue box that demand to the user if he want to load a save
	 * @version 1
	 */
	private void loadASave(Stage newWindow) {
		newWindow.close();
		
		
			ObjectInputStream objectSave;
			try {
				objectSave = new ObjectInputStream(new FileInputStream("save.txt"));
				player = (Player) objectSave.readObject();
				ia = (Player) objectSave.readObject();
				PLANETSLIST = (ArrayList<Planet>) objectSave.readObject();
					
			} 
			catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
	/**
	 * Start function that manage all the program
	 * @param primaryStage main stage 
	 * @throws Exception
	 * @version 1
	 */
	@Override
	public void start(Stage primaryStage) throws Exception{
		// TODO Auto-generated method stub
		primaryStage.setTitle("IA vs HUMAN");
		primaryStage.setResizable(false);

		
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
			
			if(i==0)
			{
				generatePlanet(player, i);
			}
			else if(i==1)
			{
				generatePlanet(ia, i);

			}
			else
			{
				generatePlanet(null, i);
			}
		}
			TimerTask increaseDefPow = new TimerTask() {
				/**
				 * Function that augment planets point every seconds
				 * 
				 */
				@Override
				public void run() {
					for (Planet planet : PLANETSLIST) {
						planet.timeAugmentation();
					}
					
				}
			};
			
			TimerTask autoAttakTimer = new TimerTask() {
				/**
				 * Function that allow the IA to attack other planet 5 seconds after the start of the game and then every 7 seconds
				 * 
				 */
				@Override
				public void run() {
					ia.automaticAttak();
					
				}
			};
			
		primaryStage.setScene(scene);
		primaryStage.show();
		File fsave = new File("save.txt");
		if(fsave.createNewFile()==false)
		{
			LoadASaveBox(primaryStage);
		}
		 primaryStage.setOnCloseRequest( event ->
		    {
		    	try {
		    		File fileSave = new File("save.txt");
					ObjectOutputStream objectSave = new ObjectOutputStream(new FileOutputStream(fileSave));
					objectSave.writeObject(player);
					objectSave.writeObject(ia);
					objectSave.writeObject(PLANETSLIST);
					
					objectSave.close();
					
					
					
					
				} 
		    	catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    	
		        primaryStage.close();
		    
		    });
		
		//EVENTS
		
		//Timer Events
		Timer timer = new Timer();
		timer.schedule(increaseDefPow,0, 1000);
		
		Timer IATimer = new Timer();
		IATimer.schedule(autoAttakTimer,5000, 7000);

		//Mouse event
		EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {
			/**
				 * Function manage the selection of 2 planets in order to launch an attack
				 * @version 1
				 */
			public void handle(MouseEvent e) {
				for (Planet planet : PLANETSLIST) {
					if (planet.isSelected(e.getX(), e.getSceneY())){
						planet.isValid();
					}
					else if(SELECTED.size()==2) {
						SELECTED.get(0).nbOfSpaceShipBox(primaryStage, SELECTED.get(1));
						SELECTED.clear();
					}
				}
			}
		};
		
		
		scene.setOnMouseDragged(mouseHandler);
		scene.setOnMousePressed(mouseHandler);
		
		new AnimationTimer() {
			/**
			* Function handle the display all the animations
			* @version 1
			*/
			public void handle(long arg0) {
				gc.drawImage(space, 0, 0);
				Iterator<Planet> it = PLANETSLIST.iterator();
				while (it.hasNext()) {
					Planet planet = it.next();
					
					planet.getSprite().updatePosition();
					planet.getSprite().render(gc);
					double radius =  (planet.getSprite().width())/2;

					gc.fillText(planet.getDefencePow()+"", planet.getSprite().getX() + radius ,planet.getSprite().getY() + radius+10);
					gc.setTextAlign(TextAlignment.CENTER);
					if(planet.isSelectedAndValid()) {
						gc.strokeText(planet.getDefencePow()+"", planet.getSprite().getX() + radius ,planet.getSprite().getY() + radius+10);

					}
					
					for (Squadron s : planet.getSquadrons()) {
						s.showAllSpaceShip(gc);
					}
					
					
					
				}
			}
		}
		
		.start();
	
		}

		
	}
