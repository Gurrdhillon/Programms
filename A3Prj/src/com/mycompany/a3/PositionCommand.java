package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class PositionCommand extends Command {
	private GameWorld gw = null;
	private Game g = null;
	
	public PositionCommand(String command,GameWorld g) {
		///Pass the game to the this commands and create play pasue methods in the game
		super(command);
		// TODO Auto-generated constructor stub
		this.gw = g;
	}
	
	@Override //do not forget @Override, makes sure you are overriding parent method
	//invoked to perform the accelrate operation
	public void actionPerformed(ActionEvent ev) {
		
		//Call some kind of position method
		
		gw.setPosition(true);
		
		}

}