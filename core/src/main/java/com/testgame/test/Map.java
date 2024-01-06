package com.testgame.test;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.testgame.test.entities.Player;
import com.testgame.test.objects.Projected;
import com.testgame.test.objects.Sprite;
import com.testgame.test.utils.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static com.testgame.test.utils.Constants.TILE_SIZE;

public class Map {
    public final int rows = Constants.str_map[0].length();
    public final int cols = Constants.str_map.length;

    private final HashMap<Character, TextureType> texture_dict = new HashMap<>();
    private ArrayList<Sprite> static_sprites = new ArrayList<>();
    private ArrayList<Wall> walls;

    public Texture wall1_texture = new Texture("wall1.png");
    public Texture wall2_texture = new Texture("wall2.png");
    public Texture wall3_texture = new Texture("wall3.png");

    public Map() {
        initTextureDict();
        this.walls = createStrMap();
    }

    private void initTextureDict() {
        texture_dict.put('1', new TextureType(wall1_texture, ObjectType.WALL));
        texture_dict.put('2', new TextureType(wall2_texture, ObjectType.WALL));
        texture_dict.put('3', new TextureType(wall3_texture, ObjectType.WALL));
        texture_dict.put('i', new TextureType(new Texture("Doom-sprites/candlestick.png"), ObjectType.SPRITE));
        texture_dict.put('b', new TextureType(new Texture("Doom-sprites/bar-sprite.png"), ObjectType.SPRITE));
    }

    private ArrayList<Wall> createMap() {
        ArrayList<Wall> walls = new ArrayList<>();
        int x = 0, y = -TILE_SIZE;

        for (int i = 0; i < rows * cols; i++) {
            if (i % rows == 0) {
                y += TILE_SIZE;
                x = 0;
            }
            if (Constants.map[i/rows][i%rows] == 1) {
                walls.add(new Wall(x, y));
            }
            x += TILE_SIZE;
        }
        return walls;
    }

    private ArrayList<Wall> createStrMap() {
        ArrayList<Wall> walls = new ArrayList<>();
        int x = 0, y = -TILE_SIZE;

        for (int i = 0; i < rows * cols; i++) {
            if (i % rows == 0) {
                y += TILE_SIZE;
                x = 0;
            }
            char wall_type = Constants.str_map[i/rows].charAt(i%rows);
            float height = (float) (1.5* TILE_SIZE);
            TextureType texture_type = texture_dict.getOrDefault(wall_type, null);

            if (texture_type != null) {
                if (texture_type.type() == ObjectType.WALL) {
                    Wall wall = new Wall(x, y);
                    wall.height = height;
                    wall.texture = texture_type.texture();
                    walls.add(wall);
                }
                else if (texture_type.type() == ObjectType.SPRITE) {
                    Sprite sprite = new Sprite()
                        .buildTexture(texture_type.texture())
                        .buildPos(x + (float) TILE_SIZE /2, y + (float) TILE_SIZE /2);
                    if (wall_type == 'b') {
                        sprite.buildHeight(20);
                    }
                    static_sprites.add(sprite);
                }
            }
            x += TILE_SIZE;
        }
        return walls;
    }

    public void draw(ShapeRenderer ctx) {
        ctx.setColor(Color.BLUE);
        for (Wall wall : walls) {
            ctx.rect(wall.x(), wall.y(), TILE_SIZE, TILE_SIZE);
        }
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public List<Sprite> getStaticSprites() {
        return static_sprites;
    }

}

record TextureType(Texture texture, ObjectType type) {}

enum ObjectType { WALL, SPRITE, ANIMATED_SPRITE }
