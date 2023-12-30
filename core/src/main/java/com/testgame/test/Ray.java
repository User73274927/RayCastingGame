package com.testgame.test;

import com.testgame.test.objects.Projected;

public record Ray(float x, float y, float h_dst, float v_dst, float angle, Projected object) {}
