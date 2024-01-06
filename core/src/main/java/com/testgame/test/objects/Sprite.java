package com.testgame.test.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.testgame.test.Wall;
import com.testgame.test.entities.Player;
import com.testgame.test.utils.Constants;
import com.testgame.test.utils.Functions;

public class Sprite implements Projected {
    protected Texture texture;

    private float projection_height;
    private float projection_width;

    private float projection_x;
    private float projection_y;

    private float width;
    private float height = 45;
    private float x;
    private float y;

    private float dist;

    public Sprite() {
        this(0f, 0f);
    }

    public Sprite(float x, float y) {
        this.texture = new Texture("Doom-sprites/HEADA1.png");
        this.x = x;
        this.y = y;
    }

    public void updateProjection(Player observer) {
        Vector2 pos = observer.getPos();
        float dx = x - pos.x;
        float dy = y - pos.y;
        dist = (float) Math.sqrt(dx*dx + dy*dy);
        float theta = (float) Math.atan2(dy, dx);
        float delta = getDelta(observer, theta);

        projection_height = Functions.calculateProjectionHeight(dist, height);
        projection_width = projection_height * ((float) texture.getWidth() / getTexture().getHeight());

        float offset_x = (delta / Constants.DELTA_RAY_ANGLE * Constants.SCALE);
        float offset_y = Functions.calculateProjectionHeight(dist, Constants.WALL_HEIGHT - height)/2f;
        projection_x = Constants.SCREEN_WIDTH/2f - projection_width/2f + offset_x;
        projection_y = Constants.SCREEN_HEIGHT/2f - projection_height/2f - offset_y;
    }

    private float getDelta(Player observer, float theta) {
        float delta =  theta - observer.getAngle();
        if (delta > Math.PI) {
            delta -= (float) Math.PI*2;
        }
        else if (delta < -Math.PI) {
            delta += (float) Math.PI*2;
        }
        return delta;
    }

    public void drawProjection(Batch batch, float parentAlpha) {
        batch.draw(texture, projection_x, projection_y, projection_width, projection_height);
    }

    @Override
    public Texture getTexture() {
        return texture;
    }

    @Override
    public float x() {
        return x;
    }

    @Override
    public float y() {
        return y;
    }

    @Override
    public float width() {
        return 0;
    }

    @Override
    public float height() {
        return height;
    }

    public Rectangle getScale() {
        return new Rectangle(projection_x, projection_y, projection_width, projection_height);
    }

    public float getDist() {
        return dist;
    }

    public Sprite buildTexture(Texture texture) {
        this.texture = texture;
        return this;
    }

    public Sprite buildPos(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Sprite buildHeight(float height) {
        this.height = height;
        return this;
    }
}
