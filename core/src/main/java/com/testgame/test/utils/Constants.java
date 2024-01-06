package com.testgame.test.utils;

import com.badlogic.gdx.graphics.Color;

import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static final byte[][] map = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1},
            {1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1},
            {1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    public static final String[] str_map = {
        "131131131131131131131131131131131131",
        "3i--------------------------------i3",
        "1--3b-------2i---------i-------3---1",
        "1--2--------3222223-3223------i2---1",
        "3--2--------2--------------32223---3",
        "1--2b-------2----3---------2-------1",
        "1--32223----3----2b-------b2---3---1",
        "3-----------i----22223-32222---2---3",
        "1----------------3---------3---2---1",
        "1------------------------------2---1",
        "33113113113113113---i--b------i2---3",
        "1b-b-b-b-b-b-b-b1---32223--32223---1",
        "1--------------------b------i------1",
        "3b-b-b-b-b-b-b-b1-----------------i3",
        "131131131131131131131131131131131131"
    };

    public static final char[] KEYS = { 'w', 'a', 's', 'd', 21, 22 }; // 21 - DC1 (right)     22 - DC2 (left)
    public static final HashMap<Character, Boolean> PRESSED_KEYS = new HashMap<>();
    public static final HashMap<Integer, Character> KEYCODE_TO_ASCII = new HashMap<>();
    public static final int KEY_TO_ASCII_OFFSET = 68;

    static {
        //PRESSED_KEYS init
        for (char key : KEYS) {
            PRESSED_KEYS.put(key, false);
        }
    }

    //angle
    public static final float RAD45 = (float) (Math.PI / 4);
    public static final float RAD90 = (float) (Math.PI / 2);
    public static final float RAD180 = (float) Math.PI;

    public static final int FPS = 30;
    public static final int TILE_SIZE = 30;
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 480;

    //Ray casting
    public static final float FOV = (float) Math.toRadians(55);
    public static final int RAY_MAX_LENGTH = 1000;
    public static final int RAYS_NUM = SCREEN_WIDTH / 2;
    public static final float DELTA_RAY_ANGLE = FOV / RAYS_NUM;
    public static final float SCALE = (float) SCREEN_WIDTH / RAYS_NUM;
    public static final float CAMERA_DIST = RAYS_NUM / (float) (2 * Math.tan(FOV/2));
    public static final float WALL_HEIGHT = 1.5f*TILE_SIZE;

    public static final Color BACKGROUND_COLOR = Color.BLACK;

}
