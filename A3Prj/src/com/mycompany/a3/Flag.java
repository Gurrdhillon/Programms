package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;



public class Flag extends Fixed {
	
	int seqNum = 1;
	int size = this.getSize();

	
	/*Flags are not allowed to change size, location and color 
	 * once created, so that's why I am passing it to 
	 * the parent constructor using super
	 */
	
	Flag(int size, Point location, int color, int seqNo) {
		super(size,location,color);
		this.seqNum = seqNo;
	}

	
/*SETCOLOR
 * Overriding paret's class setColor() function 
 * because flags are not allowed to change color
 * once created
 * 
 * @ param: color ( new color of the flag) 
 * @ return: void
 */
	
	@Override
	void setColor(int color) {
		// TODO Auto-generated method stub
		System.out.print("Oops!! flags color can't be changed");
	}
	
	
	int getSeqNum() {
		return this.seqNum;
	}


	@Override
	public String toString() {
		
			String parentDesc = super.toString(); 
			
			String myDesc = "Flag:" + parentDesc + ", seqNum=" + this.getSeqNum();

			return  myDesc ; 		 
	}
	
	
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {

		// Flag is represented with unfilled isosceles triangle
		float centerX = pCmpRelPrnt.getX() + this.getLocation().getX();
		float centerY = pCmpRelPrnt.getY() + this.getLocation().getY();
		
		int upperLeftX = (int) (centerX - this.getSize()/2);
		int upperLeftY = (int) (centerY - this.getSize()/2);

		int[] xCordinates = {upperLeftX, upperLeftX+size*2, upperLeftX+size};
		int [] yCordinates = {upperLeftY,upperLeftY,upperLeftY+size};
		g.setColor(ColorUtil.rgb(145, 168, 181));
		
		if(!this.isSelected()) {
			g.fillPolygon(xCordinates,yCordinates,3);
		} else {
			g.drawPolygon(xCordinates, yCordinates, 3);
		}

		g.setColor(this.getColor());
		g.drawString(String.valueOf(seqNum),(int)centerX + size/2,(int)centerY );
		
		}

	
	/*
	 * Methods to check if point is in triangle
	 * It takes 6 parameters, point x and y and triangle x and y coordinates
	 */
	boolean intpoint_inside_triangle(int sx, int sy, int ax, int ay, int bx, int by, int cx, int cy)
	{
	    int as_x = sx - ax;
	    int as_y = sy - ay;

	    boolean s_ab = (bx - ax) * as_y - (by - ay) * as_x > 0;

	    if ((cx - ax) * as_y - (cy - ay) * as_x > 0 == s_ab) 
	        return false;
	    if ((cx - bx) * (sy - by) - (cy - by)*(sx - bx) > 0 != s_ab) 
	        return false;
	    return true;
	}

	
	@Override
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		
		int px = (int) pPtrRelPrnt.getX();
		int py = (int) pPtrRelPrnt.getY();
		
		float centerX = pCmpRelPrnt.getX() + this.getLocation().getX();
		float centerY = pCmpRelPrnt.getY() + this.getLocation().getY();
		
		int xLoc = (int) (centerX - this.getSize()/2);
		int yLoc = (int) (centerY - this.getSize()/2);
		
		if (intpoint_inside_triangle(px,py,xLoc,yLoc, xLoc+size*2,yLoc, xLoc+size,yLoc + size)) {
			System.out.println("\nIN FLAG CONTAINS\n");
			return true;
		} else {
			return false;
		}
	}
}
