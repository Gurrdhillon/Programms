package com.mycompany.a2;

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
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent; import java.lang.String;


public class Game extends Form  {
	
	private GameWorld gw;
	private MapView mv;      // new in A2 
	private ScoreView sv;    // new in A2 
	
	private AccelerateCommand ac = null;    // new in A2 
	private BrakeCommand bc = null;        // new in A2 
	private TurnLeftCommand tlc = null;    // new in A2 
	private TurnRightCommand trc = null;    // new in A2 
	private FoodStationLocatedCommand flc = null;    // new in A2 
	private ClockTickCommand ctc = null;    // new in A2 
	private ExitCommand ec = null;    // new in A2 
	private AboutCommand abc = null;    // new in A2 
	private SpiderCollisionCommand scc = null;    // new in A2 
	private HelpCommand hc = null;    // new in A2 
	private SoundCommand sc = null;    // new in A2 
	private FlagCollideCommand flcc = null;    // new in A2 
	private Toolbar myToolbar = null;    // new in A2 
	private Container bContainer  = null;    // new in A2 
	private Container lContainer = null;     // new in A2 
	private Container rContainer = null;    // new in A2 
	
	
	public Game(){

		 this.setLayout(new BorderLayout());
		
		  gw = new GameWorld(this);    // create “Observable” GameWorld 
		  ac = new AccelerateCommand("Accelerate", gw);  //Command for acccelrate
		  bc = new BrakeCommand("Brake", gw);
	      tlc = new TurnLeftCommand("Left", gw);
	      trc = new TurnRightCommand("Right", gw);
	      flc = new FoodStationLocatedCommand("Collide With FoodStations", gw);
	      ctc = new ClockTickCommand("Tick ", gw);
	      ec = new ExitCommand("Exit", gw);
	      abc = new AboutCommand("About", gw);
	      scc = new SpiderCollisionCommand("Collide With Spider",gw);
	      hc = new HelpCommand("Help", gw);
	      sc = new SoundCommand("Sound", gw);
	      flcc = new FlagCollideCommand("Collide With Flag", gw);
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

		  this.show(); 
		  
		// code here to query MapView’s width and height and set them as world’s  
		    // width and height  
		  
//		  gw.init();      // initialize world 
		

		toolbar();
		bottomContainer();
		leftContainer();
		rightContainer();
		
		
		//Attaching key 'c' to the accelerate command
		addKeyListener('c', ac);    //Acclerate Command
		addKeyListener('b', bc);    //Brake Command
		addKeyListener('l', tlc);   //LeftTurn Command
		addKeyListener('r', trc);   //RightTurn Command
		addKeyListener('f', flc);   //FoodStationLocated Command
		addKeyListener('g', scc);   //SpiderCollision Command
		addKeyListener('t', ctc);   //ClockTick Command
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
//		Container bContainer = new Container(new GridLayout(1,2));

		bContainer = new Container(new FlowLayout(Component.CENTER));
		
		MyButton flagCollisonButton = new MyButton("Collide with Flag");
		MyButton spiderCollisonButton = new MyButton("Collide with Spider");
		MyButton foodStationCollisonButton = new MyButton("Collide with Food Station");
		MyButton tickButton = new MyButton("Tick");

		bContainer.add(flagCollisonButton);
		bContainer.add(spiderCollisonButton);
		bContainer.add(foodStationCollisonButton);
		bContainer.add(tickButton);
		
		//Adding command to button
		flagCollisonButton.setCommand(flcc);   //create command for flag located, with pop up dialog box
		spiderCollisonButton.setCommand(scc);  //Spider Collision Command
		foodStationCollisonButton.setCommand(flc);  //food Station Located
		tickButton.setCommand(ctc);          //Clock Tick Command

		//Setting the Border Color
		bContainer.getAllStyles().setBorder(Border.createLineBorder(4,
		ColorUtil.BLACK));
		this.add(BorderLayout.SOUTH,bContainer);
		this.show();
	}
	
	private void leftContainer() {
		
		//Add some gap to the before the first button in this container

		lContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		
		lContainer.getAllStyles().setPadding(Component.TOP, 100);
		
		MyButton accelrateButton = new MyButton("acclerate");
		MyButton turnLeftButton = new MyButton("Left");

		lContainer.add(accelrateButton);
		lContainer.add(turnLeftButton);
		
		//Adding command to button
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
		
		MyButton brakeButton = new MyButton("Brake");
		MyButton turnRightButton = new MyButton("Right");

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

	
}
