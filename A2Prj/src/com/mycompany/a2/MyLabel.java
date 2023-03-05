package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Label;

public class MyLabel extends Label {
	
	MyLabel(){
		this.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
	}
	
	MyLabel(String label){
		super(label);
		this.getUnselectedStyle().setFgColor(ColorUtil.BLUE);
	}
	
}
