package com.mygdx.game.GameObjekts.SpaceObjekt;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MyGdxGame;

import static com.mygdx.game.MyGdxGame.FILE_SPACESHIP;
import static com.mygdx.game.MyGdxGame.GAME_WIDTH;




public class SpaceShipDraft extends Image {

    public float scale;


    public SpaceShipDraft(final MyGdxGame game) {
        super(new Texture(FILE_SPACESHIP));
        initialize();

        this.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                game.setScreen(game.gameScreen);

                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    private void initialize(){
        scale = GAME_WIDTH / this.getWidth() ;
        this.setScale(scale);
        this.setPosition((GAME_WIDTH / 2) - (this.getWidth()*scale/2), (MyGdxGame.GAME_HEIGHT / 2) - (this.getHeight()*scale/2));
    }

    public float getScale() {
        return scale;
    }
}
