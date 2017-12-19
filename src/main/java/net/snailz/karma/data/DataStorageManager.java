package net.snailz.karma.data;

import net.snailz.karma.Karma;
import net.snailz.karma.config.KarmaConfig;

public class DataStorageManager {

    private Karma plugin;
    private DataStorage dataStorage;

    public DataStorageManager(Karma plugin){
        this.plugin = plugin;
    }

    public DataStorage getDataStorage(){
        String storageMethod = plugin.getConfig().getString("storagemethod");
        if (storageMethod.equalsIgnoreCase("file")){
            dataStorage = new FlatFileStorage(plugin);
            return dataStorage;
        }
        if (storageMethod.equalsIgnoreCase("mysql")){
            dataStorage = new MySQLStorage(plugin);
            return dataStorage;
        }
        //There is no valid data storage type specified in config so change it to file and return FlatFileStorage
        plugin.getConfig().set("storagemethod", "file");
        dataStorage = new FlatFileStorage(plugin);
        return dataStorage;
    }
}
