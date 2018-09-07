package com.mygdx.game.GameObjekts.SpaceObjekt;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.GameObjekts.SpaceShipParts.ModuleType;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Screenns.SpaceShipScreen;

import static com.mygdx.game.MyGdxGame.GAME_WIDTH;




public class SpaceShipDraft extends Image {

    public Screen actScreen;
    public float scale;


    private SpriteBatch batch;
    private Texture texture;


    public SpaceShipDraft(final MyGdxGame game) {
        super(new Texture("SpaceShip_Empty.png"));
        initialize();


        this.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                actScreen = game.kolejka.pollFirst();
                game.setScreen(game.kolejka.getFirst());

                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }
/*
    private void drawMOdule(final MyGdxGame game) {
                   for (int slot = 0; slot<9; slot++){
                if (spaceShipPlayer.schipModules.get(slot).moduleType!= ModuleType.EMPTY){
                    batch.begin();
                    game.spaceShipPlayer.schipModules.get(slot).sprite.draw(batch);
                    batch.end();
                }
            }

    }
    */

    private void initialize(){
        scale = GAME_WIDTH / this.getWidth() ;
        this.setScale(scale);
        this.setPosition((GAME_WIDTH / 2) - (this.getWidth()*scale/2), (MyGdxGame.GAME_HEIGHT / 2) - (this.getHeight()*scale/2));
    }
}
