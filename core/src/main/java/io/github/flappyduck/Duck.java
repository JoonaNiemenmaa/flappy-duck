package io.github.flappyduck;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Duck {
    final private Texture texture;

    private float x = 10;
    private float y = 20;

    final private float duck_width = 7.5f;
    final private float duck_height = 3.75f;

    final private float collision_grace = 1.685f;

    final private float flap_power = 0.6f;
    final private float gravity = 2;
    final private float max_fall_speed = 2;

    private float y_speed = 0;

    private boolean dead = false;

    public Duck () {
        texture = new Texture("duck.png");
    }

    public void update(float delta, GameWorld game_world, FitViewport viewport, Main main) {
        if (y_speed > -max_fall_speed) {
            y_speed -= gravity * delta;
        }
        if (Gdx.input.isTouched() && !dead) {
            y_speed = flap_power;
        }
        if (y > 5) {
            y += y_speed;
        } else {
            y_speed = 0;
        }


        if (detectCollision(game_world, viewport)) {
            dead = true;
            game_world.setScroll(false);
        }

    }

    private boolean detectCollision(GameWorld game_world, FitViewport viewport) {
        Chunk[] chunk_queue = game_world.getChunkQueue();
        for (Chunk chunk : chunk_queue) {
            boolean in_pipe_x = x > chunk.getPipeX() - duck_width + collision_grace && x < chunk.getPipeX() + chunk.getPipeWidth() - collision_grace;
            boolean in_pipe_y = y < chunk.getHoleHeight() || y > chunk.getHoleHeight() + chunk.getHoleSize() - duck_height;
            if (in_pipe_x && in_pipe_y) {
                return true;
            }
        }
        return y > viewport.getWorldHeight() - duck_height || y < 5;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, x, y, duck_width, duck_height);
    }
}
