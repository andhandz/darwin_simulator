package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Application;

public class World {
    public static void main(String[] args){
        try {
            Application.launch(App.class, args);
        }
        catch (IllegalArgumentException ex){
            System.out.println((ex.getMessage()));
            System.exit(1);
        }
    }
}
