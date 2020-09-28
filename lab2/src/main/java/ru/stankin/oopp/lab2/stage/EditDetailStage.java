package ru.stankin.oopp.lab2.stage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ru.stankin.oopp.lab2.db.InventoryDatabase;
import ru.stankin.oopp.lab2.details.Detail;

public abstract class EditDetailStage extends Stage {

    protected final InventoryDatabase database;

    protected final Detail detail;

    public EditDetailStage(InventoryDatabase database, Detail detail) {
        super();

        this.database = database;
        this.detail = detail;

        initLayout();
    }

    private void initLayout() {
        Label name = new Label(detail.getName() + ", " + detail.getCategory());

        Label stock = new Label("На складе: " + database.getQuantityInInventory(detail) + " шт");

        Label quantityLabel = new Label("Кол-во");

        Label error = new Label("Некорректное значение");
        error.setTextFill(Color.RED);
        error.setVisible(false);

        TextField quantity = new TextField();
        quantity.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                quantity.setText(newValue.replaceAll("[^\\d]", ""));
            }
            error.setVisible(false);
        });

        HBox hBox = new HBox(quantityLabel, quantity);
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);

        Button action = new Button(getActionTitle());
        action.setOnAction(e -> {
            try {
                onAction(Integer.parseInt(quantity.getText()));
            }
            catch (Exception exception) {
                error.setVisible(true);
            }
        });

        VBox vbox = new VBox(name, stock, hBox, error, action);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10, 10, 10, 10));

        StackPane stackPane = new StackPane(vbox);
        Scene scene = new Scene(stackPane);

        setTitle(getWindowTitle());
        setScene(scene);
    }

    protected abstract String getWindowTitle();

    protected abstract String getActionTitle();

    protected abstract void onAction(int quantity);
}
