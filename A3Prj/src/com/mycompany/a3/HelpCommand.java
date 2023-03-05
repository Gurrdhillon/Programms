package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class HelpCommand extends Command {
	
	private GameWorld gw = null;

	public HelpCommand(String command, GameWorld gw) {
		super(command);
		// TODO Auto-generated constructor stub
		this.gw = gw;
	}
	
	
	@Override //do not forget @Override, makes sure you are overriding parent method
	//invoked to perform the accelrate operation
	public void actionPerformed(ActionEvent ev){
		Dialog.show("Help", "c: Accelrate\n "
							+ "b: Brake\n" 
							+ "l: Left Turn\n" 
							+ "f: Food Station Collision\n" 
							+ "g: Spider Collison\n" 
							+ "t: Clock Tick",
				"OK", "Cancel");
	
	}

}
