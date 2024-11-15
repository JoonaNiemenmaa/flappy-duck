package io.github.flappyduck;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Background {

    final private Texture background;
    final private Texture cloud;

    final private Array<Cloud> clouds;

    final private float time_between_clouds = 5;
    private float cloud_timer;

    final private FitViewport viewport;

    public Background(FitViewport viewport) {
        background = new Texture("background.png");
        cloud = new Texture("cloud.png");
        cloud_timer = time_between_clouds;
        clouds = new Array<>();
        this.viewport = viewport;
    }

    public void update(float delta) {
        for (Cloud cloud : clouds) {
            cloud.moveCloud(delta);
            if (cloud.getX() + cloud.getWidth() < 0) {
                clouds.removeValue(cloud, true);
            }
        }
        cloud_timer -= delta;
        if (cloud_timer < 0) {
            clouds.add(new Cloud(cloud, viewport.getWorldWidth(), viewport.getWorldHeight()));
            cloud_timer = time_between_clouds;
        }
    }

    public void draw(SpriteBatch batch) {
        batch.draw(background, 0, 0, 80, 50);
        for (Cloud cloud : clouds) {
            cloud.draw(batch);
        }
    }



}
