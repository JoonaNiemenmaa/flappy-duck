package io.github.flappyduck;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class FlappyDuck implements Screen {

    final private Main main;
    final private Duck duck;
    final private GameWorld game_world;
    final private FitViewport viewport;
    final private Background background;

    public FlappyDuck(Main main) {
        this.main = main;
        viewport = new FitViewport(80, 50);
        game_world = new GameWorld(viewport);
        background = new Background(viewport);
        duck = new Duck(game_world, background, viewport);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.DARK_GRAY);

        duck.update(delta);
        game_world.update();
        background.update(delta);

        viewport.apply();
        main.batch.setProjectionMatrix(viewport.getCamera().combined);

        main.batch.begin();
        background.draw(main.batch);
        game_world.draw(main.batch);
        duck.draw(main.batch);
        main.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
