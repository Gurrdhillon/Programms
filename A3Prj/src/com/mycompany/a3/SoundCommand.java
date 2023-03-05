package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class SoundCommand extends Command {
	
	private GameWorld gw = null;

	public SoundCommand(String command, GameWorld gw) {
		super(command);
		// TODO Auto-generated constructor stub
		this.gw = gw;
	}
	
	
	@Override //do not forget @Override, makes sure you are overriding parent method
	//invoked to perform the accelrate operation
	public void actionPerformed(ActionEvent ev){
		System.out.println("\nSoundCommand is invoked...\n");
		gw.toggleSound();
	
	}

}
