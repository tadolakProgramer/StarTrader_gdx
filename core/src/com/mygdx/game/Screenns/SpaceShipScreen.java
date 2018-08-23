package com.mygdx.game.Screenns;


import com.mygdx.game.GameObjekts.SpaceObjekt.SpaceShipDraft;
import com.mygdx.game.MyGdxGame;

public class SpaceShipScreen extends AbstractScreen {

    private SpaceShipDraft spaceShipDraft;

    public SpaceShipScreen(final MyGdxGame game) {
        super(game);
        game.kolejka.addFirst(this);
        isVisible = true;
        intSpaceShipDraft();
    }


    private void intSpaceShipDraft() {
        spaceShipDraft = new SpaceShipDraft(game);
        stage.addActor(spaceShipDraft);

    }

    @Override
    public void render(float delta){
        if (isVisible) {
            super.render(delta);
            update();
            spriteBatch.begin();
            stage.draw();
            spriteBatch.end();
        }

    }
    private void update() {

            stage.act();
    }
    @Override
    public void hide(){
        isVisible = false;
    }

    @Override
    public void show(){
        isVisible = true;
    }
}
