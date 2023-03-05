package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.models.Point;

public abstract class Movable extends GameObject {
	
	/* 
	 * Now, I need to have fields here which are local 
	 * to Movable, and not part of parentClass
	 */
	
	private double heading = 0;
	private double speed = 0;
	private Random rand = new Random();
	
/* 
 * CONSTRUCTORS
 * 
 */
	public Movable(int size, double heading) {
		// TODO Auto-generated constructor stub
		super(size);
		this.heading = heading;
	}
	
	Movable(int size, double heading, int color) {
		super(size, color);
		this.heading = heading;
	}
	
	
	void setHeading(double heading) {
		this.heading = heading;
		
	}


	void setSpeed (double speed) {
		this.speed = speed;
	}
	
	double getSpeed() {
		return this.speed;
	}
	
	double getHeading() {
		return this.heading;
	}
	
	
	/***********************************************************************
	 * 
	 * Heading  is  specified  by  a  compass  angle  in  degrees:  0  means  
	 * heading north (upwards on the screen), 90 means heading east 
	 * (rightward on the screen), etc.  
	 * 
	 ***********************************************************************/
	
	/*MOVE
	 * This function updates the location 
	 * of the movable obect in accordance with its
	 * current heading and speed
	 */
	
	void move(int timeElapsed) {
		
		double distance = speed*timeElapsed/1000;
		
		float deltaX = (float) (Math.cos(Math.toRadians(90-heading))*distance);  
		float deltaY = (float) (Math.sin(Math.toRadians(90 - heading))*distance);
		float oldX = this.getLocation().getX();
		float oldY = this.getLocation().getY();
		
		//check location boundaries
		Point updatedPoint = new Point(oldX + deltaX, oldY + deltaY);
		this.setLocation(updatedPoint);
	}
	
	
	@Override
	public String toString() {
		
			String parentDesc = super.toString(); 
			
			String myDesc = parentDesc + ", heading=" + Math.round(this.getHeading())
							+ ", speed=" + Math.round(this.getSpeed());
			 return  myDesc ; 
			 
	}
	
	/*CHECKBOUNDARIES
	 * This function check if object is
	 * in boundaries, if it getting out of boundaries
	 * it reset its location
	 * @param: width ( width of game play)
	 * @param: length (length of the game play)
	 */
	

	public void checkBoundaries(int width, int length) {
		
		if (this.getLocation().getX() >= width || this.getLocation().getY() >= length
				|| this.getLocation().getX() < 0 || this.getLocation().getY() < 0) {
			
			System.out.println("\n\n\n X: " + this.getLocation().getX() + ", Y: " + this.getLocation().getY());
			System.out.println("width: " + width + " length: " + length);
			System.out.println("\n\n\n");
			
			Point s1RandomLoc = new Point(width*rand.nextFloat(),length*rand.nextFloat());
			//Location is resetted to 0 when knock with borders, 
			//I think it should be a random values, will think about it later on 
			this.setLocation(s1RandomLoc);
			
			System.out.print("Location resetted.");
		}
		

		
	}
;
}
