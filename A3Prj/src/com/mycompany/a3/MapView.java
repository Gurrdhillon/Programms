package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.plaf.Border;
import com.mycompany.a3.GameWorld.GameObjectIterator;

public class MapView extends Container implements Observer {
	
	// Reference to the GameWorld is saved here
	private GameWorld gw = null;
	ISelectable selectedOne = null;
//	private Graphics g;
	
	
	MapView(GameWorld gw){
		this.gw = gw;
//		this.getAllStyles().setBorder(Border.createLineBorder(4,
//		ColorUtil.MAGENTA));
		this.getAllStyles().setBorder(Border.createLineBorder(4,
				ColorUtil.rgb(255,0,0)));
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
//		g.setColor(ColorUtil.BLACK);
//		System.out.println("X: " +  getX() +  "Y: " +  getY());
//		System.out.println("paint is invoked");			
//		g.fillRect(getX() + 100, getY() + 100,100,100);
		
		IIterator gameObjects = gw.getIterator();

		while(gameObjects.hasNext()) {
			gameObjects.getNext().draw(g, new Point(getX(), getY()));

		}
	}

	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		System.out.println("\n Map View\n");
		gw.map();
		repaint();
		
		//Should call the repaint()
		revalidate();
	}
	
	@Override
	public void pointerPressed(int x, int y) {
		
		if(gw.isGameRunning()) {
			//do nothing
		} else {
			
		System.out.println("In the Pointer Pressed");
		x = x - this.getParent().getAbsoluteX();
		y = y - this.getParent().getAbsoluteY();
		
		
		System.out.println("\nthis.getParent().getAbsoluteX(): " + this.getParent().getAbsoluteX());
		System.out.println("\nthis.getParent().getAbsoluteY(): " + this.getParent().getAbsoluteY());
		System.out.println("x: " + x);
		System.out.println("y: " + y);


		
		Point pPtrRelPrnt = new Point(x,y);
		Point pCmpRelPrnt = new Point(this.getX(), this.getY());
		
		//Take care of moving the Fixed Object
		if(gw.getPosition()) {
			
			((GameObject) selectedOne).setLocation(pPtrRelPrnt);
			gw.setPosition(false);
			
		} else {
			
			IIterator gameObjects = gw.getIterator();
			
			while(gameObjects.hasNext()) {
				
			GameObject next = gameObjects.getNext();
				if(next instanceof ISelectable) {
					
					if ( ((ISelectable) next).contains(pPtrRelPrnt,pCmpRelPrnt)) {
						((ISelectable) next).setSelected(true);
						selectedOne = (ISelectable) next;
					} else {
						((ISelectable) next).setSelected(false);
					}	
				}
			}
			
			}
		}
		repaint();
	}
}
