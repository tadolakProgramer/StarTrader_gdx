package com.mygdx.game.GameObjekts.SpaceObjekt;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.MyGdxGame;

public abstract class SpaceObject extends Actor {

    private MyGdxGame game;
    public TextureRegion region;
    protected  Vector2 positionC;

    protected float rotationSpeed;
    public Label labelName;
    public String spaceObjectName;

    public SpaceObject(final MyGdxGame game) {
        super();
        this.game = game;
        region = new TextureRegion();
        positionC = new Vector2();
        addLabel();
        }

    private void addLabel() {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();
        labelStyle.fontColor = Color.YELLOW;
        labelName = new Label(spaceObjectName, labelStyle);
    }

    public void setTexture(Texture texture){
        setWidth(texture.getWidth());
        setHeight(texture.getHeight());
        region.setRegion(texture);
        setOrigin(texture.getWidth()/2, texture.getHeight()/2);

    }

    public void act(float dt){
        super.act(dt);
        //labelName.act(dt);
        update(dt);
    }

    public void draw(Batch batch, float parentAlpha)
    {
        Color c = getColor();
        batch.setColor(c.r, c.g, c.b, c.a);
        if ( isVisible() )
            batch.draw( region, getX(), getY(), getOriginX(),
                    getOriginY(),
                    getWidth(), getHeight(), getScaleX(),
                    getScaleY(), getRotation() );
        labelName.draw(batch, parentAlpha);
    }

    public void update(float dt){

        if ((getX() > game.GAME_WIDTH) || ((getX() + getWidth() * getScaleX()) < 0)
                || (getY() > game.GAME_HEIGHT) || ((getY() + getHeight()*getScaleX()) < 0) ) {
            setVisible(false);
        }
            else{
                setVisible(true);
                setPositionC();
                setRotations(dt);
            labelName.setX( positionC.x - labelName.getPrefWidth()/2);
            labelName.setY(positionC.y - getHeight()/2 * getScaleY() - labelName.getPrefHeight()/2);
            }
    }

    private void setRotations(float dt) {
        setRotation(getRotation()+rotationSpeed * dt);
    }

    public void  setPositionC(){
        positionC.x = (getX() + getOriginX());
        positionC.y = (getY() + getOriginY());
    }

    public void setSpaceObjectName(String spaceObjectName) {
        this.spaceObjectName = spaceObjectName;
        labelName.setText(spaceObjectName);
    }
}

