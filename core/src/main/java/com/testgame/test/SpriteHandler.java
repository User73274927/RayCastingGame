package com.testgame.test;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.testgame.test.entities.Player;
import com.testgame.test.objects.Sprite;
import com.testgame.test.utils.Constants;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.testgame.test.utils.Constants.SCREEN_HEIGHT;
import static com.testgame.test.utils.Constants.SCREEN_WIDTH;

public class SpriteHandler {
    private ArrayList<Sprite> sprites = new ArrayList<>();
    private Player observer;

    public SpriteHandler(Player observer) {
        this.observer = observer;
        sprites.add(new Sprite(SCREEN_WIDTH/2f, SCREEN_HEIGHT/2f));
    }

    public List<Sprite> getSprites() {
        return sprites;
    }

    public void addSprites(List<Sprite> sprites) {
        this.sprites.addAll(sprites);
    }
}
