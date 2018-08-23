package com.mygdx.game.Screenns;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.mygdx.game.MyGdxGame.GAME_HEIGHT;
import static com.mygdx.game.MyGdxGame.GAME_WIDTH;

public class Background implements Disposable {

    //Scene2D.ui Stage and its own Viewport for HUD
    public Stage stage;
    public Viewport viewport;
    private Image space;
    private Texture texture;

    public Background(SpriteBatch spriteBatch) {

    viewport =new FitViewport(GAME_WIDTH, GAME_HEIGHT, new OrthographicCamera());
    stage =new Stage(viewport, spriteBatch);
    texture = new Texture("space.jpg");
    space = new Image(texture);
    space.setHeight(GAME_HEIGHT);
    space.setWidth(GAME_WIDTH);
    stage.addActor(space);

}

    @Override
    public void dispose() {

    }

}
