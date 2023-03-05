package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.TextHolder;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;
import java.util.ArrayList;

public class Game extends Form implements Runnable {
	
	private GameWorld gw;
	private MapView mv;      // new in A2 
	private ScoreView sv;    // new in A2 
	private AccelerateCommand ac = null;    // new in A2 
	private BrakeCommand bc = null;        // new in A2 
	private TurnLeftCommand tlc = null;    // new in A2 
	private TurnRightCommand trc = null;    // new in A2 
	private ExitCommand ec = null;    // new in A2 
	private AboutCommand abc = null;    // new in A2 
	private HelpCommand hc = null;    // new in A2 
	private SoundCommand sc = null;    // new in A2 
	private Toolbar myToolbar = null;    // new in A2 
	private Container bContainer  = null;    // new in A2 
	private Container lContainer = null;     // new in A2 
	private Container rContainer = null;    // new in A2 

		
	private PlayPauseCommand ppc = null;      //New In A3
	private PositionCommand pc = null;      //New In A3
	MyButton playPauseButton = null;        //New in A3
	MyButton positionButton = null;        //New in A3
	MyButton accelrateButton = null;       
	MyButton turnLeftButton = null;
	MyButton brakeButton = null;
	MyButton turnRightButton = null;

	/*
	 *  New in A3
	 */
	UITimer t = null;
	//UITimer t = new UITimer(this);
	private boolean running = true;
	ArrayList<GameObject> alreadyCollided = null;
	boolean position = false;
	
	
	public Game(){
		
		  this.setLayout(new BorderLayout());
		  alreadyCollided = null;new ArrayList();
		  gw = new GameWorld(this);    // create “Observable” GameWorld 
		  ac = new AccelerateCommand("Accelerate", gw);  //Command for acccelrate
		  bc = new BrakeCommand("Brake", gw);
	      tlc = new TurnLeftCommand("Left", gw);
	      trc = new TurnRightCommand("Right", gw);
	      ec = new ExitCommand("Exit", gw);
	      abc = new AboutCommand("About", gw);
	      hc = new HelpCommand("Help", gw);
	      sc = new SoundCommand("Sound", gw);

	      //new in A3
	      ppc = new PlayPauseCommand("Play/Pause", this);
	      pc = new PositionCommand("Position",gw);
	      gw.init();      // initialize world 
	      
		  mv = new MapView(gw);      // create an “Observer” for the map 
		  sv = new ScoreView(gw);    // create an “Observer” for the game/ant state data 
		  gw.addObserver(sv);  // register the score observer
		  gw.addObserver(mv);  // register the map observer 
		// code here to create Command objects for each command, 
		// add commands to side menu and title bar area, bind commands to keys, create 
		// control containers for the buttons, add buttons to the control containers,  
		// add commands to the buttons, and add control containers, MapView, and  
		// ScoreView to the form 
		  this.add(BorderLayout.NORTH,sv);
		  this.add(BorderLayout.CENTER,mv);
		  
		  //System.out.println("Map height: " + mv.getHeight() + "\n Map width: " + mv.getWidth());

		  this.show(); 
		  
		  System.out.println("Map height: " + mv.getHeight() + "\n Map width: " + mv.getWidth());
		  
		  gw.setHeight(mv.getHeight());
		  gw.setWidth(mv.getWidth());

		toolbar();
		bottomContainer();
		leftContainer();
		rightContainer();
		
		addKeyListener('a', ac);    //Acclerate Command
		addKeyListener('b', bc);    //Brake Command
		addKeyListener('l', tlc);   //LeftTurn Command
		addKeyListener('r', trc);   //RightTurn Command
		addKeyListener('p',ppc);    //PlayPauseCommand
		
		/////////New in A3
		t = new UITimer(this);
		t.schedule(1000, true, this);
		alreadyCollided = new ArrayList();
	}

	public boolean getRunning() {
		return this.running;
	}

	void playGame() {
		if(t == null) {
			System.out.println("\n\nT is not initialized\n\n");
		} else {
			t.schedule(1000, true, this);
			running = true;
		}	
	}
	
	void pauseGame() {
		if(t == null) {
			System.out.println("\n\nT is not initialized\n\n");
		} else {		
		t.cancel();
		running = false;
		}
	}
	

	
	private void toolbar() {
		myToolbar = new Toolbar();
		setToolbar(myToolbar);
		
		//add a string title to the toolBar
		myToolbar.setTitle("WalkIt Game");

		CheckBox checkSideMenuComponent = new CheckBox("Sound");
		//set the style of the check box
		checkSideMenuComponent.getAllStyles().setBgTransparency(255);
		checkSideMenuComponent.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		checkSideMenuComponent.setText("OFF");
		checkSideMenuComponent.setCommand(sc);

		myToolbar.addCommandToSideMenu(ac); 	 	//accelrate Command
		myToolbar.addCommandToSideMenu(ec); 	 	//exit Command
		myToolbar.addCommandToSideMenu(abc);    	//aboutCommand
		//add the CheckBox component as a side menu item
		myToolbar.addComponentToSideMenu(checkSideMenuComponent);     //Sound Command
		myToolbar.closeSideMenu();
		//Adding help to the right side of the toolbar
		myToolbar.addCommandToRightBar(hc);
		
		myToolbar.closeSideMenu();
	}
	
