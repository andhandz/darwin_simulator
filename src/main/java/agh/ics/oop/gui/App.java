package agh.ics.oop.gui;

import agh.ics.oop.IWorldMap;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.Statistics;
import agh.ics.oop.Vector2d;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.FileNotFoundException;

public class App extends Application implements IDayObserver{
    private IWorldMap map;
    private SimulationEngine engine;
    private final GridPane gridPane = new GridPane();
    private agh.ics.oop.Parameters params;
    private final FlowPane statistic = new FlowPane();
    private Statistics stats;

    @Override
    public void start(Stage primaryStage) throws Exception {
    primaryStage.setScene(inputScreen(primaryStage));
    primaryStage.show();
    }

    public Scene inputScreen(Stage stage){
        FlowPane flowPane = new FlowPane();

        Label mapSize = new Label("Map size:");
        TextField mapSizeInput = new TextField();
        mapSizeInput.setMinWidth(300);
        VBox mapSizeBox = new VBox(mapSize,mapSizeInput);
        flowPane.getChildren().add(mapSizeBox);

        Label jungleSize = new Label("Jungle size:");
        TextField jungleSizeInput = new TextField();
        jungleSizeInput.setMinWidth(300);
        VBox jungleSizeBox = new VBox(jungleSize,jungleSizeInput);
        flowPane.getChildren().add(jungleSizeBox);

        Label animalAmount = new Label("Animal amount:");
        TextField animalAmountInput = new TextField();
        animalAmountInput.setMinWidth(300);
        VBox animalAmountBox = new VBox(animalAmount,animalAmountInput);
        flowPane.getChildren().add(animalAmountBox);

        Label startEnergy = new Label("Start energy:");
        TextField startEnergyInput = new TextField();
        startEnergyInput.setMinWidth(300);
        VBox startEnergyBox = new VBox(startEnergy,startEnergyInput);
        flowPane.getChildren().add(startEnergyBox);

        Label plantsAmount = new Label("Plants amount:");
        TextField plantsAmountInput = new TextField();
        plantsAmountInput.setMinWidth(300);
        VBox plantsAmountBox = new VBox(plantsAmount,plantsAmountInput);
        flowPane.getChildren().add(plantsAmountBox);

        Label plantEnergy = new Label("Plant energy:");
        TextField plantEnergyInput = new TextField();
        plantEnergyInput.setMinWidth(300);
        VBox plantEnergyBox = new VBox(plantEnergy,plantEnergyInput);
        flowPane.getChildren().add(plantEnergyBox);

        Label minimalEnergyForChild = new Label("Minimal energy for copulate:");
        TextField minimalEnergyForChildInput = new TextField();
        minimalEnergyForChildInput.setMinWidth(300);
        VBox minimalEnergyForChildBox = new VBox(minimalEnergyForChild,minimalEnergyForChildInput);
        flowPane.getChildren().add(minimalEnergyForChildBox);

        Button go = new Button("GO!");
        go.setOnAction(ev -> {
            int[] par = {Integer.parseInt(mapSizeInput.getText()),
                    Integer.parseInt(jungleSizeInput.getText()),
                    Integer.parseInt(animalAmountInput.getText()),
                    Integer.parseInt(startEnergyInput.getText()),
                    Integer.parseInt(plantsAmountInput.getText()),
                    Integer.parseInt(plantEnergyInput.getText()),
                    Integer.parseInt(minimalEnergyForChildInput.getText())
            };
            params = new agh.ics.oop.Parameters(par);
            this.engine = new SimulationEngine(params);
            this.map = this.engine.getMap();
            this.stats = this.engine.getStats();
            this.engine.addObserver(this);
            engine.setMoveDelay(300);
            Scene scene = new Scene(buttons(), 8*map.getMapSize()+200, 8*map.getMapSize()+200);
            stage.setScene(scene);
            stage.show();
            drawMap();
            Thread engineThread = new Thread(engine);
            engineThread.start();
        });

        go.setMinWidth(201);
        go.setMinHeight(20);
        flowPane.getChildren().add(go);
        flowPane.setAlignment(Pos.CENTER);
        return new Scene(flowPane,400,400);
    }

    public void drawMap(){
        GuiElementBox creator;
        try{
            creator = new GuiElementBox();
            for(int i=0;i<map.getMapSize();i++){
                for(int j=0; j< map.getMapSize();j++){
                    VBox pixel = creator.View(new Vector2d(i,j),map);
                    gridPane.add(pixel,i,j);
                    GridPane.setHalignment(pixel, HPos.CENTER);
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public VBox buttons(){
        Button start = new Button("start");
        Button stop = new Button("stop");
        start.setOnAction(ev -> engine.setRunning(true));
        stop.setOnAction(ev -> engine.setRunning(false));
        VBox buttonBox = new VBox(start,stop);
        return new VBox(gridPane,buttonBox,statistic);
    }

    public void stats(){
        Label day = new Label("Day: " + stats.getDay());
        Label animalCounter = new Label("Animals on map: " + stats.getAnimalCounter());
        Label plantCounter = new Label("Plants on map: " + stats.getPlantCounter());
        Label avgEnergy = new Label("Average animals energy: " + stats.getAvgEnergy());
        Label avgLivingTime = new Label("Average animals living time: " + stats.getAvgLivingTime());
        Label childrenCounter = new Label("Living children: " + stats.getChildrenCounter());
        VBox stats = new VBox(day,animalCounter,plantCounter,avgEnergy,avgLivingTime,childrenCounter);
        statistic.getChildren().add(stats);
    }

    @Override
    public void nextDay() {
        Platform.runLater(() -> {
            gridPane.getChildren().clear();
            statistic.getChildren().clear();
            drawMap();
            stats();
        });
    }
}


