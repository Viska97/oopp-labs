package ru.stankin.oopp.lab2;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.stankin.oopp.lab2.db.InventoryDatabase;
import ru.stankin.oopp.lab2.details.*;
import ru.stankin.oopp.lab2.stage.MainStage;

import java.util.HashSet;
import java.util.Set;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        new MainStage(new InventoryDatabase(getDetails())).show();
    }

    public static Set<Detail> getDetails() {
        Set<Detail> details = new HashSet<>();
        details.add(new Body());
        details.add(new Cap());
        details.add(new Disk());
        details.add(new Glassful());
        details.add(new Nut());
        details.add(new Pin());
        details.add(new Screw());
        details.add(new Shaft());
        details.add(new Sleeve());
        return details;
    }
}
