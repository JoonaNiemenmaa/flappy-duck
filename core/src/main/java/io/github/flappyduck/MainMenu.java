package io.github.flappyduck;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenu implements Screen {

    private Main main;

    public MainMenu(Main main) {
        this.main = main;
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.DARK_GRAY);

        main.batch.begin();
        main.font.draw(main.batch, "Welcome to flappy duck! Press anywhere to start", 100, 100);
        main.batch.end();

        if (Gdx.input.isTouched()) {
            main.setScreen(new FlappyDuck(main));
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {

    }
}
