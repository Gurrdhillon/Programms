package com.mycompany.a1;

import com.codename1.charts.models.Point;



public class Flag extends Fixed {
	
	int seqNum = 1;

	
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

}
