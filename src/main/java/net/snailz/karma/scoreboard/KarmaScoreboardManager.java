package net.snailz.karma.scoreboard;

import net.snailz.karma.Karma;

public class KarmaScoreboardManager {

    private Karma plugin;
    private KarmaScoreboard karmaScoreboard;

    public KarmaScoreboardManager(Karma plugin){
        this.plugin = plugin;
    }

    public KarmaScoreboard getScoreboard(){
        if (plugin.getConfig().getString("scoreboardmethod").equalsIgnoreCase("vanilla")){
            karmaScoreboard = new VanillaScoreboard(plugin.getConfig());
            return karmaScoreboard;
        } else if (plugin.getConfig().getString("scoreboardmethod").equalsIgnoreCase("vault")){
            karmaScoreboard = new VaultScoreboard();
            return karmaScoreboard;
        }
        //No Valid Scoreboard Set
        plugin.getConfig().set("scoreboardmethod", "vanilla");
        karmaScoreboard = new VanillaScoreboard(plugin.getConfig());
        return karmaScoreboard;
    }


}
