package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.Image;
import com.codename1.ui.events.ActionEvent;

public class SpiderCollisionCommand extends Command {

	private GameWorld gw = null;

	public SpiderCollisionCommand(String command, GameWorld gw) {
		super(command);
		// TODO Auto-generated constructor stub
		this.gw = gw;
	}
	
	
	@Override //do not forget @Override, makes sure you are overriding parent method
	//invoked to perform the accelrate operation
	public void actionPerformed(ActionEvent ev){
		System.out.println("\nSpiderCollisionCommand is invoked...\n");
		gw.spiderCollision();;
	}

}
