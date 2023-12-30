package com.testgame.test;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.testgame.test.entities.Entity;
import com.testgame.test.objects.Projected;
import com.testgame.test.utils.Functions;

import static com.testgame.test.utils.Constants.*;


public class RayCastingRenderer {
    private Ray[] ray_buffer = new Ray[RAYS_NUM];
    private Map map;
    private Entity observer;


    public RayCastingRenderer(Map map, Entity observer) {
        this.map = map;
        this.observer = observer;
    }

    public void rayCasting() {
        float ray_angle = observer.getAngle() - FOV/2;
        Vector2 pos = observer.getPos();
        float ray_offset = FOV / (RAYS_NUM - 1);

        for (int i = 0; i < RAYS_NUM; i++) {
            float v_dst = 0, h_dst = 0;
            float px, py;
            Projected object = null;

            int dir_by_x = (Math.cos(ray_angle) >= 0) ? 1 : -1;
            px = (int)( (dir_by_x == 1) ?
                Math.ceil(pos.x / TILE_SIZE) :
                Math.floor(pos.x / TILE_SIZE)
            ) * TILE_SIZE;

            //vertical
            VLoop:
            while (v_dst < RAY_MAX_LENGTH) {
                if (Math.cos(ray_angle) == 0.0) {
                    v_dst = RAY_MAX_LENGTH;
                    break;
                }
                v_dst = (float) ((px - pos.x) / Math.cos(ray_angle));
                py = (float) (pos.y + v_dst * Math.sin(ray_angle));

                for (Wall wall : map.getWalls()) {
                    if (Functions.intersects(px, py, wall)) {
                        object = wall;
                        break VLoop;
                    }
                }
                px += TILE_SIZE * dir_by_x;
            }

            //horizontal
            int dir_by_y = (Math.sin(ray_angle) >= 0) ? 1 : -1;
            py = (int)( (dir_by_y == 1) ?
                Math.ceil(pos.y / TILE_SIZE) :
                Math.floor(pos.y / TILE_SIZE)
            ) * TILE_SIZE;

            HLoop:
            while (h_dst < RAY_MAX_LENGTH) {
                if (Math.sin(ray_angle) == 0.0) {
                    h_dst = RAY_MAX_LENGTH;
                    break;
                }
                h_dst = (float) ((py - pos.y) / Math.sin(ray_angle));
                px = (float) (pos.x + h_dst * Math.cos(ray_angle));

                for (Wall wall : map.getWalls()) {
                    if (Functions.intersects(px, py, wall)) {
                        if (h_dst < v_dst) {
                            object = wall;
                        }
                        break HLoop;
                    }
                }
                py += TILE_SIZE * dir_by_y;
            }
            v_dst = (v_dst > RAY_MAX_LENGTH) ? RAY_MAX_LENGTH : v_dst;
            h_dst = (h_dst > RAY_MAX_LENGTH) ? RAY_MAX_LENGTH : h_dst;

            px = (float) (pos.x + h_dst * Math.cos(ray_angle));
            py = (float) (pos.y + v_dst * Math.sin(ray_angle));

            ray_buffer[i] = new Ray(px, py, h_dst, v_dst, ray_angle, object);
            ray_angle += ray_offset;
        }
    }

    public void draw(ShapeRenderer ctx) {
        ctx.begin(ShapeRenderer.ShapeType.Filled);

        for (int i = 0; i < ray_buffer.length; i++) {
            Ray r = ray_buffer[i];
            float wall_projection_height = calculateWallProjectionHeight(r);
            float dst = Math.min(r.h_dst(), r.v_dst());
            float ray_angle = r.angle();

            if (dst != RAY_MAX_LENGTH) {
                Color shadow = Functions.rgbaConvert(255, 255, 255, 255 - (int)(255 * (dst / RAY_MAX_LENGTH) + 50));

                ctx.setColor(shadow);
                ctx.rect((float) i*SCALE,
                    (float) SCREEN_HEIGHT/2 - wall_projection_height/2,
                    SCALE, wall_projection_height
                );
            }
        }
        ctx.end();
    }

    public void drawTextures(Batch batch) {
        batch.begin();
        batch.enableBlending();
        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ZERO);
        int i = 0;
        for (Ray r : ray_buffer) {
            float dst = Math.min(r.h_dst(), r.v_dst());
            float offset = ((r.h_dst() < r.v_dst()) ? r.x() : r.y()) % TILE_SIZE;
            //offset = ((r.angle() < 0) ? -TILE_SIZE : 0) + offset;
            float projection_height =  calculateWallProjectionHeight(r);

            if (dst != RAY_MAX_LENGTH && r.object() != null) {
                Color shadow = Functions.rgbaConvert(255, 255, 255, 255 - (int)(255 * (dst / RAY_MAX_LENGTH) + 50));
                TextureRegion texture = new TextureRegion(r.object().getTexture());
                float texture_scale = 256f / TILE_SIZE;
                texture.setRegion((int)(offset*texture_scale), 0, (int)(texture_scale), 256);

                batch.setColor(shadow);
                batch.draw(texture, (float) i*SCALE,
                    (float) SCREEN_HEIGHT/2 - projection_height/2,
                    SCALE, projection_height
                );
            }
            i++;
        }
        batch.end();
    }

    private float calculateWallProjectionHeight(Ray r) {
        float dst = Math.min(r.h_dst(), r.v_dst());
        float ray_angle = r.angle();
        //disable fish effect
        dst *= (float) Math.cos(observer.getAngle() - ray_angle);

        float projection_dst = RAYS_NUM / (float) (2 * Math.tan(FOV/2));
        float wall_projection_height = (1.5f*TILE_SIZE*SCALE * projection_dst) / dst;

        return wall_projection_height;
    }

}
