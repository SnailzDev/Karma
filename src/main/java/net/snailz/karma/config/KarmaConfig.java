package net.snailz.karma.config;

import net.snailz.karma.user.KarmaLevel;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;


public class KarmaConfig {

    private static KarmaConfig karmaConfig = new KarmaConfig();

    private KarmaConfig(){

    }

    public static KarmaConfig getInstance(){
        return karmaConfig;
    }

    public String prefix;

    public int defaultKarma;

    public boolean yellowEnabled;
    public int yellowTime;

    public boolean maxMinEnabled;
    public int maxKarma;
    public int minKarma;

    private FileConfiguration config;

    /*
    0 = Red Karma Change
    1 = Neutral Karma Change
    2 = Green Karma Change
    */
    private int[] redKarmaChange;
    private int[] neutralKarmaChange;
    private int[] greenKarmaChange;

    private int minRedKarma;
    private int minGreenKarma;
    private int[] rangeNeutralKarma;

    public void initKarmaConfig(FileConfiguration config_instance){
        config = config_instance;
        prefix = ChatColor.translateAlternateColorCodes('&', config.getString("prefix")) + " ";
        defaultKarma = config.getInt("defaultkarma");
        yellowEnabled = config.getBoolean("yellowplayers.enabled");
        yellowTime = config.getInt("yellowplayers.duration");
        maxKarma = config.getInt("maxkarmalevels.max");
        minKarma = config.getInt("maxkarmalevels.min");
        maxMinEnabled = config.getBoolean("maxkarmalevels.enabled");

        loadRanges();
        loadKarmaChages();
    }

    private void loadKarmaChages(){
        redKarmaChange = new int[]{config.getInt("karmachanges.red.red"), config.getInt("karmachanges.red.neutral"),
                config.getInt("karmachanges.red.green")};
        System.out.println("(KC) redKarmaChange = " + config.getInt("karmachanges.red.red") + config.getInt("karmachanges.red.neutral") +
                config.getInt("karmachanges.red.green"));
        neutralKarmaChange = new int[]{config.getInt("karmachanges.neutral.red"), config.getInt("karmachanges.neutral.neutral"),
                config.getInt("karmachanges.neutral.green")};
        System.out.println("(KC) neutralKarmaChange = " + config.getInt("karmachanges.neutral.red") + config.getInt("karmachanges.neutral.neutral") +
                config.getInt("karmachanges.neutral.green"));
        greenKarmaChange = new int[]{config.getInt("karmachanges.green.red"), config.getInt("karmachanges.green.neutral"),
                config.getInt("karmachanges.green.green")};
        System.out.println("(KC) greenKarmaChange = " + config.getInt("karmachanges.green.red") + config.getInt("karmachanges.green.neutral") +
                config.getInt("karmachanges.green.green"));
    }

    private void loadRanges(){
        minRedKarma = config.getInt("karmalevels.red");
        minGreenKarma = config.getInt("karmalevels.green");

        String neutralKarma = config.getString("karmalevels.neutral");
        String[] neutralKarmaSplit = neutralKarma.split("~");
        rangeNeutralKarma = new int[]{Integer.parseInt(neutralKarmaSplit[0]), Integer.parseInt(neutralKarmaSplit[1])};
    }

    public int getKarmaChange(KarmaLevel killer, KarmaLevel killed){
        System.out.println("getKarmaChange:");
        System.out.println("killer = " + killer);
        System.out.println("killed = " + killed);
        switch (killer){
            case RED:
                System.out.println("RED");
                switch (killed){
                    case RED:
                        System.out.println("RED");
                        return redKarmaChange[0];
                    case NEUTRAL:
                        System.out.println("NEUTRAL");
                        return redKarmaChange[1];
                    case GREEN:
                        System.out.println("GREEN");
                        return redKarmaChange[2];
                }
            case NEUTRAL:
                System.out.println("NEUTRAL");
                switch (killed){
                    case RED:
                        System.out.println("RED");
                        return neutralKarmaChange[0];
                    case NEUTRAL:
                        System.out.println("NEUTRAL");
                        return neutralKarmaChange[1];
                    case GREEN:
                        System.out.println("GREEN");
                        return neutralKarmaChange[2];
                }
            case GREEN:
                System.out.println("GREEN");
                switch (killed){
                    case RED:
                        System.out.println("RED");
                        return greenKarmaChange[0];
                    case NEUTRAL:
                        System.out.println("NEUTRAL");
                        return greenKarmaChange[1];
                    case GREEN:
                        System.out.println("GREEN");
                        return greenKarmaChange[2];
                }
            default:
                return 0;
        }

    }

    public KarmaLevel getKarmaLevel(int karma){
        System.out.println("(KC) DEBUG: Min Green Karma = " + minGreenKarma);
        System.out.println("(KC) DEBUG: Min Red Karma = " + minGreenKarma);
        System.out.println("(KC) DEBUG: Min Neutral = " + rangeNeutralKarma[0] + " ; Max Neutral = " + rangeNeutralKarma[1]);
        System.out.println("(KC) DEBUG: green calc: " + karma + ">=" + minGreenKarma);
        System.out.println("(KC) DEBUG: red calc: " + karma + "<=" + minRedKarma);
        System.out.println("(KC) DEBUG: neutral calc: " + karma + ">=" + rangeNeutralKarma[0] + "&&" + karma + "<=" + rangeNeutralKarma[1]);
        if (karma <= minRedKarma){
            System.out.println("(KC) Returning Red");
            return KarmaLevel.RED;
        } else if (karma >= minGreenKarma){
            System.out.println("(KC) Returning Green");
            return KarmaLevel.GREEN;
        } else if (karma >= rangeNeutralKarma[0] && karma <= rangeNeutralKarma[1]){
            System.out.println("(KC) Returning Neutral");
            return KarmaLevel.NEUTRAL;
        }
        return null;
    }
}
