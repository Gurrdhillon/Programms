package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public abstract class GameObject implements IDrawable,ICollider {
	
	//I am not sure if I should have these variables here or not
	private Point location = new Point();
	private int color;
	private int size = 0;             //Default value of size is assumed to be 0
	
	
	
	/*
	 * CONSTRUCTORS
	 */
	GameObject(int size) {
		this.size = size;
	}
	

	GameObject(int size, Point location) {
		this.size = size;
		this.location = location;
	}
	
	
	GameObject(int size, int color) {
		this.size = size;
		this.color = color;
	}
	
	GameObject(int size, Point location, int color) {
		this.size = size;
		this.location = location;
		this.color = color;
	}
	
	
	
	/*
	 * SETCOLOR
	 * This function sets the color of object
	 * @ param: color (of the object)
	 * @ return: void
	 */
	void setColor(int color) {
		this.color = color;
	}
	
	
	/*
	 * SETLOCATION
	 * This function sets the loation of object
	 * @ param: point (location of the object)
	 * @ return: void
	 */
	void setLocation(Point point) {
		this.location = point;
		
	}
		
	
	Point getLocation() {
		return location;
	}
	
	int getColor() {
		return color;
	}
	
	
	int getSize() {
		return size;
	}
	
	
	@Override
	public String toString() {
		
		 String myDesc = "loc=" + Math.round(this.getLocation().getX()) + ","  +  
				 				Math.round(this.getLocation().getY()) +
						", color=" + "["  + ColorUtil.red(this.getColor()) + ","  
						+ ColorUtil.green(this.getColor()) + ","  
						+ ColorUtil.blue(this.getColor()) + "]"
						+ ", size=" + this.getSize();
		 return  myDesc ; 
	}
	
	@Override
	public boolean colliderWith(GameObject otherObject, Point pnt) {
		// TODO Auto-generated method stub
		
		boolean result = false;
		
		float centerX = pnt.getX();
		float centerY = pnt.getY();
		
		int thisCentreX = (int) (centerX + (int) this.getLocation().getX() + (this.getSize()/2));
		int thisCentreY = (int) (centerY + (int) this.getLocation().getY() + (this.getSize()/2));
		
//		System.out.println("thisX: " + this.getLocation().getX() + "\nthisY: " + this.getLocation().getY());
//		System.out.println("In ColludeWith\nthisCentreX: " + thisCentreX + "\nthisCentreY" + thisCentreY);
		
		int otherCenterX = (int) ((int) centerX + otherObject.getLocation().getX() + (this.getSize()/2));
		int otherCenterY = (int) ((int) centerY + otherObject.getLocation().getY() + (this.getSize()/2));
		
		int dx = thisCentreX - otherCenterX;
		int dy = thisCentreY - otherCenterY;
		
		int distanceBetweenCentersSqr = (dx*dx + dy*dy);
		int thisRadius = this.getSize();
		int otherRadius = otherObject.getSize()/2;
		
		int radiisqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius + otherRadius*otherRadius);
		
		System.out.println("gap: " + (distanceBetweenCentersSqr - radiisqr) + "\n");
		if (distanceBetweenCentersSqr <= radiisqr) {
			result = true;
		}
		
		return result;
	}
	
	@Override
	public void handleCollision(GameObject otherObject) {
		// TODO Auto-generated method stub
		
	}	

}
