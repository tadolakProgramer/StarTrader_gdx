package com.mygdx.game.Screenns;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.MyGdxGame;

import static com.badlogic.gdx.scenes.scene2d.InputEvent.Type.touchDragged;

/**
 * Created by User on 2018-03-07.
 */

public abstract class AbstractScreen implements Screen {

    protected MyGdxGame game;
    protected Stage stage;
    public OrthographicCamera camera;
    protected SpriteBatch spriteBatch;
    protected boolean isVisible;


    public AbstractScreen(MyGdxGame game) {
        this.game = game;
        createCamera();
        stage = new Stage(new FitViewport(MyGdxGame.GAME_WIDTH, MyGdxGame.GAME_HEIGHT, camera));
        spriteBatch = new SpriteBatch();
        Gdx.input.setInputProcessor(stage);
    }



    private void createCamera() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, MyGdxGame.GAME_WIDTH, MyGdxGame.GAME_HEIGHT);
        camera.update();
    }


    @Override
    public void render (float delta) {
        clearScreen();
        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);

    }

    private void clearScreen() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void show() {

    }

    @Override
    public void resume(){
        game.setPaused(false);
    }

    @Override
    public void pause() {
        game.setPaused(true);
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        game.dispose();
    }

    @Override
    public void resize(int width, int height) {

    }
}
