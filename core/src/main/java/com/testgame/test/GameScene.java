package com.testgame.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.testgame.test.entities.Player;
import com.testgame.test.ui.UIMap;
import com.testgame.test.utils.Constants;
import com.testgame.test.utils.Functions;

public class GameScene extends Stage {
    private final ShapeRenderer ctx = new ShapeRenderer();
    private final ObjectsRenderer renderer;
    private Batch batch;
    private Texture background;
    private Texture floor;

    public static final Map map = new Map();
    private Player player;

    private UIMap ui_map;

    public GameScene() {
        this.floor = new Texture("floor2.png");
        this.background = new Texture("background.bmp");
        this.player = new Player();
        this.renderer = new ObjectsRenderer(map, player);
        this.ui_map = new UIMap(map, player, 10, 10);
        this.batch = getBatch();
    }

    public void clear() {
        //ctx.setColor(Constants.BACKGROUND_COLOR);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        //Gdx.gl.glClearColor(0, 0, 0, 255);
////        Gdx.gl.glEnable(GL20.GL_BLEND);
////        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        batch.begin();
        //batch.disableBlending();
        batch.draw(background, 0, Constants.SCREEN_HEIGHT/2f, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT/2f);
        //batch.draw(floor, 0, 0,  Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT/2f);
        batch.end();
        ctx.begin(ShapeRenderer.ShapeType.Filled);
        ctx.setColor(Functions.rgbaConvert(54, 56, 56, 255));
        ctx.rect(0, 0,  Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT/2f);
        ctx.end();
    }

    public void draw() {
        clear();
        renderer.draw(batch);
        ctx.begin(ShapeRenderer.ShapeType.Line);
        ui_map.draw(ctx);
        ctx.end();
    }

    public void update() {
        renderer.update();
        player.update();
    }

    @Override
    public boolean keyDown(int code) {
        if (code > 22) {
            code += Constants.KEY_TO_ASCII_OFFSET;
        }

        for (char key : Constants.KEYS) {
            if (key == code) {
                Constants.PRESSED_KEYS.put(key, true);
            }
        }
        return false;
    }

    @Override
    public boolean keyUp(int code) {
        if (code > 22) {
            code += Constants.KEY_TO_ASCII_OFFSET;
        }

        for (char key : Constants.KEYS) {
            if (key == (char)code) {
                Constants.PRESSED_KEYS.put(key, false);
            }
        }
        return false;
    }

    @Override
    public void act() {
        super.act();
        update();
    }
}
