package com.mycompany.a3;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;



public class FoodStation extends Fixed {
	
    private int capacity;
    
    
    /*
     * FoodStation can'r change size or location once created, so that's why
     * i am passing it to parents constructor 
     */
	FoodStation(int size,Point location, int color, int capacity) {
		
		
		super(size, location);
		this.setColor(color);
		this.capacity = this.getSize(); //Iniitial capacity proportional to size of foodstation
	}
	

		
	void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	int getCapacity() {
		return capacity;
	}


	@Override
	public String toString() {
		
			String parentDesc = super.toString(); 
			
			String myDesc = "FoodStation:" + parentDesc + ", capacity=" + this.getCapacity();

			return  myDesc ;  
		}
	

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		// Draw Filled Squares of sidelength 5 to represent FoodStation
		int squareSide = this.getSize();
		float centerX = pCmpRelPrnt.getX() + this.getLocation().getX();
		float centerY = pCmpRelPrnt.getY() + this.getLocation().getY();
		
		int upperLeftX = (int) (centerX - this.getSize()/2);
		int upperLeftY = (int) (centerY - this.getSize()/2);
		g.setColor(this.getColor());
		
		if(!this.isSelected()) {
			g.fillRect(upperLeftX, upperLeftY, squareSide, squareSide);
		} else {
			g.drawRect(upperLeftX, upperLeftY, squareSide, squareSide);
		}

		g.setColor(ColorUtil.BLACK);
		g.drawString(String.valueOf(capacity),(int)centerX,(int)centerY);
	}



	/*
	 * pPtrRelPrnt: pointer relative to parent origin
	 * pCmpRelPrnt: component position relative to parent origin
	 */

	@Override
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		
		
		int px = (int) pPtrRelPrnt.getX();
		int py = (int) pPtrRelPrnt.getY();
		
		float centerX = pCmpRelPrnt.getX() + this.getLocation().getX();
		float centerY = pCmpRelPrnt.getY() + this.getLocation().getY();
		
		int xLoc = (int) (centerX - this.getSize()/2);
		int yLoc = (int) (centerY - this.getSize()/2);
		

		if ((px >= xLoc) && (px <= xLoc + this.getSize()) && ((py >= yLoc) && py <= yLoc + this.getSize())) {
			System.out.println("\nIN FOODSTATION CONTAINS\n");
			return true;
		} else {
			return false;
		}
	}
	
	
}
