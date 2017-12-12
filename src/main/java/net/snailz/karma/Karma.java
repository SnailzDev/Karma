
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
import net.snailz.karma.runnables.YellowTimer;
import net.snailz.karma.scoreboard.KarmaScoreboard;
import net.snailz.karma.scoreboard.KarmaScoreboardManager;
import net.snailz.karma.user.KarmaUserManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Karma extends JavaPlugin{

    private DataStorageManager dataStorageManager = new DataStorageManager(this);
    private DataStorage dataStorage;
    private KarmaUserManager karmaUserManager;
    private KarmaScoreboardManager karmaScoreboardManager;
    private YellowTimer yellowTimer;
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

        KarmaAPI.getInstance().initAPI(karmaUserManager);

        KarmaConfig.initKarmaConfig(this.getConfig());
        Messages.initMessages(messages);

        this.getCommand("karma").setExecutor(new AdminCommands(karmaUserManager));

        //Init Yellow Timer
        yellowTimer = new YellowTimer(karmaUserManager);
        yellowTimer.runTaskTimerAsynchronously(this, 20L, 20L);

        initListeners();

    }

    private void initListeners(){
        this.getServer().getPluginManager().registerEvents(new JoinLeaveListeners(karmaUserManager), this);
        this.getServer().getPluginManager().registerEvents(new KillListener(karmaUserManager), this);
        this.getServer().getPluginManager().registerEvents(new DamageListener(karmaUserManager, yellowTimer), this);
    }

    @Override
    public void onDisable(){
        karmaUserManager.saveAllKarma();
    }

}
