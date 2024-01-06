package com.testgame.test;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.testgame.test.entities.Player;
import com.testgame.test.objects.Sprite;
import com.testgame.test.utils.Functions;

import java.util.ArrayList;

import static com.testgame.test.utils.Constants.RAY_MAX_LENGTH;

public class ObjectsRenderer {
    private ArrayList<ObjectToRender> zBuffer = new ArrayList<>();
    private RayCastingHandler ray_casting;
    private SpriteHandler sprites;

    private Player observer;
    private Map map;

    public ObjectsRenderer(Map map, Player observer) {
        this.ray_casting = new RayCastingHandler(map, observer);
        this.sprites = new SpriteHandler(observer);
        this.observer = observer;
        this.map = map;
        sprites.addSprites(map.getStaticSprites());
    }

    public void update() {
        zBuffer.clear();
        ray_casting.rayCasting();

        int i = 0;
        for (RayCast ray : ray_casting.getRayBuffer()) {
            TextureRegion proj_texture = ray_casting.getTextureProjection(ray);
            Rectangle scale = ray_casting.getProjection(ray, i);
            if (proj_texture != null) {
                zBuffer.add(new ObjectToRender(proj_texture, scale, Math.min(ray.h_dst(), ray.v_dst())));
            }
            i++;
        }

        for (Sprite sprite : sprites.getSprites()) {
            sprite.updateProjection(observer);
            TextureRegion region = new TextureRegion(sprite.getTexture());
            zBuffer.add(new ObjectToRender(region, sprite.getScale(), sprite.getDist()));
        }

        zBuffer.sort(((o1, o2) -> {
            if (o1.dist() == o2.dist()) return 0;
            return (o1.dist() > o2.dist()) ? -1 : 1;
        }));
    }

    public void draw(Batch batch) {
        batch.begin();
//        batch.enableBlending();
//        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ZERO);
        for (ObjectToRender object : zBuffer) {
            Color shadow = Functions.rgbaConvert(255, 255, 255, 255 - (int)(255 * (object.dist() / RAY_MAX_LENGTH) + 75));
            Rectangle scale = object.scale();
            //batch.setColor(shadow);
            batch.draw(object.proj_texture(), scale.x, scale.y, scale.width, scale.height);
        }
        batch.end();
    }

}
