package ru.stankin.oopp.lab2.stage;

import ru.stankin.oopp.lab2.db.InventoryDatabase;
import ru.stankin.oopp.lab2.details.Detail;

public class AddDetailStage extends EditDetailStage {

    public AddDetailStage(InventoryDatabase database, Detail detail) {
        super(database, detail);
    }

    @Override
    protected String getWindowTitle() {
        return "Добавить детали";
    }

    @Override
    protected String getActionTitle() {
        return "Добавить";
    }

    @Override
    protected void onAction(int quantity) {
        database.addToInventory(detail, quantity);
        database.saveToDisk();
        close();
    }
}
