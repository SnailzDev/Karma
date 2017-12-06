package net.snailz.karma;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public class KarmaConfig {

    public static String prefix;

    public static int defaultKarma;


    public static void initKarmaConfig(FileConfiguration config){
        prefix = ChatColor.translateAlternateColorCodes('&', config.getString("prefix")) + " ";
        defaultKarma = config.getInt("defaultkarma");
    }
}
