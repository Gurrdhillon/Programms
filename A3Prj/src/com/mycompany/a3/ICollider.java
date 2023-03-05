package com.mycompany.a3;

import com.codename1.charts.models.Point;

public interface ICollider {
	
	boolean colliderWith(GameObject otherObject, Point pnt);
	
	void handleCollision(GameObject otherObject);

}
