package com.mygdx.game.Screenns;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.mygdx.game.GameObjekts.SpaceObjekt.SpaceShipDraft;
import com.mygdx.game.GameObjekts.SpaceObjekt.SpaceShipPlayer;
import com.mygdx.game.GameObjekts.SpaceShipParts.ModuleActor;
import com.mygdx.game.GameObjekts.SpaceShipParts.ModuleType;
import com.mygdx.game.MyGdxGame;

import static com.mygdx.game.GameObjekts.SpaceShipParts.ModuleType.EMPTY;

public class SpaceShipScreen extends AbstractScreen {

    private SpaceShipDraft spaceShipDraft;
    private ModuleActor moduleActor;
    private SpaceShipPlayer spaceShipPlayer;

    private Label moneyLabel;
    private static Label fuellLabel;
    private Label moneyLabelText;
    private Label titanLabelText;
    private Label titanLabel;
    private Label worldLabel;
    private Label fuelLabelText;
    private ProgressBar fuelFillBar;
    protected Skin skin;
    private Table table;


    private float time = 0;
    private int fps = 0;


    public SpaceShipScreen(final MyGdxGame game, final SpaceShipPlayer spaceShipPlayer) {
        super(game);
        this.spaceShipPlayer = spaceShipPlayer;
        game.kolejka.addFirst(this);
        table = new Table();
        isVisible = true;
        skin = new Skin(Gdx.files.internal("skin/flat-earth-ui.json"));
        intSpaceShipDraft(spaceShipPlayer);
        createTableWithCargo();
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

    private void createTableWithCargo() {

        table.top().pad(10f);
        //table.debug();
        //make the table fill the entire stage
        table.setFillParent(false);
        table.setPosition(5, MyGdxGame.GAME_HEIGHT);
        table.setWidth(200f);
        table.setColor(255,1,1,255);
        float x = table.getX();
        float y = table.getY();
/*
        ProgressBar.ProgressBarStyle progressBarStyle = skin.get("fancy", ProgressBar.ProgressBarStyle.class);
        TiledDrawable tiledDrawable = skin.getTiledDrawable("slider-fancy-knob").tint(skin.getColor("fuell"));
        tiledDrawable.setMinWidth(0);
        progressBarStyle.knobBefore = tiledDrawable;
*/

        moneyLabelText = new Label("Money", new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
        fuelLabelText = new Label("Fuel: ", new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
        titanLabelText = new Label("Titan:", new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
        worldLabel = new Label("WORLD: ", new Label.LabelStyle(new BitmapFont(), Color.YELLOW));


        //define our labels using the String, and a Label style consisting of a font and color
        moneyLabel = new Label(String.format("%.2f", spaceShipPlayer.getMoney()), new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
        fuellLabel =new Label(String.format("%.2f", spaceShipPlayer.fuelFill), new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
        titanLabel = new Label(String.format("%.2f",spaceShipPlayer.titanFill), new Label.LabelStyle(new BitmapFont(), skin.getColor("titan")));

        //fuelFillBar = new ProgressBar(0.0f, (float) spaceShipPlayer.fuelCapacity, 1, false, skin, "fancy");

        //table.row();
        table.add(moneyLabelText).expandX().left();
        table.add(moneyLabel).expandX();

        table.row();
        table.add(fuelLabelText).expandX().left();
        table.add(fuellLabel).expandX();

        if (spaceShipPlayer.titanFill > 0) {
            table.row();
            table.add(titanLabelText).expandX().left();
            table.add(titanLabel).expandX();
        }

        stage.addActor(table);

        //stage.addActor(fuelFillBar);


    }
}
