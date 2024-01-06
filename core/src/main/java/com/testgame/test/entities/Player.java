package com.testgame.test.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import static com.testgame.test.utils.Constants.*;

public class Player extends Entity {
    private final float delta_angle = 0.03f;

    public Player() {
        super();
        this.pos = new Vector2(SCREEN_WIDTH/2f, SCREEN_HEIGHT/2f);
        this.speed = 2;
        this.angle = 0; //Radians
    }

    public void update() {
        rotate();
        move();

        pos = pos.add(velocity);
        velocity.setZero();

    }

    public void draw(ShapeRenderer ctx) {
        ctx.setColor(Color.YELLOW);
        ctx.circle(pos.x, pos.y, 5);
        ctx.setColor(Color.BLUE);
        ctx.line(pos.x, pos.y,
                (float) (pos.x+ RAY_MAX_LENGTH *Math.cos(angle)),
                (float) (pos.y+ RAY_MAX_LENGTH *Math.sin(angle))
        );
    }

    private void rotate() {
        if (PRESSED_KEYS.get((char)22)) {
            angle = (float)((angle + delta_angle) % (2*Math.PI));
        }
        if (PRESSED_KEYS.get((char)21)) {
            angle = (float)((angle - delta_angle) % (2*Math.PI));
        }
        if (angle > 0 && angle > Math.PI) {
            angle -= Math.PI*2;
        }
        else if (angle < 0 && angle < -Math.PI) {
            angle += Math.PI*2;
        }
    }

    private void move() {
        double dx = 0, dy = 0;
        if (PRESSED_KEYS.get('w')) {
            dx = speed * Math.cos(angle);
            dy = speed * Math.sin(angle);
        }
        if (PRESSED_KEYS.get('s')) {
            dx = -speed * Math.cos(angle);
            dy = -speed * Math.sin(angle);
        }
        if (PRESSED_KEYS.get('a')) {
            dx = -speed * Math.cos(RAD90 + angle);
            dy = -speed * Math.sin(RAD90 + angle);
        }
        if (PRESSED_KEYS.get('d')) {
            dx = speed * Math.cos(RAD90 + angle);
            dy = speed * Math.sin(RAD90 + angle);
        }

        velocity.set((float) dx, (float) dy);
    }

}
