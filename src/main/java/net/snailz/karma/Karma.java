
package net.snailz.karma;

import net.snailz.karma.api.KarmaAPI;
import net.snailz.karma.data.DataStorage;
import net.snailz.karma.data.DataStorageManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Karma extends JavaPlugin{

    DataStorageManager dataStorageManager = new DataStorageManager(this);
    DataStorage dataStorage;
    public KarmaUserManager karmaUserManager;

    @Override
    public void onEnable(){
        dataStorage = dataStorageManager.getDataStorage();
        karmaUserManager = new KarmaUserManager(dataStorage);

        initListeners();

        KarmaAPI.getInstance().initAPI(karmaUserManager);

        KarmaConfig.initKarmaConfig(this.getConfig());
    }

    private void initListeners(){
        this.getServer().getPluginManager().registerEvents(new JoinLeaveListeners(karmaUserManager), this);
    }

    @Override
    public void onDisable(){
        karmaUserManager.saveAllKarma();
    }
}
