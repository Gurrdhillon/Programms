package com.mycompany.a3;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;


public class GameWorld extends Observable implements ICollection {
	
	/*
	 * Collections to aggregate objects of 
	 * abstract type GameObject
	 */
	
	private int life = 3;
	private int clock = 0;
	private Ant theAnt= null;
	
	//New value added in A2
	private boolean sound = false;

	private int gameWorldLength = 500;
	private int gameWorldWidth = 500;
	private Random rand;
	private Game game = null;
    private ArrayList<GameObject> gameObjectsCollection;
	private boolean position;
	
	
	/* 
	 * Constructor
	 * 
	 * This constructor initialize variables and create a arraylist 
	 * to hold gameObjectsCollection
	 * 
	 * @param: None
	 * @return: NOne
	 */
	GameWorld(Game game) {
		
		this.game = game;
		rand = new Random();
		gameObjectsCollection = new ArrayList<GameObject>();
		
	}
	
	public boolean isGameRunning() {
		return game.getRunning();
	}
	void setPosition(boolean position) {
		this.position = position;
	}
	
	boolean getPosition() {
		return this.position;
	}
	

	@Override
	public void add(GameObject object) {
		// TODO Auto-generated method stub
		gameObjectsCollection.add(object);	
	}

	@Override
	public IIterator getIterator() {
		// TODO Auto-generated method stub
		return new GameObjectIterator();
	}
	
	public class GameObjectIterator implements IIterator{
		
		private int currElementIndex;
		
		public GameObjectIterator() {
			currElementIndex = -1;
		}

		@Override
		public boolean hasNext() {
			if (gameObjectsCollection.size ( ) <= 0) {
				return false;
			}
			if (currElementIndex == gameObjectsCollection.size() - 1 ) {
				return false;
			}
			
			return true;
		}

		@Override
		public GameObject getNext() {
			currElementIndex ++ ;
			return(gameObjectsCollection.get(currElementIndex));
		}
		
	}
	
	/*Setter/mutator for height and width
	 * 
	 */
	  void setHeight(int length) {
		this.gameWorldLength = length;
	}
	
	 void setWidth(int width) {
		this.gameWorldWidth = width;
	}
//	
	private void setSound(boolean sound) {
		this.sound = sound;
		setChanged();
		notifyObservers();
	}
	
	public void toggleSound() {
		if (this.getSound()) {
			this.setSound(false);
		} else {
			this.setSound(true);
		}
		game.getToolbar().closeSideMenu();
	}
	
	
	/*
	 * This function return 
	 * private variable life
	 * 
	 * @param: None
	 * return: int (life)
	 */

	public int getLife() {
		return life;
	}

	/*
	 * This function return 
	 * private variable clock
	 * return: int (life)
	 */

	public int getClock() {
		return clock;
	}
	
	public boolean getSound() {
		return sound;
		
	}
																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																	                 
	/*
	 * Function INIT
	 * This function create and put in the game
	 * 4 flags, 1 ant, 2 spider, and 2 Foodstations
	 * 
	 */
	
