package agh.ics.oop.gui;

import agh.ics.oop.IWorldMap;
import agh.ics.oop.Vector2d;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox {
    private Image imageGrass = null;
    private Image imageAnimal = null;
    private Image imagePlant = null;
    private Image imageJungle = null;
    private Image imageLowAnimal = null;
    private Image imageDyingAnimal = null;

    public GuiElementBox() throws FileNotFoundException {
        try {
            this.imageGrass = new Image(new FileInputStream("src/main/resources/grass.jpeg"));
            this.imageAnimal = new Image(new FileInputStream("src/main/resources/animal.jpg"));
            this.imagePlant = new Image(new FileInputStream("src/main/resources/plant.jpg"));
            this.imageJungle = new Image(new FileInputStream("src/main/resources/jungle.png"));
            this.imageLowAnimal= new Image(new FileInputStream("src/main/resources/animalLow.png"));
            this.imageDyingAnimal = new Image(new FileInputStream("src/main/resources/1animal.png"));
        }
        catch (FileNotFoundException ex) {
            System.out.println("Couldn't load files -> " + ex);
        }
    }

    public VBox View(Vector2d cord, IWorldMap map){
        ImageView view;
        if(map.AnimalAt(cord)!=null && map.AnimalAt(cord).size()!=0){
            if(map.AnimalAt(cord).size()>1 || map.AnimalAt(cord).get(0).getEnergy()>=10) {
                view = new ImageView(imageAnimal);
            }
            else if(map.AnimalAt(cord).get(0).getEnergy()<10 && map.AnimalAt(cord).get(0).getEnergy()>1){
                view = new ImageView(imageLowAnimal);
            }
            else{
                view = new ImageView(imageDyingAnimal);
            }
        }
        else if(map.PlantAt(cord)!=null ){
            view = new ImageView(imagePlant);
        }
        else if(cord.follows(map.getJungleLowerLeft()) && cord.precedes(map.getJungleUpperRight())){
            view = new ImageView(imageJungle);
        }
        else{
            view = new ImageView(imageGrass);
        }
        view.setFitWidth(8);
        view.setFitHeight(8);
        VBox pixel = new VBox();
        pixel.getChildren().addAll(view);
        pixel.setAlignment(Pos.CENTER);
        return pixel;
    }
}
