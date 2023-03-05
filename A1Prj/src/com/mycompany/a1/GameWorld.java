package com.mycompany.a1;

import java.util.ArrayList;
import java.util.Random;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;


/*Method  init()  is  responsible  for  creating  the  initial  state  of  the  world.    This  should  include 
adding to the game world at least the following:  a minimum of four Flag objects, positioned 
and sized as you choose and numbered sequentially defining the path (you may add more 
than four initial flags if you like - maximum number of flags you can add is nine); one Ant,
initially  positioned  at  the  flag  #1  with  initial  heading  which  is  zero,  initial  positive  non-zero 
speed as you choose, and initial size as you choose; at least two Spider objects, randomly 
positioned with a randomly-generated size, heading, and speed; and at least two 
FoodStation objects with random locations and with random sizes.   
*/

/* 
 * Constructor
 * 
 * This constructor initialize variables and crete a arraylist 
 * to hold GameObjects
 * 
 * @param: None
 * @return: NOne
 */



public class GameWorld {
	
	/*
	 * Collections to aggregate objects of 
	 * abstract type GameObject
	 */
	
	private int life = 3;
	
	private int clock = 0;
	
	private Ant theAnt= null;


	private int gameWorldLength = 1000;
	private int gameWorldWidth = 1000;
	Random rand;
	

	ArrayList<GameObject> gameObjects;
	