	public void init() {
		
		gameObjectsCollection.clear();
		
		/*ADDING 4 FLAGS
		 * size:10
		 * location: (10, 10), (400,400), (200,600), (990,990)
		 * color: blue
		 * flagNo: 1,2,3,4
		 */ 
		
		Flag f1 = new Flag(100,new Point(10,10), ColorUtil.rgb(1,0,255), 1);
		Flag f2 = new Flag(100,new Point(400,400), ColorUtil.rgb(1,0,255), 2);
		Flag f3 = new Flag(100,new Point(200,600), ColorUtil.rgb(1,0,255), 3);
		Flag f4 = new Flag(100,new Point(990,990), ColorUtil.rgb(1,0,255), 4);
		
		
		this.add(f1);
		this.add(f2);
		this.add(f3);
		this.add(f4);
		
		
		/* Creating Ant using singeton design pattern and assigning it to theAnt
		 * ADDING 1 ANT
		 * size: 10
		 * color: black
		 * speed: 10
		 * heading:0
		 * location: flag1 location
		 */
		
		Ant ant = Ant.getAnt(100, ColorUtil.rgb(255,0,0), 300, 0, gameObjectsCollection.get(0).getLocation(), this);
		theAnt = ant;
		
		this.add(theAnt);
		
		/*
		 * ADDING 2 SPIDERS
		 * size: 8
		 * color: black
		 * speed: 4
		 * heading:random
		 * location: random
		 */
		
		Point s1RandomLoc = new Point(gameWorldLength*rand.nextFloat(),gameWorldWidth*rand.nextFloat());
		double s1RandomHeading =  360*rand.nextDouble();
		Point s2RandomLoc = new Point(gameWorldLength*rand.nextFloat(),gameWorldWidth*rand.nextFloat());
		double s2RandomHeading =  360*rand.nextDouble();
		Spider s1 = new Spider(8, ColorUtil.rgb(0,0,0),400,s1RandomHeading, s1RandomLoc);
		Spider s2 = new Spider(8, ColorUtil.rgb(0,0,0),450,s2RandomHeading, s2RandomLoc);
		
		
		this.add(s1);
		this.add(s2);
		
		/*
		 * ADDING 2 FOODSTATIONS
		 * size: random
		 * location: random
		 * color: green
		 * capacity: equal to size
		 */
		
		int fs1RandomSize = 10 + rand.nextInt(100);
		Point fs1LocationRandom = new Point(gameWorldLength*rand.nextFloat(),gameWorldWidth*rand.nextFloat());  //bw 0 and 1000
		int fs2RandomSize = 10 + rand.nextInt(100);
		Point fs2LocationRandom = new Point(gameWorldLength*rand.nextFloat(),gameWorldWidth*rand.nextFloat());  //bw 0 and 1000
		
		FoodStation fs1 = new FoodStation(fs1RandomSize,fs1LocationRandom,ColorUtil.rgb(136, 133, 46),fs1RandomSize);
		FoodStation fs2 = new FoodStation(fs2RandomSize,fs2LocationRandom,ColorUtil.rgb(136, 133, 46),fs2RandomSize);
		
		this.add(fs1);
		this.add(fs2);
	}
	
	/*
	 * DISPLAY COMPLETE
	 * This function display game vitals
	 */
	
	public void display() {
		
		
		String output = "LivesLeft: " + this.getLife() + 
						", Clock: " + this.getClock() +
						", LastFlagReached: " + theAnt.getLastFlagReached() +
						", CurrentFoodLevel: " + theAnt.getFoodLevel() +
						", Health: " + theAnt.getHealthLevel();
		
		System.out.println(output);
		
	}
	
	/* function MAP 
	 * This function print all the object of 
	 * gameObjectsCollection by invoking their toString()
	 * method, which is been overridden for 
	 * every object
	 * It does not print FoodStation if its already used
	 * 
	 */
	
	public void map() {
		
		GameObjectIterator  gameObjects = (GameObjectIterator) this.getIterator();
		
		while(gameObjects.hasNext()) {
			System.out.println(gameObjects.getNext());
		}	
	}	
	
	/*EXIT
	 * This function exit the app
	 */
	
	public void exit() {
		//Implementation confirmation
		System.exit(0);
	}
	
	
	/*function ACCELERATE
	 * accelerate the Ant if Ant's current 
	 * health allow it.
	 */
	
	public void accelerate() {
		
		//New Speed is 10% increase
		
		double newSpeed;
		
		newSpeed = theAnt.getSpeed() + 0.1*theAnt.getSpeed();
		
		if(theAnt.getHealthLevel() >= 10) {
			
			if (newSpeed < theAnt.getMaximumSpeed() ) {
				theAnt.setSpeed(newSpeed);		
			
			} else {
				System.out.println("The ant can't accelerate");	
			}
			
		} else if (theAnt.getHealthLevel() >= 5) {
			
			if (newSpeed < (theAnt.getMaximumSpeed())*0.5) {
				theAnt.setSpeed(newSpeed);		
			
			} else {
				System.out.println("The ant can't accelerate");	
			}
			
		} else if (theAnt.getHealthLevel() == 0) {
			
			System.out.println("The ant can't accelerate");
		}
		
		//Methods to notify observer and call update method of observers
		setChanged();
		notifyObservers();			
	}
	

	
	/*Function BRAKE
	 * This function reduce the current speed by 10%, 
	 * if speed is greater than 1, else 
	 * nothing happen
	 */
	public void brake() {
		
		if (theAnt.getSpeed() > 1) {
			theAnt.setSpeed(0.9*theAnt.getSpeed());	
		} else {
			System.out.println("Brakes can't be applied");
		}
		
		//Methods to notify observer and call update method of observers
		setChanged();
		notifyObservers();
	}
	

