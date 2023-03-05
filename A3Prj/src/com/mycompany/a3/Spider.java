package com.mycompany.a3;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;


/*
 * Spiders are movable (but not steerable objects which walk on the ground the path is on. 
They  add  (or  subtract)  small  random  values  (e.g.,  5  degrees)  to  their  heading  while  they  
move  so  as  to  not  run  in  a  straight  line.  If  the  spider’s  center  hits  a  side  of  the  world,  it  
changes heading and does not move out of bounds. If the spider gets to the same location 
as  the  ant  it  decreases  the  health  level  of  the  ant  by  one.  Spiders  are  not  allowed  to  
change color once they are created. Speed of a spider should be initialized to a reasonable 
random  value  (e.g.,  ranging  between  5  and  10)  at  the  time  of  instantiation.  Heading  of  a 
spider should be initialized to a random value (ranging between 0 and 359) at the time of 
instantiation
 */



public class Spider extends Movable {
	
   
    Spider(int size, int color, int speed, double heading, Point location) { 
    	
    	super(size, heading,color);
    	this.setSpeed(speed);      //Between 5 and 10
		super.setLocation(location);
    }
    
    
    @Override
    void setColor(int color) {
    	System.out.println("Color can't be changed");
    }
    
    @Override
    void setHeading(double heading) {
    	System.out.println("OOPS! You cant set heading. 404!! ");	
    }
    
    
//    @Override
//    void setLocation(Point location) {
//    	
//    }


    /*function CHANGEDIR
     * This function change direction of Spider 
     * by change degrees
     * 
     * @ param: change (change to be applied to the direction of spider)
     * @ return: void
     * 
     */
    void changeDir(double change) {
    	super.setHeading(getHeading() + change);
    }
    
    
	@Override
	public String toString() {
		
			String parentDesc = super.toString(); 
			
			String myDesc = "Spider:" + parentDesc;

			 return  myDesc ; 
			 
	}


	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		// Spider is represented with unfilled isosceles triangle
		

		float centerX = pCmpRelPrnt.getX() + this.getLocation().getX();
		float centerY = pCmpRelPrnt.getY() + this.getLocation().getY();
		
		int upperLeftX = (int) (centerX - this.getSize()/2);
		int upperLeftY = (int) (centerY - this.getSize()/2);

		int[] xCordinates = {upperLeftX, upperLeftX+200, upperLeftX+100};
		int [] yCordinates = {upperLeftY,upperLeftY,upperLeftY+100};
//		g.setColor(ColorUtil.BLACK);
		g.setColor(this.getColor());
//		g.fillArc(x, y, radius, radius, 0, 180);
		g.drawPolygon(xCordinates,yCordinates,3);
		
		}
	}