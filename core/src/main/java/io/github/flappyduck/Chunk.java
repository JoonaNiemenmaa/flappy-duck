package io.github.flappyduck;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Chunk {
    private float x_position = 0;

    final private int hole_height;
    final private int hole_size = 3;
    final private int tiles_until_pipe;

    final private float tile_width = 5;
    final private float tile_height = 5;

    final private float pipe_width = 10;
    final private float pipe_height = 5;

    final private float[] ground_array;
    final private float[] pipe_array;

    final private Texture ground_texture;
    final private Texture pipe_body_texture;
    final private Texture pipe_base_texture;
    final private Texture pipe_bottom_texture;
    final private Texture pipe_top_texture;

    public Chunk(float x_position, int tiles_until_pipe, GameWorld.ChunkTextures chunk_textures) {
        this.tiles_until_pipe = tiles_until_pipe;
        this.x_position = x_position;

        ground_texture = chunk_textures.ground_texture;
        pipe_body_texture = chunk_textures.pipe_body_texture;
        pipe_base_texture = chunk_textures.pipe_base_texture;
        pipe_bottom_texture = chunk_textures.pipe_bottom_texture;
        pipe_top_texture = chunk_textures.pipe_top_texture;

        ground_array = new float[tiles_until_pipe];
        for (int i = 0; i < ground_array.length; i++) {
            ground_array[i] = i * tile_width;
        }

        pipe_array = new float[10];
        for (int i = 0; i < pipe_array.length; i++) {
            pipe_array[i] = i * tile_height;
        }

        hole_height = (int)(Math.random() * 4 + 3);

    }

    public void draw(SpriteBatch batch) {
        for (float pos : ground_array) {
            float x = x_position + pos;
            batch.draw(ground_texture, x, 0, tile_width, tile_height);
        }
        float pipe_x = x_position + ground_array[ground_array.length - 1] + tile_width;
        for (int i = 0; i < pipe_array.length; i++) {

            if (i == 0) {
                batch.draw(pipe_base_texture, pipe_x, pipe_array[i], pipe_width, pipe_height);
            } else if (i == hole_height - 1) {
                batch.draw(pipe_top_texture, pipe_x, pipe_array[i], pipe_width, pipe_height);
            } else if (i == hole_height + hole_size) {
                batch.draw(pipe_bottom_texture, pipe_x, pipe_array[i], pipe_width, pipe_height);
            } else if (i < hole_height - 1 || i > hole_height + 3) {
                batch.draw(pipe_body_texture, pipe_x, pipe_array[i], pipe_width, pipe_height);
            }
        }
    }

    public float getX() {
        return x_position;
    }

    public void setX(float x) {
        this.x_position = x;
    }

    public float getChunkWidth() {
        return (tiles_until_pipe + 2) * tile_width;
    }

    public float getHoleHeight() {
        return hole_height * tile_height;
    }

    public float getHoleSize() {
        return hole_size * tile_height;
    }

    public float getPipeX() {
        return x_position + ground_array[ground_array.length - 1] + tile_width;
    }

    public float getTileWidth() {
        return tile_width;
    }

    public float getTileHeight() {
        return tile_height;
    }

    public float getPipeWidth() {
        return pipe_width;
    }

    public float getPipeHeight() {
        return pipe_height;
    }

}
