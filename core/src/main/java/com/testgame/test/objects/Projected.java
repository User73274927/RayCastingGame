package com.testgame.test.objects;

import com.badlogic.gdx.graphics.Texture;

public interface Projected {
    Texture getTexture();
    float x();
    float y();
    float width();
    float height();

    static Projected create(Texture texture, float x, float y, float width, float height) {
        return new Projected() {
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
                return width;
            }

            @Override
            public float height() {
                return height;
            }
        };
    }
}
