package io.github.flappyduck;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class Background {

    final private Texture background;
    final private Texture cloud;

    final private Array<Cloud> clouds;

    final private float time_between_clouds = 1;
    private float cloud_timer;

    public Background() {
        background = new Texture("background.png");
        cloud = new Texture("cloud.png");
        cloud_timer = time_between_clouds;
        clouds = new Array<>();
    }

    public void update(float delta) {
        for (Cloud cloud : clouds) {

        }
        cloud_timer -= delta;
        if (cloud_timer < 0) {
            clouds.add(new Cloud());
            cloud_timer = time_between_clouds;
        }
    }

    public void draw(SpriteBatch batch) {
        batch.draw(background, 0, 0, 80, 50);
    }



}
