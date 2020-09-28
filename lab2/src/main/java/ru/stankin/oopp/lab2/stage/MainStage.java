package ru.stankin.oopp.lab2.stage;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.stankin.oopp.lab2.db.InventoryDatabase;
import ru.stankin.oopp.lab2.details.Detail;

import java.util.Map;

public class MainStage extends Stage {

    private final InventoryDatabase database;

    private final ObservableList<Map.Entry<Detail,Integer>> inventoryList;

    private final TableView<Map.Entry<Detail,Integer>> inventoryTable;

    public MainStage(InventoryDatabase database) {
        this.database = database;
        this.inventoryList = FXCollections.observableArrayList();

        inventoryTable = createTable();

        Scene scene = new Scene(inventoryTable);
        setScene(scene);

        setWidth(800);
        setHeight(600);
        setTitle("Склад");

        fillTable();
    }

    private TableView<Map.Entry<Detail,Integer>> createTable() {
        TableView<Map.Entry<Detail,Integer>> table = new TableView<>(inventoryList);

        TableColumn<Map.Entry<Detail,Integer>, String> nameColumn = new TableColumn<>("Название детали");
        nameColumn.setCellValueFactory(entry -> new SimpleStringProperty(entry.getValue().getKey().getName()));
        nameColumn.setPrefWidth(220);

        TableColumn<Map.Entry<Detail,Integer>, String> categoryColumn = new TableColumn<>("Категория");
        categoryColumn.setCellValueFactory(entry -> new SimpleStringProperty(entry.getValue().getKey().getCategory()));
        categoryColumn.setPrefWidth(220);

        TableColumn<Map.Entry<Detail,Integer>, String> quantityColumn = new TableColumn<>("Кол-во на складе");
        quantityColumn.setCellValueFactory(entry -> new SimpleStringProperty(String.valueOf(entry.getValue().getValue())));
        quantityColumn.setPrefWidth(150);

        TableColumn<Map.Entry<Detail,Integer>, String> addButtonColumn = new TableColumn<>("");
        addButtonColumn.setCellFactory(param -> new TableCell<Map.Entry<Detail, Integer>, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                if(!empty) {
                    Button addButton = new Button("Добавить");
                    addButton.setOnAction(event -> addDetail(getTableView().getItems().get(getTableRow().getIndex()).getKey()));
                    setGraphic(addButton);
                    setText(null);
                }
            }
        });
        addButtonColumn.setPrefWidth(105);

        TableColumn<Map.Entry<Detail,Integer>, String> removeButtonColumn = new TableColumn<>("");
        removeButtonColumn.setCellFactory(param -> new TableCell<Map.Entry<Detail, Integer>, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                if(!empty) {
                    Button removeButton = new Button("Убрать");
                    removeButton.setOnAction(event -> removeDetail(getTableView().getItems().get(getTableRow().getIndex()).getKey()));
                    setGraphic(removeButton);
                    setText(null);
                }
            }
        });
        removeButtonColumn.setPrefWidth(100);

        table.getColumns().add(nameColumn);
        table.getColumns().add(categoryColumn);
        table.getColumns().add(quantityColumn);
        table.getColumns().add(addButtonColumn);
        table.getColumns().add(removeButtonColumn);

        table.getSortOrder().add(categoryColumn);
        table.getSortOrder().add(nameColumn);

        return table;
    }

    private void fillTable() {
        database.loadFromDisk();
        inventoryList.addAll(database.getInventory().entrySet());
        inventoryTable.sort();
    }

    private void updateTable() {
        for(int i=0; i<inventoryList.size(); i++) {
            Map.Entry<Detail, Integer> entry = inventoryList.get(i);
            int quantity = database.getQuantityInInventory(entry.getKey());
            entry.setValue(quantity);
            inventoryList.set(i, entry);
        }
    }

    private void addDetail(Detail detail) {
        Stage addStage = new AddDetailStage(database, detail);
        addStage.initModality(Modality.APPLICATION_MODAL);
        addStage.initOwner(this);
        addStage.showAndWait();
        updateTable();
    }

    private void removeDetail(Detail detail) {
        Stage removeStage = new RemoveDetailStage(database, detail);
        removeStage.initModality(Modality.APPLICATION_MODAL);
        removeStage.initOwner(this);
        removeStage.showAndWait();
        updateTable();
    }

}
