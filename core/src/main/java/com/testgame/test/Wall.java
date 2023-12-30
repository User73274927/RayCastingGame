package com.testgame.test;

import com.badlogic.gdx.graphics.Texture;
import com.testgame.test.objects.Projected;

public class Wall implements Projected {
    public Texture texture;
    private float x;
    private float y;

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
    public float width() {
        return 0;
    }

    @Override
    public float height() {
        return 0;
    }
}
