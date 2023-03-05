package com.mycompany.a2;
import com.codename1.charts.models.Point;



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
}
