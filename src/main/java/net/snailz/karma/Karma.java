
package net.snailz.karma;

import net.snailz.karma.api.KarmaAPI;
import net.snailz.karma.commands.AdminCommands;
import net.snailz.karma.config.CustomConfig;
import net.snailz.karma.config.KarmaConfig;
import net.snailz.karma.config.Messages;
import net.snailz.karma.data.DataStorage;
import net.snailz.karma.data.DataStorageManager;
import net.snailz.karma.listeners.DamageListener;
import net.snailz.karma.listeners.JoinLeaveListeners;
import net.snailz.karma.listeners.KillListener;
import net.snailz.karma.scoreboard.KarmaScoreboard;
import net.snailz.karma.scoreboard.KarmaScoreboardManager;
import net.snailz.karma.user.KarmaUserManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Karma extends JavaPlugin{

    private DataStorageManager dataStorageManager = new DataStorageManager(this);
    private DataStorage dataStorage;
    private KarmaUserManager karmaUserManager;
    private KarmaScoreboardManager karmaScoreboardManager;
    public static KarmaScoreboard karmaScoreboard;

    @Override
    public void onEnable(){
        this.saveDefaultConfig();
        CustomConfig messages = new CustomConfig("messages", this);
        messages.saveDefaultCustomFile();
        dataStorage = dataStorageManager.getDataStorage();
        karmaUserManager = new KarmaUserManager(dataStorage);
        karmaScoreboardManager = new KarmaScoreboardManager(this);
        karmaScoreboard = karmaScoreboardManager.getScoreboard();

        initListeners();

        KarmaAPI.getInstance().initAPI(karmaUserManager);

        KarmaConfig.initKarmaConfig(this.getConfig());
        Messages.initMessages(messages);

        this.getCommand("karma").setExecutor(new AdminCommands(karmaUserManager));

    }

    private void initListeners(){
        this.getServer().getPluginManager().registerEvents(new JoinLeaveListeners(karmaUserManager), this);
        this.getServer().getPluginManager().registerEvents(new KillListener(karmaUserManager), this);
        this.getServer().getPluginManager().registerEvents(new DamageListener(karmaUserManager), this);
    }

    @Override
    public void onDisable(){
        karmaUserManager.saveAllKarma();
    }

}
