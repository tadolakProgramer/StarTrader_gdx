package com.mygdx.game.Screenns;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.GameObjekts.SpaceObjekt.SpaceShipDraft;
import com.mygdx.game.GameObjekts.SpaceObjekt.SpaceShipPlayer;
import com.mygdx.game.GameObjekts.SpaceShipParts.CargoType;
import com.mygdx.game.GameObjekts.SpaceShipParts.ModuleActor;
import com.mygdx.game.GameObjekts.SpaceShipParts.ModuleType;
import com.mygdx.game.GameObjekts.SpaceShipParts.ShipModule;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Screenns.Hud.WindowPaymentList;

import java.util.ArrayList;
import java.util.List;

import static com.mygdx.game.MyGdxGame.FILE_EARTH_SKIN;
import static com.mygdx.game.MyGdxGame.FILE_UI_SKIN;
import static com.mygdx.game.MyGdxGame.GAME_HEIGHT;
import static com.mygdx.game.MyGdxGame.GAME_WIDTH;

public class SpaceShipScreen extends AbstractScreen {

    private SpaceShipDraft spaceShipDraft;
    private ModuleActor moduleActor;
    private SpaceShipPlayer spaceShipPlayer;

    private Label moneyLabel;
    private static Label fuellLabel;
    private Label moneyLabelText;
    private Label titanLabelText;
    private Label grafenLabelText;
    private Label titanLabel;
    private Label grafelLabel;
    private Label worldLabel;
    private Label fuelLabelText;
    private ProgressBar fuelFillBar;
    protected Skin skin;
    private Skin skin2;
    private Table table;
    private Table tableCrows;

    private List<Table> crowTables = new ArrayList<>();
    private Label waterLabelText;
    private Label waterlLabel;

    private Window windowShipModuleInfo;


    public SpaceShipScreen(final MyGdxGame game, final SpaceShipPlayer spaceShipPlayer) {
        super(game);
        this.spaceShipPlayer = spaceShipPlayer;
        game.kolejka.addFirst(this);
        table = new Table();
        tableCrows = new Table();
        isVisible = true;
        skin = new Skin(Gdx.files.internal(FILE_EARTH_SKIN));
        skin2 = new Skin(Gdx.files.internal(FILE_UI_SKIN));
        intSpaceShipDraft(spaceShipPlayer);
        createTableWithCargo();
        createTableCrows();
    }


