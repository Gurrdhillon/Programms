package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.plaf.Border;

public class MyButton extends Button {
	
	MyButton(String string){
		super(string);
		this.getUnselectedStyle().setBgTransparency(255);
		this.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		this.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		
		this.getUnselectedStyle().setBorder(Border.createLineBorder(3,ColorUtil.BLACK));

		//add padding to all styles of button2
		this.getAllStyles().setPadding(Component.TOP, 5);
		this.getAllStyles().setPadding(Component.BOTTOM, 5);
		this.getAllStyles().setPadding(Component.LEFT,1);
		this.getAllStyles().setPadding(Component.RIGHT,1);
	}

}