	/*
	 *function TURNLEFT
	 * This function turn ant to left by 5 degree,
	 * and then call move to update Ant's location
	 */
	
	public void turnLeft() {
		theAnt.steer(- 5);
		
		//Pass a constant 20mlsec as elapsed time between two ticks
		theAnt.move(20);
		
		setChanged();
		notifyObservers();
	}
	
	
	/*
	 * TURNRIGHT 
	 * This function turn ant to right by 5 degree
	 * and then call move to update Ant's location
	 */
	
	public void turnRight() {
		theAnt.steer(5);
		//Pass a constant 20mlsec as elapsed time between two ticks
		theAnt.move(20);
		
		setChanged();
		notifyObservers();
	}
	
	

	/*NEXTFLAGREACHED
	 * this function takes an Integer parameter and
	 * if nextFlagReached is nextFlag to be reached 
	 * then it get updated else it is ignored, and nothing happen
	 * 
	 * @param: flagReached , the current flag ant has reached
	 * @return: void
	 */
	public void nextFlagReached(int flagReached) {	
		
		if (flagReached - theAnt.getLastFlagReached() == 1) {
			
			theAnt.setLastFlagReached(flagReached);
			
			checkWin();
		}
		
		//Methods to notify observer and call update method of observers
		setChanged();
		notifyObservers();
		
	}
	
	
	/*FOODSTATIONLOCATED
	 * This function pretend that and has gotten food, ant food level increase
	 * and then food capacity of that station would go to zero, then
	 * it create another foodStand with random newLocateion and  capacity
	 * 
	 */
	
