package net.snailz.karma;

import net.snailz.karma.data.DataStorage;
import net.snailz.karma.data.DataStorageManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Karma extends JavaPlugin{

    DataStorageManager dataStorageManager = new DataStorageManager(this);
    DataStorage dataStorage;

    public void onEnable(){
        dataStorage = dataStorageManager.getDataStorage();
    }
}
