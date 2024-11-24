package io.github.flappyduck;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameWorld {

    private boolean scroll = true;

    final private int tiles_until_first_pipe = 16;
    final private int tiles_between_pipes = 4;

    final private float scroll_speed = 12;

    final private Chunk[] chunk_queue;

    public class ChunkTextures {

        final Texture ground_texture;
        final Texture pipe_body_texture;
        final Texture pipe_base_texture;
        final Texture pipe_bottom_texture;
        final Texture pipe_top_texture;

        public ChunkTextures() {
            ground_texture = new Texture("ground.png");
            pipe_body_texture = new Texture("pipe_body.png");
            pipe_base_texture = new Texture("pipe_base.png");
            pipe_bottom_texture = new Texture("pipe_bottom.png");
            pipe_top_texture = new Texture("pipe_top.png");
        }

    }

    final ChunkTextures chunk_textures;

    final FitViewport viewport;

    public GameWorld(FitViewport viewport) {
        this.viewport = viewport;
        chunk_textures = new ChunkTextures();

        chunk_queue = new Chunk[4];

        float x_position = 0;
        chunk_queue[0] = new Chunk(x_position, tiles_until_first_pipe, chunk_textures);
        x_position += chunk_queue[0].getChunkWidth();
        for (int i = 1; i < chunk_queue.length; i++) {
            chunk_queue[i] = new Chunk(x_position, tiles_between_pipes, chunk_textures);
            x_position += chunk_queue[i].getChunkWidth();
        }
    }

    public void update(float delta) {
        if (scroll) {
            for (Chunk chunk : chunk_queue) {
                if (chunk != null) {
                    chunk.setX(chunk.getX() - scroll_speed * delta);
                }
            }
            if (chunk_queue[0].getX() < -chunk_queue[0].getChunkWidth()) {
                for (int i = 1; i < chunk_queue.length; i++) {
                    chunk_queue[i - 1] = chunk_queue[i];
                }
                float next_x = chunk_queue[chunk_queue.length - 2].getChunkWidth() + chunk_queue[chunk_queue.length - 2].getX();
                chunk_queue[chunk_queue.length - 1] = new Chunk(next_x, tiles_between_pipes, chunk_textures);
            }
        }
    }

    public void draw(SpriteBatch batch) {
        for (Chunk chunk : chunk_queue) {
            if (chunk != null) {
                chunk.draw(batch);
            }
        }
    }

    public void setScroll(boolean scroll) {
        this.scroll = scroll;
    }

    public Chunk[] getChunkQueue() {
        return chunk_queue;
    }

    public float getScrollSpeed() {
        return scroll_speed;
    }

    public Chunk getNextChunk(Chunk current_chunk) {
        for (int i = 0; i < chunk_queue.length; i++) {
            if (chunk_queue[i] == current_chunk) {
                return chunk_queue[i + 1];
            }
        }
        return chunk_queue[0];
    }
}