	public void foodStationLocated(FoodStation fs) {			

		if (fs.getCapacity() == 0) {
			
			//Do nothing
			
		} else {
			
			theAnt.setFoodLevel(theAnt.getFoodLevel() + fs.getCapacity());
			
			fs.setCapacity(0);
			
			//Set Color of food station to light green
			fs.setColor(ColorUtil.rgb(209, 206, 119));
			
			//Creating new foodStation with random capacity and random location gameWorldWidth
			int fsNewRandomSize = 10 + rand.nextInt(100);
			System.out.println(gameWorldWidth + " ," + gameWorldLength);
			Point fsNewLocationRandom = new Point((gameWorldWidth-100)*rand.nextFloat(),(gameWorldLength-100)*rand.nextFloat());  //bw 0 and 1000
			
			FoodStation fsnew = new FoodStation(fsNewRandomSize,fsNewLocationRandom,ColorUtil.rgb(136, 133, 46),fsNewRandomSize);
			
			gameObjectsCollection.add(fsnew);
		}
			
			//Methods to notify observer and call update method of observers
			setChanged();
			notifyObservers();
	}
	
	
	/*
	 * SPIDERCOLLISION()
	 * This function pretend that ant has collided with the Spider,
	 * it reduce the health of Ant by 1,
	 * and the it call checkSpeed() to adjust speed,
	 * according to health level, and then fade the color of Ant
	 */
	public void spiderCollision() {
		
	// pretending like ant collided with a spider, so decrease health level by 1	
		theAnt.setHealthLevel(theAnt.getHealthLevel() - 1);
		
		
	// Calling checkSpeed after reduced health 
		theAnt.checkSpeed();
			
		
	// As ant get hit by more and more spiders, its color would get less and less red
		theAnt.setColor(ColorUtil.rgb(ColorUtil.red(theAnt.getColor()) - 10, 0, 0));
		
	// Check if food or health level is 0, then end the game
		this.checkLife();
		
	// Methods to notify observer and call update method of observers
		setChanged();
		notifyObservers();
	}
	
	
	/*CLOCKTICK
	 * This function tell the game world that the â€œgame clockâ€� has ticked
	 * It does following things:
	 * (1)  Spiders  update  their  heading  as  indicated  above.
	 * (2)  all  movable  objects  are  told  to  update  their  positions  according  to  their  current  heading  
	 * and  speed
	 * (3)  the  ants  food  level  is  reduced  by  the  amount  indicated  by  its  
	 * foodConsumptionRate
	 * (4)  the  elapsed  time  â€œgame  clockâ€�  is  incremented  by  one
	 * (5) Check foodlevel of the game, and if 0, end the game
	 */
	
	
	public void clockTick() {		
//(1)  Spiders  update  their  heading  as  indicated  above. 
		
		/*
		 * Use for loop for finding spider
		 * Get a randomNo
		 * ChangeDir using this random variable
		 */
		
		  for (int i=0; i<gameObjectsCollection.size(); i++) {
			  
			   if (gameObjectsCollection.get(i) instanceof Spider) { 
				   
			    Spider spider = (Spider)gameObjectsCollection.get(i); 
			    
			    int randomNo = rand.nextInt(2);
			    
			    if ( randomNo == 0) {
			    
			    	spider.changeDir(-5);
			   
			   } else if (randomNo == 1) {
				   
				   spider.changeDir(5);   
			  }		   
		   }
		}
	

//(2)  all  movable  objects  are  told  to  update  their  positions  according  to  their  current heading and speed ?????
		// Everytime clock ticks, Call move for movable methods
		
		  for (int i=0; i<gameObjectsCollection.size(); i++) { 
			   if (gameObjectsCollection.get(i) instanceof Movable) { 
			    Movable mObj = (Movable)gameObjectsCollection.get(i); 
			//Pass a constant 20mlsec as elapsed time between two ticks
			   mObj.move(20); 
			    
			 //call checkBoundaries to fix if object past game area
			   mObj.checkBoundaries(gameWorldWidth, gameWorldLength);
			   
			   }   
			 }
		  
		  		  
//(3)  the  antâ€™s  food  level  is  reduced  by  the  amount  indicated  by  its foodConsumptionRate,  
		  
			if ( gameObjectsCollection.get(4) instanceof Ant) {
				Ant ant = (Ant) gameObjectsCollection.get(4);
				ant.setFoodLevel(ant.getFoodLevel() - ant.getFoodConsumptionRate());	
				
			}
		
//(4)  the  elapsed  time  â€œgame  clock  is  incremented  by  one 
			clock++;
			
//(5) Check if food or health level is 0, then end the game
			this.checkLife();
			
			//Methods to notify observer and call update method of observers
			setChanged();
			notifyObservers();
	}
	
	/*CHECKLIFE
	 * This function check the current Life, 
	 * if all the lives are gone, game ends,
	 * otherwise repaint the game, with one less life
	 */
	void checkLife() {
		
		if (theAnt.getFoodLevel() <= 0 || theAnt.getHealthLevel() <= 0) {
			this.life = this.life -1;
			
			if (this.life <= 0) {
				System.out.println("Game  over,  you  failed!");
				this.exit();
			} else {
				System.out.println("You lost a Life. Game reinitialized!");
				this.init();
//				System.out.print("FoodLevel: " + theAnt.getFoodLevel());
			}	
		}	
	}
	
	/*CHECKWIN()
	 * This function check if ant has reached the lasFlag,
	 * if yes, it ends the game with wininig message
	 * 
	 */
	void checkWin() {
		
		if (theAnt.getLastFlagReached() == 4) {
			System.out.println("Game over, you win!");
			this.exit();
		}
			
	}
	
	//Methods For Accessing Ant private varibles in ScoreView
	int getAntLastFlagReached() {
		return theAnt.getLastFlagReached();
	}
	
	int getAntFoodLevel() {
		return theAnt.getFoodLevel();
	}
	
	int getAntHealthLevel() {
		return theAnt.getHealthLevel();
	}

	Ant getAnt() {
		return theAnt;
	}	
}
