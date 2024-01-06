package com.testgame.test;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;


public record ObjectToRender(TextureRegion proj_texture, Rectangle scale, float dist) {}
