package com.testgame.test;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.testgame.test.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class Map {
    final int rows = Constants.str_map[0].length();
    final int cols = Constants.str_map.length;
    final int cell_size = Constants.TILE_SIZE;
    private ArrayList<Wall> walls;

    public Texture wall1_texture = new Texture("wall1.png");
    public Texture wall2_texture = new Texture("wall2.png");
    public Texture wall3_texture = new Texture("wall3.png");

    public Map() {
        this.walls = createStrMap();
    }

    private ArrayList<Wall> createMap() {
        ArrayList<Wall> walls = new ArrayList<>();
        int x = 0, y = -cell_size;

        for (int i = 0; i < rows * cols; i++) {
            if (i % rows == 0) {
                y += cell_size;
                x = 0;
            }
            if (Constants.map[i/ rows][i% rows] == 1) {
                walls.add(new Wall(x, y));
            }
            x += cell_size;
        }
        return walls;
    }

    private ArrayList<Wall> createStrMap() {
        ArrayList<Wall> walls = new ArrayList<>();
        int x = 0, y = -cell_size;

        for (int i = 0; i < rows * cols; i++) {
            if (i % rows == 0) {
                y += cell_size;
                x = 0;
            }
            char wall_type = Constants.str_map[i/ rows].charAt(i% rows);
            Texture texture = null;

            if (wall_type == '1') {
                texture = wall1_texture;
            }
            else if (wall_type == '2') {
                texture = wall2_texture;
            }
            else if (wall_type == '3') {
                texture = wall3_texture;
            }

            if (texture != null) {
                Wall wall = new Wall(x, y);
                wall.texture = texture;
                walls.add(wall);
            }
            x += cell_size;
        }
        return walls;
    }

    public void draw(ShapeRenderer ctx) {
        ctx.setColor(Color.BLUE);
        for (Wall wall : walls) {
            ctx.rect(wall.x(), wall.y(), cell_size, cell_size);
        }
    }

    public List<Wall> getWalls() {
        return walls;
    }

}
