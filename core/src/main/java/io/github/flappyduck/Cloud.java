package io.github.flappyduck;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Cloud {
    final private Texture texture;

    final private float width = 10;
    final private float height = 3.125f;

    private float x;
    private float y;

    final private float speed;

    public Cloud(Texture texture, float world_width, float world_height) {
        this.texture = texture;

        speed = (float)Math.random() * 4 + 1;
        y = world_height - (float)Math.random() * world_height * 0.8f - height;
        x = world_width;
    }

    public void moveCloud(float delta) {
        x -= speed * delta;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
    }

    public float getX() {
        return x;
    }

    public float getWidth() {
        return width;
    }
}
