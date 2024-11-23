package io.github.flappyduck;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScoreTracker {
    private int score = 0;

    final private Duck duck;
    final private GameWorld game_world;

    final private BitmapFont font;

    private Chunk current_chunk;

    public ScoreTracker(Duck duck, GameWorld game_world, BitmapFont font) {
        this.duck = duck;
        this.game_world = game_world;
        this.font = font;

        current_chunk = game_world.getChunkQueue()[0];
    }

    public void update() {
        if (current_chunk.getX() + current_chunk.getChunkWidth() - current_chunk.getPipeWidth() / 2 < duck.getX()) {
            score += 1;
            current_chunk = game_world.getNextChunk(current_chunk);
        }
    }

    public void drawScore(SpriteBatch batch) {
        font.draw(batch, Integer.toString(score), 38, 49);
    }
}
