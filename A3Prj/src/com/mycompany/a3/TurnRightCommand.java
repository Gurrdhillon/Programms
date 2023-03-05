package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class TurnRightCommand extends Command {
	
	private GameWorld gw = null;

	public TurnRightCommand(String command, GameWorld gw) {
		super(command);
		// TODO Auto-generated constructor stub
		this.gw = gw;
	}
	
	
	@Override 
	//invoked to perform the accelrate operation
	public void actionPerformed(ActionEvent ev){
		System.out.println("\nTurnRightCommand is invoked...\n");
		gw.turnRight();
	}

}