	GameWorld() {
		
		rand = new Random();
	
		gameObjects = new ArrayList<GameObject>();
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
	 *
	 * return: int (life)
	 */

	public int getClock() {
		return clock;
	}
																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																			                  

	/*
	 * Function INIT
	 * This function create and put in the game
	 * 4 flags, 1 ant, 2 spider, and 2 Foodstations
	 * 
	 */
	
	public void init() {
		
		
		/*ADDING 4 FLAGS
		 * size:10
		 * location: (10, 10), (400,400), (200,600), (990,990)
		 * color: blue
		 * flagNo: 1,2,3,4
		 */ 
		
		Flag f1 = new Flag(10,new Point(10,10), ColorUtil.rgb(1,0,255), 1);
		Flag f2 = new Flag(10,new Point(400,400), ColorUtil.rgb(1,0,255), 2);
		Flag f3 = new Flag(10,new Point(200,600), ColorUtil.rgb(1,0,255), 3);
		Flag f4 = new Flag(10,new Point(990,990), ColorUtil.rgb(1,0,255), 4);
		
		
		gameObjects.add(f1);
		gameObjects.add(f2);
		gameObjects.add(f3);
		gameObjects.add(f4);
		
		
		/*
		 * ADDING 1 ANT
		 * size: 10
		 * color: black
		 * speed: 10
		 * heading:0
		 * location: flag1 location
		 */
		
		Ant ant = new Ant(10, ColorUtil.rgb(0,0,0), 10, 0, gameObjects.get(0).getLocation());
		theAnt = ant;
		
		gameObjects.add(ant);
		
		
		/*
		 * ADDING 2 SPIDERS
		 * size: 8
		 * color: red
		 * speed: 4
		 * heading:random
		 * location: random
		 */
		
		Point s1RandomLoc = new Point(gameWorldLength*rand.nextFloat(),gameWorldWidth*rand.nextFloat());
		double s1RandomHeading =  360*rand.nextDouble();
		Point s2RandomLoc = new Point(gameWorldLength*rand.nextFloat(),gameWorldWidth*rand.nextFloat());
		double s2RandomHeading =  360*rand.nextDouble();
		Spider s1 = new Spider(8, ColorUtil.rgb(255,0,0),4,s1RandomHeading, s1RandomLoc);
		Spider s2 = new Spider(8, ColorUtil.rgb(255,0,0),4,s2RandomHeading, s2RandomLoc);
		
		
		gameObjects.add(s1);
		gameObjects.add(s2);
		
		

		/*
		 * ADDING 2 FOODSTATIONS
		 * size: random
		 * location: random
		 * color: green
		 * capacity: equal to size
		 */
		
		int fs1RandomSize = rand.nextInt(5);
		Point fs1LocationRandom = new Point(gameWorldLength*rand.nextFloat(),gameWorldWidth*rand.nextFloat());  //bw 0 and 1000
		int fs2RandomSize = rand.nextInt(20);
		Point fs2LocationRandom = new Point(gameWorldLength*rand.nextFloat(),gameWorldWidth*rand.nextFloat());  //bw 0 and 1000
		
		FoodStation fs1 = new FoodStation(fs1RandomSize,fs1LocationRandom,ColorUtil.rgb(0,255,0),fs1RandomSize);
		FoodStation fs2 = new FoodStation(fs2RandomSize,fs2LocationRandom,ColorUtil.rgb(0,255,0),fs2RandomSize);
		
		
		gameObjects.add(fs1);
		gameObjects.add(fs2);
		
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
	 * GameObjects by invoking their toString()
	 * method, which is been overridden for 
	 * every object
	 * It does not print FoodStation if its already used
	 * 
	 */
	
	public void map() {
		
		for (int i=0; i < gameObjects.size(); i++) {
			
			if(!( gameObjects.get(i) instanceof FoodStation && ((FoodStation) gameObjects.get(i)).getCapacity() == 0)) {
				System.out.println(gameObjects.get(i));
			}
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
			System.out.println("BRakes can't be applied");
		}
	}
	

	/*
	 *function TURNLEFT
	 * This function turn ant to left by 5 degree,
	 * and then call move to update Ant's location
	 */
	
	public void turnLeft() {
		theAnt.steer(- 5);
		theAnt.move();
	}
	
	
	/*
	 * TURNRIGHT 
	 * This function turn ant to right by 5 degree
	 * and then call move to update Ant's location
	 */
	
	public void turnRight() {
		theAnt.steer(5);
		theAnt.move();
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
		
	}
	
	
	/*FOODSTATIONLOCATED
	 * This function pretend that and has gotten food, ant food level increase
	 * and then food capacity of that station would go to zero, then
	 * it create another foodStand with random newLocateion and  capacity
	 * 
	 */
	
	public void foodStationLocated() {

			//Picking a non empty food station randomly
			//So I have 2 station, I gotta pick on of those two, 
			//I can also do a for loop here, if I dont know 
			//which object is foodStation, bt here I know, I will pick one of those two
		
			
			/*
			 * Picking a foodStation
			 */
			int x = 7 + rand.nextInt((gameObjects.size() - 7) + 1);
			FoodStation fs = (FoodStation) gameObjects.get(x);
			

			/*
			 * Making sure picked foodStation is not used, if used, pick another one
			 */
			while (fs.getCapacity() == 0) {
				
				x = 7 + rand.nextInt((gameObjects.size() - 7) + 1);
				 
				fs = (FoodStation) gameObjects.get(x);
				
			}
			
			
			theAnt.setFoodLevel(theAnt.getFoodLevel() + fs.getCapacity());
			
			fs.setCapacity(0);
			
			//Set Color of food station to light green
			fs.setColor(ColorUtil.rgb(124,252,0));
			
			//Creating new foodStation with random capacity and random location
			int fsNewRandomSize = rand.nextInt(20);
			
			Point fsNewLocationRandom = new Point(1000*rand.nextFloat(),1000*rand.nextFloat());  //bw 0 and 1000
			
			FoodStation fsnew = new FoodStation(fsNewRandomSize,fsNewLocationRandom,ColorUtil.rgb(0,255,0),fsNewRandomSize);
			
			gameObjects.add(fsnew);
		
	}
	
	
	/*
	 * PRETEND  that  a  spider  has  gotten  to  the  same  location  and  collided  with  the  ant.  
The  effect  of  colliding  with  a  spider  is  to  decrease  the  health  level  of  the  ant  as 
described  above,  fade  the  color  of  the  ant  (i.e.,  it  becomes  lighter  red  â€“  throughout 
the  game,  the  ant  can  have  different  shades  of  red),  and  (if  necessary)  reduce  the  
speed  of  the  ant  so  that  above-mentioned  speed-limitation  rule  is  enforced.  Since 
currently  no  change is introduced to the spider after the collision, it does not matter 
which spider is picked. 
	 */
	
	
	/*
	 * SPIDERCOLLISION()
	 * This function pretend that ant has collided with the Spider,
	 * it reduce the health of Ant by 1,
	 * and the it call checkSpeed() to adjust speed,
	 * according to health level, and then fade the color of Ant
	 */
	//Need to be worked ON VERY MUCH, need to built logic for health speed and food
	public void spiderCollision() {
		
	//pretending like ant collided with a spider, so decrease health level by 1	
		theAnt.setHealthLevel(theAnt.getHealthLevel() - 1);
		
		
	//Calling checkSpeed after reduced health 
		theAnt.checkSpeed(theAnt.getSpeed());
			
		
	//As ant get hit by more and more spiders, its color would get less and less red
		theAnt.setColor(ColorUtil.rgb(ColorUtil.red(theAnt.getColor()) - 10, 0, 0));
		
	// Check if food or health level is 0, then end the game
		this.checkLife();
		
	}
	
	
	
	/*CLOCKTICK
	 * This function tell the game world that the â€œgame clockâ€� has ticked
	 * It does following things:
	 * (1)  Spiders  update  their  heading  as  indicated  above.
	 * (2)  all  movable  objects  are  told  to  update  their  positions  according  to  their  current  heading  
	 * and  speed
	 * (3)  the  antâ€™s  food  level  is  reduced  by  the  amount  indicated  by  its  
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
		
		  for (int i=0; i<gameObjects.size(); i++) {
			  
			   if (gameObjects.get(i) instanceof Spider) { 
				   
			    Spider spider = (Spider)gameObjects.get(i); 
			    
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
		
		  for (int i=0; i<gameObjects.size(); i++) { 
			   if (gameObjects.get(i) instanceof Movable) { 
			    Movable mObj = (Movable)gameObjects.get(i); 
			   mObj.move(); 
			   
			   //call checkBoundaries to fix if object past game area
			   mObj.checkBoundaries(gameWorldWidth, gameWorldLength);
			   
			   
			   }   
			 }
		  
		  		  
//(3)  the  antâ€™s  food  level  is  reduced  by  the  amount  indicated  by  its foodConsumptionRate,  
		  
			if ( gameObjects.get(4) instanceof Ant) {
				Ant ant = (Ant) gameObjects.get(4);
				ant.setFoodLevel(ant.getFoodLevel() - ant.getFoodConsumptionRate());	
				
			}
		
//(4)  the  elapsed  time  â€œgame  clockâ€�  is  incremented  by  one 
			clock++;
			
//(5) Check if food or health level is 0, then end the game
			this.checkLife();
	
	}
	
	
	/*CHECKLIFE
	 * This function check the current Life, 
	 * if all the lives are gone, game ends,
	 * otherwise repaint the game, with one less life
	 */
	void checkLife() {
		
		if (theAnt.getFoodLevel() == 0 || theAnt.getHealthLevel() == 0) {
			this.life = this.life -1;
			
			if (this.life == 0) {
				System.out.println("Game  over,  you  failed!");
				this.exit();
			} else {
				System.out.println("You lost a Life. Game reinitialized!");
				this.init();
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
}
