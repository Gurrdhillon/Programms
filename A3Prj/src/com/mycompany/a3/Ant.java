package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;


public class Ant extends Movable implements ISteerable {
	
	//Static ant instance for singleton design pattern
	private static Ant theAnt;
	
	final int maximumSpeed = 5000;
	final int foodConsumptionRate = 2;
	private int foodLevel = 1000;
	private int healthLevel = 100;         //Set according to given assignemnt
	private int lastFlagReached = 1;
	
	Point antLocation = null;
	
	//New in A3
	GameWorld gw = null;
	
	
	/*
	 * Constructor
	 */
	private Ant(int size, int color, int speed, int heading, Point location, GameWorld gw) {
		super(size, heading);
		this.setColor(color);
		this.setSpeed(speed);
		this.setLocation(location);
		this.healthLevel = 100;
		this.lastFlagReached = 1;
		this.foodLevel = 1000;
		this.gw = gw;
	}
	
	public static Ant getAnt(int size, int color, int speed, int heading, Point location, GameWorld gw) {
	
		/////CREATING ISSUES WHILE TRYING TO REINTIIALIZE GAME
//		if (theAnt == null ) {
//			theAnt = new Ant(size, color, speed, heading, location);
//		}
		
	if (theAnt == null || theAnt.foodLevel == 0 || theAnt.healthLevel == 0 ) {
		theAnt = new Ant(size, color, speed, heading, location, gw);
	}
	
		return theAnt;
	}
	

	public void setFoodLevel(int foodLevel) {
		this.foodLevel = foodLevel;
	}

	
	public void setHealthLevel(int healthLevel) {
		this.healthLevel = healthLevel;
	}
	
	public void setLastFlagReached(int lastFlagReached) {
		this.lastFlagReached = lastFlagReached;
	}
	
	
	public int getMaximumSpeed() {
		return maximumSpeed;
	}


	public int getFoodLevel() {
		return foodLevel;
	}


	public int getFoodConsumptionRate() {
		return foodConsumptionRate;
	}

	public int getHealthLevel() {
		return healthLevel;
	}

	public int getLastFlagReached() {
		return lastFlagReached;
	}
	
	
	/*CHECKSPEED
	 * This function adjust speed of 
	 * ant according to its health and 
	 * food level
	 * 
	 * @param: speed, current speed of Ant
	 * @return: void
	 */
	void checkSpeed() {
		
		if (healthLevel >= 10) {
			//do nothing	
		} else if (healthLevel >= 9 && healthLevel < 10 && this.getSpeed() > 0.9 * maximumSpeed) {
			this.setSpeed(0.9 * maximumSpeed);
		} else if (healthLevel  >= 7 && healthLevel < 9 && this.getSpeed() > 0.7 * maximumSpeed) {
			this.setSpeed(0.7 * maximumSpeed);
		} else if (healthLevel >= 5 && healthLevel < 7 && this.getSpeed() > 0.5 * maximumSpeed) {
			this.setSpeed(0.5 * maximumSpeed);
		} else if (healthLevel >= 3 && healthLevel < 5 && this.getSpeed() > 0.3 * maximumSpeed) {
			this.setSpeed(0.3 * maximumSpeed);
		} else if (healthLevel >= 1  && healthLevel < 3 && this.getSpeed() > 0.1 * maximumSpeed) {
			this.setSpeed(0.1 * maximumSpeed);
		}
	}
		
		
	@Override
	public String toString() {
	
		String parentDesc = super.toString(); 
		
		String myDesc = "Ant:" + parentDesc + ", maxSpeed="+ Math.round(this.getMaximumSpeed())
						+ ", foodConsumptionRate=" + this.getFoodConsumptionRate();

		return  myDesc ; 
			 
	}  
	
	
	/*
	 * This function is implementation of interfaces method steer
	 * It steer the Ant in given direction
	 */
	
	@Override
	public void steer(double d) {
		
		double newheading = this.getHeading() + d;
		
		if (newheading < 0) {
			newheading = 360 + newheading;
		} else if (newheading >359) {
			newheading = newheading - 360;
		}
		
		this.setHeading(newheading);
		
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		// Ant would be displayed as Filled Circle
		int radius = this.getSize();
		float centerX = pCmpRelPrnt.getX() + this.getLocation().getX();
		float centerY = pCmpRelPrnt.getY() + this.getLocation().getY();
		
		
		
		
		int upperLeftX = (int) (centerX - radius/2);
		int upperLeftY = (int) (centerY - radius/2);
		
//		System.out.println("parentX: " + pCmpRelPrnt.getX() + "\nparentY: " + pCmpRelPrnt.getY());
//		System.out.println("centerX: " + centerX + "\ncenterY: " + centerY);
//		System.out.println("upperLeftX: " + upperLeftX + "\nupperLeftY: " + upperLeftY);
		
		g.setColor(this.getColor());
		g.fillArc(upperLeftX,upperLeftY,2*radius, 2*radius, 0,360);
//		g.setColor(this.getColor());	
	}


	@Override
	public void handleCollision(GameObject otherObject) {
		
		if (otherObject instanceof Spider) {
			System.out.println("\n\n\nSpider instance in handle Collision\n\n");
			gw.spiderCollision();
		} else if (otherObject instanceof FoodStation) {
			System.out.println("\n\nFoodStation instance in handle Collision\n\n");
			gw.foodStationLocated((FoodStation) otherObject);
		} else if (otherObject instanceof Flag) {
			System.out.println("\n\nFlag instance in handle Collision\n\n");
			gw.nextFlagReached(((Flag) otherObject).getSeqNum());
		}	
	}

}
