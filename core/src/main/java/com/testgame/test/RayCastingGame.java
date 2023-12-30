package com.testgame.test;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class RayCastingGame extends ApplicationAdapter {
    private GameScene game;
    private InputMultiplexer multiplexer;

    @Override
    public void create() {
        game = new GameScene();
        multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(game);
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void render() {
        game.act();
        game.draw();
    }

    @Override
    public void dispose() {
        game.dispose();
    }
}
