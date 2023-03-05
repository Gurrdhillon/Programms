package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.plaf.Border;

public class MapView extends Container implements Observer {
	
	private GameWorld gw = null;
	
	MapView(GameWorld gw){
		this.gw = gw;
//		this.getAllStyles().setBorder(Border.createLineBorder(4,
//		ColorUtil.MAGENTA));
		this.getAllStyles().setBorder(Border.createLineBorder(4,
				ColorUtil.rgb(255,0,0)));
	}

	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		System.out.println("\n Map View\n");
		gw.map();
	}
}
