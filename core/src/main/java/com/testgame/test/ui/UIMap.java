package com.testgame.test.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.testgame.test.Map;
import com.testgame.test.Wall;
import com.testgame.test.entities.Player;
import com.testgame.test.utils.Constants;

public class UIMap {
    private float x, y;
    private Map map;
    private Player player;

    private final int TILE_SIZE = 15;
    private final float scale = TILE_SIZE / (float)Constants.TILE_SIZE;

    public UIMap(Map map, float x, float y) {
        this(map, null, x, y);
    }

    public UIMap(Map map, Player player, float x, float y) {
        this.map = map;
        this.player = player;
        this.x = x;
        this.y = y;
    }

    public void draw(ShapeRenderer ctx) {
        float x = this.x, y = this.y - TILE_SIZE;
        for (int i = 0; i < map.cols * map.rows; i++) {
            if (i % map.rows == 0) {
                y += TILE_SIZE;
                x = 0;
            }
            char tile = Constants.str_map[i/map.rows].charAt(i%map.rows);
            if (tile == '1' || tile == '2' || tile == '3') {
                ctx.setColor(Color.BLUE);
                ctx.rect(x, y, TILE_SIZE, TILE_SIZE);
            }
            x += TILE_SIZE;
        }
        if (player != null) {
            Vector2 player_pos = player.getPos();
            ctx.circle(this.x + player_pos.x*scale, this.y + player_pos.y*scale, 5);
        }
    }
}
