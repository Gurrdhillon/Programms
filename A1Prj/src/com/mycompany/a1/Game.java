package com.mycompany.a1;

import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener; import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent; import java.lang.String;


public class Game extends Form {
	
	private GameWorld gw;
	private boolean exit = false;

	
	public Game(){
		
		gw = new GameWorld();
		gw.init();
		play();
	}
	
	public boolean getExit() {
		return exit;
	}
	

	private void play() { 
		
	 Label myLabel = new Label("Enter a Command:"); 
	 this.addComponent(myLabel); 
	 final TextField myTextField = new TextField(); 
	 this.addComponent(myTextField); 
	 this.show();
	 
	 myTextField.addActionListener(new ActionListener() { 
	 
	  public void actionPerformed(ActionEvent evt) { 
	     
	  String sCommand=myTextField.getText().toString(); 
	  myTextField.clear();
	  
	  
		  if(getExit()) {
			  
			  switch (sCommand.charAt(0)) { 
			  
			  case 'y':
				  gw.exit();
				  break;
				  
			  case 'n':
				  System.out.println(" You decided to not exit the game!!");
				  exit = false;
				  break;
			
			default:
					 System.out.println("Enter y or n");
				  
			  }  
			  
		  } else {
		  
			   switch (sCommand.charAt(0)) { 
	
			    case 'a': 
			     gw.accelerate();  
				 System.out.println("Acceleration applied.");
			     break; 
			    case 'b': 
				  gw.brake(); 
				 System.out.println("Breaks applied.");
				  break; 
			    case 'l': 
				  gw.turnLeft();  
				 System.out.println("5 degree left turn.");
				  break; 
				case 'r': 
				   gw.turnRight(); 
				System.out.println("5 degree right turn.");
				   break;
			    case '1':
			        gw.nextFlagReached(1);
			   	 System.out.println("Flag 1 reached.");
			        break;
			    case '2':
			        gw.nextFlagReached(2);
				 System.out.println("Flag 2 reached.");	        
			        break;
			    case '3':
			        gw.nextFlagReached(3);
				 System.out.println("Flag 3 reached.");	        
			        break;
			    case '4':
			        gw.nextFlagReached(4);
			   	 System.out.println("Flag 4 reached.");
			        break;
			    case 'f': 
				  gw.foodStationLocated();
				System.out.println("Next food station located.");		  
				  break; 
			    case 'g': 
				  gw.spiderCollision(); 
				  
				System.out.println("Spider crashed into ant.");		  
				  break; 
				case 't': 
				   gw.clockTick();  
				System.out.println("Clock ticked.");			   
				   break;		   
				case 'd':
					gw.display();
				System.out.println("Display printed.");			
					break;
				case 'm':
					gw.map();
				System.out.println("Map printed");				
					break;
				case 'x':
					//get user input if  they enter y, else don't exit
					exit = true;
				     break;
				 default:
					 System.out.println("Please eneter a valid command");
		    	 
	   				} //switch 
		  		} //else
	  		} //actionPerformed 
	 	} //new ActionListener() 
	); //addActionListener 
 } //play 
}
