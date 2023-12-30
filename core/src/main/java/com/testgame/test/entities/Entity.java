package com.testgame.test.entities;

import com.badlogic.gdx.math.Vector2;
import com.testgame.test.Wall;
import com.testgame.test.utils.Functions;

import java.util.ArrayList;

public class Entity {
    protected Vector2 pos = new Vector2();
    protected Vector2 velocity = new Vector2();

    protected float angle;
    protected float speed;

    public Entity() {

    }

    public void checkCollision(ArrayList<Wall> walls) {
        for (Wall wall : walls) {
            if (Functions.intersects(pos.x, pos.y, wall)) {

            }
        }
    }

    public float getAngle() {
        return angle;
    }

    public float getSpeed() {
        return speed;
    }

    public Vector2 getPos() {
        return pos;
    }
}
