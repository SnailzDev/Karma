package net.snailz.karma.config;

import net.snailz.karma.user.KarmaLevel;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;


public class KarmaConfig {

    public static String prefix;

    public static int defaultKarma;

    public static ArrayList<Integer> redKarma;
    public static ArrayList<Integer> neutralKarma;
    public static ArrayList<Integer> greenKarma;

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
        //Gets String From Config
        String redRange = config.getString("karmaranges.red");
        String neutralRange = config.getString("karmaranges.neutral");
        String greenRange = config.getString("karmaranges.green");

        //Splits String with ~ and turns in to list of integers
        String[] redValuesStr = redRange.split("~");
        int[] redValues = {Integer.parseInt(redValuesStr[0]), Integer.parseInt(redValuesStr[1])};
        String[] neutralValuesStr = neutralRange.split("~");
        int[] neutralValues = {Integer.parseInt(neutralValuesStr[0]), Integer.parseInt(neutralValuesStr[1])};
        String[] greenValuesStr = greenRange.split("~");
        int[] greenValues = {Integer.parseInt(greenValuesStr[0]), Integer.parseInt(greenValuesStr[1])};

        //Loops through list untill it gets to the greater value and adds all values to Array
        int r;
        ArrayList<Integer> redKarmaValues = new ArrayList<>();
        for (r=redValues[0]; r<=redValues[1]; r++){
            redKarmaValues.add(r);
        }

        int n;
        ArrayList<Integer> neutralKarmaValues = new ArrayList<>();
        for (n=neutralValues[0]; n<=neutralValues[1]; n++){
            neutralKarmaValues.add(n);
        }

        int g;
        ArrayList<Integer> greenKarmaValues = new ArrayList<>();
        for (g=greenValues[0]; g<=greenValues[1]; g++){
            greenKarmaValues.add(g);
        }

        System.out.println(greenKarmaValues.toString());
        System.out.println(neutralKarmaValues.toString());
        System.out.println(redKarmaValues.toString());

        //Adds values to static arrays
        redKarma = redKarmaValues;
        neutralKarma = neutralKarmaValues;
        greenKarma = greenKarmaValues;

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
}
