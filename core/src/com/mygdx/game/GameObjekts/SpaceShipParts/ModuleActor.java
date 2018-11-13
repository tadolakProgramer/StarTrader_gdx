package com.mygdx.game.GameObjekts.SpaceShipParts;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MyGdxGame;

import static com.mygdx.game.MyGdxGame.FILE_CARGO_ATLAS;

public class ModuleActor extends Actor {

    private String textureName;
    public TextureRegion region;
    public TextureAtlas textureAtlas;

    public ModuleActor(final MyGdxGame game, final ShipModule shipModule, int index, float scale) {
        super();
        textureAtlas = new TextureAtlas(FILE_CARGO_ATLAS);
        region = new TextureRegion();

        switch (shipModule.moduleType) {
                case FUEL: {
                    textureName = "CargoFuel";
                    setTexture(textureName);
                    setScale(scale);
                    setPositionModuleOnShip(index);
                    break;
                }
                case GAS: {
                    textureName = "CargoGrafen";
                    setTexture(textureName);
                    setScale(scale);
                    setPositionModuleOnShip(index);
                    break;
                }
                case HOUSING_MODULE: {
                    textureName = "CargoHM";
                    setTexture(textureName);
                    setScale(scale);
                    setPositionModuleOnShip(index);
                    break;
                }
                case LOSE: {
                    textureName = "CargoTitan";
                    setTexture(textureName);
                    setScale(scale);
                    setPositionModuleOnShip(index);
                    break;
                }
                case EMPTY:{
                    textureName = "Empty";
                    setTexture(textureName);
                    setScale(scale);
                    setPositionModuleOnShip(index);
                    break;
                }
                default:{
                    textureName = "CargoTitan";
                    setTexture(textureName);
                }
            }
        this.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (!shipModule.getModuleType().equals(ModuleType.EMPTY)) {
                        System.out.println("Fuell: " + shipModule.capacitys.get(CargoType.FUEL));
                }

                return super.touchDown(event, x, y, pointer, button);
            }
        });

    }

    private void setPositionModuleOnShip(int index) {

        if (index <=5) {
            setPosition(349 + (index - 1) * 93.3f, 270);
        }
        else
        {
            setPosition(420 + (index - 6) * 93.3f, 523);
            setRotation(180);
        }
    }

    private void setTexture(String textureName){
        setWidth(textureAtlas.findRegion(textureName).packedWidth);
        setHeight(textureAtlas.findRegion(textureName).packedHeight);
        region.setRegion(textureAtlas.findRegion(textureName));
    }

    public void act(float dt){
        super.act(dt);
        //labelName.act(dt);
        update(dt);
    }

    public void update(float dt) {

    }

    public void draw(Batch batch, float parentAlpha)
    {
        Color c = getColor();
        batch.setColor(c.r, c.g, c.b, c.a);
        if ( isVisible() )
            batch.draw(region, getX(), getY(), getOriginX(),
                    getOriginY(),
                    getWidth(), getHeight(), getScaleX(),
                    getScaleY(), getRotation());

    }
}
