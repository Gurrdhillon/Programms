package com.mycompany.a2;

import com.codename1.charts.models.Point;


public class Ant extends Movable implements ISteerable {
	
	//Static ant instance for singleton design pattern
	private static Ant theAnt;
	
	final int maximumSpeed = 50;
	final int foodConsumptionRate = 2;
	private int foodLevel = 10;
	private int healthLevel = 10;         //Set according to given assignemnt
	private int lastFlagReached = 1;
	
	Point antLocation = null;
	
	

	
	/*
	 * Constructor
	 */
	
	
	private Ant(int size, int color, int speed, int heading, Point location) {
		super(size, heading);
		this.setColor(color);
		this.setSpeed(speed);
		this.setLocation(location);
		this.healthLevel = 10;
		this.lastFlagReached = 1;
		this.foodLevel = 10;
		
	}
	
	public static Ant getAnt(int size, int color, int speed, int heading, Point location) {
		
		if (theAnt == null) {
			theAnt = new Ant(size, color, speed, heading, location);
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
			//dp nothing	
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
		
		if(newheading < 0) {
			newheading = 360 + newheading;
		} else if (newheading >359) {
			newheading = newheading - 360;
		}
		
		this.setHeading(newheading);
		
	}

}
