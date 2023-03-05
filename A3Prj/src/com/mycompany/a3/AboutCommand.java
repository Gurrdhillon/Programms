package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class AboutCommand extends Command {
	
	private GameWorld gw = null;

	public AboutCommand(String command, GameWorld gw) {
		super(command);
		// TODO Auto-generated constructor stub
		this.gw = gw;
	}
	
	
	@Override //do not forget @Override, makes sure you are overriding parent method
	//invoked to perform the accelrate operation
	public void actionPerformed(ActionEvent ev){
		Dialog.show("About", "Name: Gursevak Singh\n Instructor: Dr.Pinar Muyan\n CSC 133\n Version 2" ,
				"OK", "Cancel");
	
	}

}
