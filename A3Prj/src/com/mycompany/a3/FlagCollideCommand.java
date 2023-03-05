package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;

public class FlagCollideCommand extends Command {
	
	private GameWorld gw = null;

	public FlagCollideCommand(String command, GameWorld gw) {
		super(command);
		// TODO Auto-generated constructor stub
		this.gw = gw;
	}
	
	
	@Override //do not forget @Override, makes sure you are overriding parent method
	//invoked to perform the accelrate operation
	public void actionPerformed(ActionEvent ev){
		System.out.println("\nFoodStationLocatedCommand is invoked...\n");

		Command cOk = new Command("Ok");

		TextField myTF = new TextField();

		//[if you only want to display the okay option, you do not need to
		//create “cmds”, just use 
		Command c = Dialog.show("Enter the Flag Number", myTF, cOk);
		if (c == cOk) {
	
//			System.out.println("getText " + myTF.getText());
//			if ( myTF.getText() == "") {
			if (myTF.getText() != "" && Character.isDigit(myTF.getText().charAt(0))) {
				gw.nextFlagReached(Integer.parseInt(myTF.getText()));

			} else {
				System.out.println("Not a valid number");				
			}
		} 
	}

}
