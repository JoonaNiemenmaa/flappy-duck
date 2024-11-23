package io.github.flappyduck;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;


public class Main extends Game {
    SpriteBatch batch;
    BitmapFont font;
    FitViewport viewport;

    final private float font_scale = 2f;

    @Override
    public void create() {
        batch = new SpriteBatch();
        viewport = new FitViewport(80, 50);
        font = new BitmapFont(Gdx.files.internal("font.fnt"));
        font.setUseIntegerPositions(false);
        font.getData().setScale((viewport.getWorldHeight() / Gdx.graphics.getHeight()) * font_scale);

        this.setScreen(new FlappyDuck(this));
    }
    public void render() {
        super.render(); // important!
    }
}
