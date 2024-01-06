package com.testgame.test;

import com.badlogic.gdx.graphics.Texture;
import com.testgame.test.objects.Projected;

import static com.testgame.test.utils.Constants.TILE_SIZE;

public class Wall implements Projected {
    public Texture texture;
    private float x;
    private float y;
    public float height = (float) (1.5 * TILE_SIZE);

    public Wall(float x, float y) {
        this.x = x;
        this.y = y;
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
    public float height() {
        return height;
    }

    @Override
    public float width() {
        return 0;
    }

}