    private void intSpaceShipDraft(final SpaceShipPlayer spaceShipPlayer) {
        spaceShipDraft = new SpaceShipDraft(game);
        for(int i = 1; i <= 10 ; i++){

                int index = i;
                ShipModule shipModule = spaceShipPlayer.shipModules.get(i);
                moduleActor = new ModuleActor(this, shipModule , index, spaceShipDraft.getScale());
                stage.addActor(spaceShipDraft);
                stage.addActor(moduleActor);

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

    private void createTableCrows() {

        tableCrows.bottom().center().pad(10f);
        //tableCrows.debug();
        //make the table fill the entire stage
        tableCrows.setFillParent(false);

        //tableCrows.setWidth(200f);
        tableCrows.setColor(255,1,1,255);
        //Label.LabelStyle labelStyle = new Label.LabelStyle(new BitmapFont(), Color.CORAL);


        //Define dataLabel
        int personsNumber = spaceShipPlayer.persosns.size();

        for (int tn=0; tn<personsNumber; tn++) {

            crowTables.add(new Table());

            crowTables.get(tn).row().pad(5);
            crowTables.get(tn).add(new Label("Specjalist:", skin));
            crowTables.get(tn).add(new Label(spaceShipPlayer.persosns.get(tn).getCrowType().name(), skin));
            crowTables.get(tn).row().pad(5);
            crowTables.get(tn).add(new Label("Name:", skin));
            crowTables.get(tn).add(new Label(spaceShipPlayer.persosns.get(tn).getName(), skin));
            crowTables.get(tn).row().pad(5);
            crowTables.get(tn).add(new Label(spaceShipPlayer.persosns.get(tn).getFirstExperienceLevel().getExperienceType().name(), skin));
            crowTables.get(tn).add(new Label(Float.toString(spaceShipPlayer.persosns.get(tn).getFirstExperienceLevel().getLevel()), skin));
            crowTables.get(tn).row().pad(5);
            crowTables.get(tn).add(new Label(spaceShipPlayer.persosns.get(tn).getSecondExperienceLevel().getExperienceType().name(), skin));
            crowTables.get(tn).add(new Label(Float.toString(spaceShipPlayer.persosns.get(tn).getSecondExperienceLevel().getLevel()), skin));

            crowTables.get(tn).setPosition(100 + 200 * tn+1, 100);
            stage.addActor(crowTables.get(tn));
        }
    }

    private void createTableWithCargo() {

        table.top().pad(10f);
        //table.debug();
        //make the table fill the entire stage
        table.setFillParent(false);
        table.setPosition(5, MyGdxGame.GAME_HEIGHT);
        table.setWidth(200f);
        table.setColor(255,1,1,255);


        moneyLabelText = new Label("Money", skin);
        fuelLabelText = new Label("Fuel: ", skin);


        worldLabel = new Label("WORLD: ", skin);


        //define our labels using the String, and a Label style consisting of a font and color
        moneyLabel = new Label(String.format("%.2f", spaceShipPlayer.getMoney()), skin);
        fuellLabel =new Label(String.format("%.2f", spaceShipPlayer.getFill(CargoType.FUEL)), skin);



        //fuelFillBar = new ProgressBar(0.0f, (float) spaceShipPlayer.fuelCapacity, 1, false, skin, "fancy");

        //table.row();
        table.add(moneyLabelText).expandX().left();
        table.add(moneyLabel).expandX();

        table.row();
        table.add(fuelLabelText).expandX().left();
        table.add(fuellLabel).expandX();

        if (spaceShipPlayer.getFill(CargoType.TITAN) > 0) {

            titanLabelText = new Label("Titan:", skin, "titan");
            titanLabel = new Label(" "+spaceShipPlayer.getFill(CargoType.TITAN), skin, "titan");

            table.row();
            table.add(titanLabelText).expandX().left();
            table.add(titanLabel).expandX();
        }
        if (spaceShipPlayer.getFill(CargoType.GRAFEN) > 0) {

            grafenLabelText = new Label("Grafen", skin, "grafen");
            grafelLabel = new Label(" "+spaceShipPlayer.getFill(CargoType.GRAFEN) ,skin, "grafen");

            table.row();
            table.add(grafenLabelText).expandX().left();
            table.add(grafelLabel).expandX();
        }

        if (spaceShipPlayer.getFill(CargoType.WATER) > 0) {

            waterLabelText = new Label("Grafen", skin, "water");
            waterlLabel = new Label(" "+spaceShipPlayer.getFill(CargoType.WATER) ,skin, "water");

            table.row();
            table.add(waterLabelText).expandX().left();
            table.add(waterlLabel).expandX();
        }

        stage.addActor(table);

        //stage.addActor(fuelFillBar);

    }

    public void createWindowShipModuleInfo(ShipModule shipModule){


        int wares = shipModule.capacitys.size();
        windowShipModuleInfo = new Window(shipModule.getModuleType().name(), skin2);
        TextButton btnClose = new TextButton("Close", skin2);
        Table table = new Table();

        table.align(1);
        table.top();

        table.row();
        table.add(new Label("Name: ", skin2)).pad(5);
        table.add(new Label(shipModule.name, skin2)).pad(5);

        table.row();
        table.add(new Label("Capacity: ", skin2)).pad(5);
        table.add(new Label(""+shipModule.getCapacity(), skin2)).pad(5);

        for (int i =0; i < CargoType.values().length; i++){
            if (shipModule.capacitys.containsKey(CargoType.values()[i])){
                table.row();
                table.add(new Label((CargoType.values()[i]).toString(), skin2)).pad(5);
                table.add(new Label(shipModule.capacitys.get(CargoType.values()[i]).toString(), skin2)).pad(5);
                //
            }
        }

        table.row();
        table.add(new Label("Fill: ", skin2)).pad(5);
        table.add(new Label(String.format("%.2f",shipModule.getFill()), skin2)).pad(5);

        table.row();
        table.add(new Label("Base failure rate: ", skin2)).pad(5);
        table.add(new Label(""+shipModule.baseFailureRate, skin2)).pad(5);

        table.row();
        table.add(new Label("Factor failure rate: ", skin2)).pad(5);
        table.add(new Label(""+shipModule.experienceLevel, skin2)).pad(5);

        table.row();
        table.add(new Label("Cost: ", skin2)).pad(5);
        table.add(new Label(""+shipModule.cost, skin2)).pad(5);

        table.row().colspan(2).pad(10);
        table.add(btnClose);

        windowShipModuleInfo.setSize(300,400);
        windowShipModuleInfo.setPosition(GAME_WIDTH / 2.0f - 100, GAME_HEIGHT / 2.0f - 100);
        windowShipModuleInfo.add(table).grow().pad(10);

        stage.addActor(windowShipModuleInfo);

        btnClose.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                windowShipModuleInfo.remove();
                //gameScreen.multiplexer.removeProcessor(stage);
            }
        });
    }
}
