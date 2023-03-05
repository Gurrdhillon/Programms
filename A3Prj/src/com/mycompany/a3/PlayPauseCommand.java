package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class PlayPauseCommand extends Command {
	private GameWorld gw = null;
	private Game g = null;
	
	public PlayPauseCommand(String command,Game g) {
		///Pass the game to the this commands and create play pasue methods in the game
		super(command);
		// TODO Auto-generated constructor stub
		this.g = g;
	}
	
	@Override //do not forget @Override, makes sure you are overriding parent method
	//invoked to perform the accelrate operation
	public void actionPerformed(ActionEvent ev) {
		
		if(g.getRunning()) {
			System.out.println("\nPlay Command Invoked\n");
			g.pauseGame();
		} else {
			System.out.println("\nPause Command Invoked\n");

			g.playGame();
		}
		
		g.updateButtons();
	}
}