	public Toolbar getToolbar() {
		return myToolbar;
		
	}
	
	private void bottomContainer() {

		bContainer = new Container(new FlowLayout(Component.CENTER));
		
		playPauseButton = new MyButton("Play");
		positionButton = new MyButton("Position");
		
		bContainer.add(positionButton);
		
//		if (running) {
//			position.setEnabled(false);
//		} else {
//			position.setEnabled(true);
//		}
		
//		position.setEnabled(false);
		bContainer.add(playPauseButton);


		playPauseButton.setCommand(ppc);
		positionButton.setCommand(pc);
		
		//Setting Initial text of the button
		playPauseButton.setText("Pause");
		
		//Setting positionCommand to disabled state
		pc.setEnabled(false);
		
		//Setting Positon buttin to disabled state
		positionButton.setEnabled(false);
		
		revalidate();

		//Setting the Border Color
		bContainer.getAllStyles().setBorder(Border.createLineBorder(4,
		ColorUtil.BLACK));
		this.add(BorderLayout.SOUTH,bContainer);
		this.show();
	}
	
	
	/*
	 * Method to update the buttons and commands according to status of 
	 * variable running
	 */
	void updateButtons() {
		
		if(running) {
			playPauseButton.setText("Pause");
			positionButton.setEnabled(false);
			brakeButton.setEnabled(true);
			turnRightButton.setEnabled(true);
			accelrateButton.setEnabled(true);
			turnLeftButton.setEnabled(true);
			
			pc.setEnabled(false);
			bc.setEnabled(true);
			ac.setEnabled(true);
			tlc.setEnabled(true);
			trc.setEnabled(true);
			addKeyListener('a', ac);    //Acclerate Command
			addKeyListener('b', bc);    //Brake Command
			addKeyListener('l', tlc);   //LeftTurn Command
			addKeyListener('r', trc);   //RightTurn Command
			addKeyListener('p',ppc);    //PlayPauseCommand
			myToolbar.addCommandToSideMenu(ac);
		} else {
			playPauseButton.setText("Play");
			positionButton.setEnabled(true);
			brakeButton.setEnabled(false);
			turnRightButton.setEnabled(false);
			accelrateButton.setEnabled(false);
			turnLeftButton.setEnabled(false);
			removeKeyListener('a',ac);
			removeKeyListener('b', bc);    //Brake Command
			removeKeyListener('l', tlc);   //LeftTurn Command
			removeKeyListener('r', trc);   //RightTurn Command
			removeKeyListener('p',ppc);    //PlayPauseCommand
			ac.setEnabled(false);
			myToolbar.removeCommand(ac);
			
		}
		revalidate();
	}

	
	private void leftContainer() {
		
		//Add some gap to the before the first button in this container
		lContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		lContainer.getAllStyles().setPadding(Component.TOP, 100);
		
		 accelrateButton = new MyButton("acclerate");
		 turnLeftButton = new MyButton("Left");

		lContainer.add(accelrateButton);
		lContainer.add(turnLeftButton);
		
		// Adding command to button
		accelrateButton.setCommand(ac);
		turnLeftButton.setCommand(tlc);
		
		//Setting the Border Color
		lContainer.getAllStyles().setBorder(Border.createLineBorder(1,
		ColorUtil.BLACK));
		this.add(BorderLayout.WEST,lContainer);
		this.show();
	}
	
	
	private void rightContainer() {
		
		//Add some gap to the before the first button in this container
		rContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		
		rContainer.getAllStyles().setPadding(Component.TOP, 100);
		
		brakeButton = new MyButton("Brake");
		turnRightButton = new MyButton("Right");

		rContainer.add(brakeButton);
		rContainer.add(turnRightButton);
		
		//Adding command to button
		brakeButton.setCommand(bc);
		turnRightButton.setCommand(trc);

		//Setting the Border Color
		rContainer.getAllStyles().setBorder(Border.createLineBorder(1,
		ColorUtil.BLACK));
		this.add(BorderLayout.EAST,rContainer);
		this.show();
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		System.out.println("\n\n\n run method is being called \n\n\n");
		gw.clockTick();
		
		System.out.println("Running: " + running);

//		
//		System.out.println("Map position in Game\nX: " + mv.getX() + "nY: " + mv.getY());
		
		//Code for handling collision
		Ant theAnt = gw.getAnt();
		
		IIterator iter = gw.getIterator();
		
		while(iter.hasNext()) {
			
			
			ICollider otherObject = (ICollider) iter.getNext();
			
			Point pnt = new Point(mv.getX(), mv.getY());
			
			boolean collided = theAnt.colliderWith((GameObject) otherObject,
					new Point(mv.getX(), mv.getY()));
			
			
			////Handling repeated collisions with same objects
			
			if(collided) {
				if (alreadyCollided.contains(otherObject)) {
					//do nothing
				} else {
				theAnt.handleCollision((GameObject) otherObject);
				alreadyCollided.add((GameObject) otherObject);
				}
				
			} else {
				alreadyCollided.remove(otherObject);
			}
			
//			if(theAnt.colliderWith((GameObject) otherObject,new Point(mv.getX(), mv.getY()))) {
//				theAnt.handleCollision((GameObject) otherObject);
//			}	
		}
		revalidate();
	}
	
}