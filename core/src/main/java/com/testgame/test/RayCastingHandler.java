package com.testgame.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.testgame.test.entities.Player;
import com.testgame.test.objects.Projected;
import com.testgame.test.utils.Functions;

import static com.testgame.test.utils.Constants.*;


public class RayCastingHandler {
    private RayCast[] ray_buffer = new RayCast[RAYS_NUM];
    private RayCast center_ray;
    private Map map;
    private Player observer;

    public RayCastingHandler(Map map, Player observer) {
        this.map = map;
        this.observer = observer;
    }

    public void update() {
        rayCasting();
    }

    public void draw(Batch batch, ShapeRenderer ctx) {
        drawFloor(ctx);
        drawTextures(batch);
    }

    public void rayCasting() {
        float ray_angle = observer.getAngle() - FOV/2;
        float ray_offset = FOV / (RAYS_NUM - 1);
        Vector2 pos = observer.getPos();
        center_ray = calculateRay(pos, observer.getAngle());

        for (int i = 0; i < RAYS_NUM; i++) {
            ray_buffer[i] = calculateRay(pos, ray_angle);
            ray_angle += ray_offset;
        }
    }

    @Deprecated
    public void drawProjection(ShapeRenderer ctx) {
        ctx.begin(ShapeRenderer.ShapeType.Filled);

        for (int i = 0; i < ray_buffer.length; i++) {
            RayCast r = ray_buffer[i];
            float wall_projection_height = calculateProjectionHeight(r, 1.5f*TILE_SIZE);
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

    @Deprecated
    public void drawTextures(Batch batch) {
        batch.begin();
        batch.enableBlending();
        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ZERO);
        int i = 0;
        for (RayCast r : ray_buffer) {
            float dst = Math.min(r.h_dst(), r.v_dst());
            float offset = ((r.h_dst() < r.v_dst()) ? r.x() : r.y()) % TILE_SIZE;
            //offset = ((r.angle() < 0) ? -TILE_SIZE : 0) + offset;

            if (dst != RAY_MAX_LENGTH && r.object() != null) {
                Color shadow = Functions.rgbaConvert(255, 255, 255, 255 - (int)(255 * (dst / RAY_MAX_LENGTH) + 75));
                TextureRegion texture = getTextureProjection(r);
                Rectangle projection = getProjection(r, i);

                batch.setColor(shadow);
                batch.draw(texture, projection.x, projection.y, projection.width, projection.height);
            }
            i++;
        }
        batch.end();
    }

    private float calculateProjectionHeight(RayCast r, float height) {
        float dst = Math.min(r.h_dst(), r.v_dst());
        //disable fish effect
        dst *= (float) Math.cos(observer.getAngle() - r.angle());
        return Functions.calculateProjectionHeight(dst, height);
    }

    @Deprecated
    public void drawFloor(ShapeRenderer ctx) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        ctx.begin(ShapeRenderer.ShapeType.Filled);
        for (float py = 0; py < SCREEN_HEIGHT/2f; py += SCALE) {
            Color shadow = getShadow(py);
            ctx.setColor(shadow);
            ctx.rect(0, py, SCREEN_WIDTH, SCALE);
        }
        ctx.end();
    }

    private RayCast calculateRay(Vector2 pos, float angle) {
        float v_dst = 0, h_dst = 0;
        float px, py;
        Projected object = null;

        int dir_by_x = (Math.cos(angle) >= 0) ? 1 : -1;
        px = (int)( (dir_by_x == 1) ?
            Math.ceil(pos.x / TILE_SIZE) :
            Math.floor(pos.x / TILE_SIZE)
        ) * TILE_SIZE;

        //interactions with vertical
        VLoop:
        while (v_dst < RAY_MAX_LENGTH) {
            if (Math.cos(angle) == 0.0) {
                v_dst = RAY_MAX_LENGTH;
                break;
            }
            v_dst = (float) ((px - pos.x) / Math.cos(angle));
            py = (float) (pos.y + v_dst * Math.sin(angle));

            for (Wall wall : map.getWalls()) {
                if (Functions.intersects(px, py, wall)) {
                    object = wall;
                    break VLoop;
                }
            }
            px += TILE_SIZE * dir_by_x;
        }

        //interactions with horizontal
        int dir_by_y = (Math.sin(angle) >= 0) ? 1 : -1;
        py = (int)( (dir_by_y == 1) ?
            Math.ceil(pos.y / TILE_SIZE) :
            Math.floor(pos.y / TILE_SIZE)
        ) * TILE_SIZE;

        HLoop:
        while (h_dst < RAY_MAX_LENGTH) {
            if (Math.sin(angle) == 0.0) {
                h_dst = RAY_MAX_LENGTH;
                break;
            }
            h_dst = (float) ((py - pos.y) / Math.sin(angle));
            px = (float) (pos.x + h_dst * Math.cos(angle));

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

        px = (float) (pos.x + h_dst * Math.cos(angle));
        py = (float) (pos.y + v_dst * Math.sin(angle));

        return new RayCast(px, py, h_dst, v_dst, angle, object);
    }

    public TextureRegion getTextureProjection(RayCast r) {
        float dst = Math.min(r.h_dst(), r.v_dst());
        float offset = ((r.h_dst() < r.v_dst()) ? r.x() : r.y()) % TILE_SIZE;
        if (dst >= RAY_MAX_LENGTH && r.object() == null) return null;

        TextureRegion proj_texture = new TextureRegion(r.object().getTexture());
        float texture_scale = (float) proj_texture.getTexture().getWidth() / TILE_SIZE;
        proj_texture.setRegion((int)(offset*texture_scale), 0, (int)(texture_scale), proj_texture.getTexture().getHeight());
        return proj_texture;
    }

    public Rectangle getProjection(RayCast r, int i) {
        float projection_height = calculateProjectionHeight(r, 1.5f*TILE_SIZE);
        float dst = Math.min(r.h_dst(), r.v_dst());
        if (dst > RAY_MAX_LENGTH) return new Rectangle();
        return new Rectangle(i*SCALE, (float) SCREEN_HEIGHT/2 - projection_height/2, SCALE, projection_height);
    }

    public Color getShadow(float dst) {
        int opacity = (int) (255 * Math.min(1, dst / RAY_MAX_LENGTH) + 75);
        return Functions.rgbaConvert(0, 0, 0, opacity);
    }

    public RayCast[] getRayBuffer() {
        return ray_buffer;
    }

}
