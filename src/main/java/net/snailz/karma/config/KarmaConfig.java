package net.snailz.karma.config;

import net.snailz.karma.user.KarmaLevel;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;


public class KarmaConfig {

    public static String prefix;

    public static int defaultKarma;

    public static boolean yellowEnabled;
    public static int yellowTime;

    private static FileConfiguration config;

    /*
    0 = Red Karma Change
    1 = Neutral Karma Change
    2 = Green Karma Change
    */
    private static int[] redKarmaChange;
    private static int[] neutralKarmaChange;
    private static int[] greenKarmaChange;

    public static void initKarmaConfig(FileConfiguration config_instance){
        config = config_instance;
        prefix = ChatColor.translateAlternateColorCodes('&', config.getString("prefix")) + " ";
        defaultKarma = config.getInt("defaultkarma");
        yellowEnabled = config.getBoolean("yellowplayers.enabled");
        yellowTime = config.getInt("yellowplayers.time");

        loadRanges();
        loadKarmaChages();
    }

    private static void loadKarmaChages(){
        redKarmaChange = new int[]{config.getInt("karmachanges.red.red"), config.getInt("karmachanges.red.neutral"),
                config.getInt("karmachanges.red.green")};
        neutralKarmaChange = new int[]{config.getInt("karmachanges.neutral.red"), config.getInt("karmachanges.neutral.neutral"),
                config.getInt("karmachanges.neutral.green")};
        greenKarmaChange = new int[]{config.getInt("karmachanges.green.red"), config.getInt("karmachanges.green.neutral"),
                config.getInt("karmachanges.green.green")};
    }

    private static void loadRanges() throws IllegalArgumentException{
        //Parsing code here
    }

    public static int getKarmaChange(KarmaLevel killer, KarmaLevel killed){
        int karmaChange;

        switch (killer){
            case RED:
                switch (killed){
                    case RED:
                        return redKarmaChange[0];
                    case NEUTRAL:
                        return redKarmaChange[1];
                    case GREEN:
                        return redKarmaChange[2];
                }
            case NEUTRAL:
                switch (killed){
                    case RED:
                        return neutralKarmaChange[0];
                    case NEUTRAL:
                        return neutralKarmaChange[1];
                    case GREEN:
                        return neutralKarmaChange[2];
                }
            case GREEN:
                switch (killed){
                    case RED:
                        return greenKarmaChange[0];
                    case NEUTRAL:
                        return greenKarmaChange[1];
                    case GREEN:
                        return greenKarmaChange[2];
                }
        }

        return 0;

    }

    public static KarmaLevel getKarmaLevel(int karma){
        //Karma to KarmaLevel code here
    }
}
