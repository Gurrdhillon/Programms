package com.mycompany.a1;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public abstract class GameObject {
	
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
		

		 String myDesc = "loc=" + Math.round(this.getLocation().getX()) + ","  +  Math.round(this.getLocation().getY()) +
						", color=" + "["  + ColorUtil.red(this.getColor()) + ","  
						+ ColorUtil.green(this.getColor()) + ","  
						+ ColorUtil.blue(this.getColor()) + "]"
						+ ", size=" + this.getSize();
		 return  myDesc ; 
		
	}
	
	

}
