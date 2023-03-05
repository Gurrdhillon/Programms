package com.mycompany.a3;



import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;

public class ScoreView extends Container implements Observer {
	
	private GameWorld gw = null;
	private MyLabel soundValue = new MyLabel();
	private MyLabel timeValue = new MyLabel();
	private MyLabel liveLeftValue = new MyLabel();
	private MyLabel lastFlagReachedValue = new MyLabel(); 
	private MyLabel foodLevelValue = new MyLabel();
	private MyLabel healthLevelValue = new MyLabel();
	
	
	ScoreView(GameWorld gw) {
		
		this.gw = gw;
		
		this.setLayout(new FlowLayout(Component.CENTER));
		
		this.add(new MyLabel("Time  "));
		timeValue.setText(String.valueOf(gw.getClock()));
//		timeValue.setText("0          ");  //One potential Solution
		this.add(timeValue  );
		this.add(new MyLabel("LivesLeft  "));
		liveLeftValue.setText(String.valueOf(gw.getLife()));
		this.add(liveLeftValue);
		this.add(new MyLabel("LastFlagReached  "));
		lastFlagReachedValue.setText(String.valueOf(gw.getAntLastFlagReached()));
		this.add(lastFlagReachedValue);
		this.add(new MyLabel("Food Level  "));
//		foodLevelValue.setText(String.valueOf(gw.getAntFoodLevel()));
		foodLevelValue.setText("0"); 
		this.add(foodLevelValue);
		this.add(new MyLabel("Health Level  "));
		healthLevelValue.setText(String.valueOf(gw.getAntHealthLevel()));
		this.add(healthLevelValue);
		this.add(new MyLabel("Sound  "));
		soundValue.setText("OFF");
		this.add(soundValue);
		this.getAllStyles().setBorder(Border.createLineBorder(4,
		ColorUtil.BLACK));
		revalidate();
	}
	
	

	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		
		timeValue.setText(String.valueOf(gw.getClock()));;
		liveLeftValue.setText(String.valueOf(gw.getLife()));
		lastFlagReachedValue.setText(String.valueOf(gw.getAntLastFlagReached()));
		foodLevelValue.setText(String.valueOf(gw.getAntFoodLevel()));
		healthLevelValue.setText(String.valueOf(gw.getAntHealthLevel()));
		
		if(gw.getSound()) {
//			System.out.println(gw.getSound());
			soundValue.setText("ON");
			
		} else {
//			System.out.println(gw.getSound());
			soundValue.setText("OFF");
		}
		revalidate();

	}
	
}
