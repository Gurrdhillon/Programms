package com.mycompany.a1;

import com.codename1.charts.models.Point;

public abstract class Fixed extends GameObject {
	

	
	/*
	 * All fixed game objects are not allowed to change location
	 * once they are created
	 */
	
	Fixed(int size, Point location){
		super(size, location);
	}
	
	Fixed(int size, Point location, int color) {
		super(size, location, color);
	}
	
	
	/*
	 * Fixed Object location can't be changed once Initialized,
	 * so here setLocation is overrided to implement this
	 * for fixed objects
	 */
	@Override
	 void setLocation(Point point) {
		System.out.println("Fixed Objects Location can't be changed");
	}	
	
}
