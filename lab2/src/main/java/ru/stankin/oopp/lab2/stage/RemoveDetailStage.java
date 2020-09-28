package ru.stankin.oopp.lab2.stage;

import ru.stankin.oopp.lab2.db.InventoryDatabase;
import ru.stankin.oopp.lab2.details.Detail;

public class RemoveDetailStage extends EditDetailStage {

    public RemoveDetailStage(InventoryDatabase database, Detail detail) {
        super(database, detail);
    }

    @Override
    protected String getWindowTitle() {
        return "Убрать детали";
    }

    @Override
    protected String getActionTitle() {
        return "Убрать";
    }

    @Override
    protected void onAction(int quantity) {
        database.removeFromInventory(detail, quantity);
        database.saveToDisk();
        close();
    }
}
