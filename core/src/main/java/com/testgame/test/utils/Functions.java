package com.testgame.test.utils;


import com.badlogic.gdx.graphics.Color;
import com.testgame.test.Wall;

import static com.testgame.test.utils.Constants.*;

public class Functions {
    public static boolean intersects(float x, float y, Wall wall) {
        return wall.x() <= x && x <= wall.x() + Constants.TILE_SIZE &&
                wall.y() <= y && y <= wall.y() + Constants.TILE_SIZE;
    }

    public static Color rgbaConvert(int r, int g, int b, int a) {
        return new Color(r / 255f, g / 255f, b / 255f, a / 255f);
    }

    public static float calculateProjectionHeight(float dst, float height) {
        return (height * SCALE * CAMERA_DIST) / dst;
    }

    public static float calculateProjectionWidth(float dst, float width) {
        return (width * CAMERA_DIST) / dst;
    }
}
