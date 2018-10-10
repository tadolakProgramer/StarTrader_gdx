package com.mygdx.game.Screenns;



import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.GameObjekts.SpaceObjekt.SpaceShipDraft;
import com.mygdx.game.GameObjekts.SpaceObjekt.SpaceShipPlayer;
import com.mygdx.game.GameObjekts.SpaceShipParts.ModuleActor;
import com.mygdx.game.GameObjekts.SpaceShipParts.ModuleType;
import com.mygdx.game.MyGdxGame;

import static com.mygdx.game.GameObjekts.SpaceShipParts.ModuleType.EMPTY;

public class SpaceShipScreen extends AbstractScreen {

    private SpaceShipDraft spaceShipDraft;
    private ModuleActor moduleActor;





    public SpaceShipScreen(final MyGdxGame game, final SpaceShipPlayer spaceShipPlayer) {
        super(game);
        game.kolejka.addFirst(this);
        isVisible = true;
        intSpaceShipDraft(spaceShipPlayer);
    }


    private void intSpaceShipDraft(final SpaceShipPlayer spaceShipPlayer) {
        spaceShipDraft = new SpaceShipDraft(game);
        for(int i = 1; i <= 10 /*spaceShipPlayer.schipModules.size()-1*/ ; i++){

            if (spaceShipPlayer.schipModules.get(i).moduleType !=  EMPTY) {

                int index = i;
                ModuleType moduleType = spaceShipPlayer.schipModules.get(i).moduleType;
                moduleActor = new ModuleActor(game, moduleType, index, spaceShipDraft.getScale());
                stage.addActor(spaceShipDraft);
                stage.addActor(moduleActor);
            }
        }
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
    @Override
    public void dispose(){

    }
}
