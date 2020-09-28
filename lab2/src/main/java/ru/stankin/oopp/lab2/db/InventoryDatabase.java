package ru.stankin.oopp.lab2.db;

import ru.stankin.oopp.lab2.details.Detail;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class InventoryDatabase {

    private static final String DATABASE_FILE_NAME = "inventory.bin";

    private final HashMap<Detail, Integer> inventory = new HashMap<>();

    public InventoryDatabase(Set<Detail> details) {
        for(Detail detail : details) {
            inventory.put(detail, 0);
        }
    }

    public Map<Detail, Integer> getInventory() {
        return inventory;
    }

    public int getQuantityInInventory(Detail detail) {
        return inventory.getOrDefault(detail, 0);
    }

    public void addToInventory(Detail detail, int quantity) {
        if(quantity <= 0) {
            throw new IllegalArgumentException("quantity must be greater then 0");
        }
        int currentQuantity = inventory.getOrDefault(detail, 0);
        inventory.put(detail, currentQuantity + quantity);
    }

    public void removeFromInventory(Detail detail, int quantity) {
        if(quantity <= 0) {
            throw new IllegalArgumentException("quantity must be greater then 0");
        }
        int availableQuantity = inventory.getOrDefault(detail, 0);
        if(availableQuantity < quantity) {
            throw new IllegalArgumentException("quantity to remove is greater then quantity in inventory");
        }
        availableQuantity = availableQuantity - quantity;
        inventory.put(detail, availableQuantity);
    }

    public void saveToDisk() {
        try {
            FileOutputStream fos = new FileOutputStream(DATABASE_FILE_NAME);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(inventory);
            oos.close();
            fos.close();
            System.out.println("Inventory database saved to disk");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void loadFromDisk() {
        try {
            File saveFile = new File(DATABASE_FILE_NAME);
            if(saveFile.exists()) {
                FileInputStream fis = new FileInputStream(DATABASE_FILE_NAME);
                ObjectInputStream ois = new ObjectInputStream(fis);
                HashMap<Detail, Integer> savedInventory = (HashMap<Detail, Integer>) ois.readObject();
                inventory.putAll(savedInventory);
                ois.close();
                fis.close();
                System.out.println("Inventory database loaded from disk");
            }
            else {
                System.out.println("No database found on disk");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
