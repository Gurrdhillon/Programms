package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class ExitCommand extends Command {
	
	private GameWorld gw = null;

	public ExitCommand(String command, GameWorld gw) {
		super(command);
		// TODO Auto-generated constructor stub
		this.gw = gw;
	}
	
	
	@Override //do not forget @Override, makes sure you are overriding parent method
	//invoked to perform the accelrate operation
	public void actionPerformed(ActionEvent ev){
		System.out.println("\nExitCommand is invoked...\n");
		Boolean bOk = Dialog.show("Confirm quit", "Are you sure you want to quit?", 
				"Ok", "Cancel");

				if (bOk){
					gw.exit();
				}
		}
}
