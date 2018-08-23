package com.mygdx.game.Screenns;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.MyGdxGame;

/**
 * Created by User on 2018-03-07.
 */

public class SpalshScreen extends AbstractScreen {

    private Texture spalashImg;

    public SpalshScreen(final MyGdxGame game) {
        super(game);
        initSplashScreen();
        final Screen gameScreen = new GameScreen(game);
        game.kolejka.addFirst(gameScreen);

        Timer.schedule(new Task() {
            @Override
            public void run() {
                game.setScreen(gameScreen);
            }
        },1);

        }

    private void initSplashScreen() {
        spalashImg = new Texture("badlogic.jpg");
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        spriteBatch.begin();
        spriteBatch.draw(spalashImg, 10, 10);
        spriteBatch.end();
    }
}
