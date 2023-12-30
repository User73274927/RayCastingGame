package com.testgame.test.utils;

import com.badlogic.gdx.graphics.Color;

import java.util.HashMap;

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
        "3----------------------------------3",
        "1--3--------2------------------3---1",
        "1--2--------3222223-3223-------2---1",
        "3--2--------2--------------32223---3",
        "1--2--------2----3---------2-------1",
        "1--32223----3----2---------2---3---1",
        "3----------------22223-32222---2---3",
        "1----------------3---------3---2---1",
        "1------------------------------2---1",
        "33113113113113113--------------2---3",
        "1---------------1---32223--32223---1",
        "1----------------------------------1",
        "3---------------1------------------3",
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
    public static final float RAD45 = 0.78f;
    public static final float RAD90 = 1.57f;

    public static final int FPS = 30;
    public static final int TILE_SIZE = 30;
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 480;

    //Ray casting
    public static final float FOV = (float) Math.toRadians(60);
    public static final int RAY_MAX_LENGTH = 500;
    public static final int RAYS_NUM = SCREEN_WIDTH;
    public static final float SCALE = (float) SCREEN_WIDTH / RAYS_NUM;

    public static final Color BACKGROUND_COLOR = Color.BLACK;

}
