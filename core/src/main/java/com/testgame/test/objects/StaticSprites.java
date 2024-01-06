package com.testgame.test.objects;

import com.badlogic.gdx.graphics.Texture;

public enum StaticSprites {
    BARREL(new Texture("Doom-sprites/bar-sprite.png")),
    CANDLESTICK(new Texture("Doom-sprites/candlestick.png"));


    public final Texture texture;

    StaticSprites(Texture texture) {
        this.texture = texture;
    }

    public Sprite create() {
        return new Sprite()
            .buildTexture(texture);
    }
}
