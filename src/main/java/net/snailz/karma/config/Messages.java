package net.snailz.karma.config;


import net.snailz.karma.user.KarmaLevel;
import org.bukkit.ChatColor;

public class Messages {
    private static String karmaResetSender;
    private static String karmaResetReceiver;
    private static String karmaAddSender;
    private static String karmaAddReceiver;
    private static String karmaRemoveSender;
    private static String karmaRemoveReceiver;
    
    private static String illegalKarmaAmmount;
    
    private static String karmaLoss;
    private static String karmaGain;
    private static String karmaLevelChange;

    public static void initMessages(CustomConfig config){
        karmaResetSender = ChatColor.translateAlternateColorCodes('&', config.getCustomFile().getString("karmareset.sender"));
        karmaResetReceiver = ChatColor.translateAlternateColorCodes('&', config.getCustomFile().getString("karmareset.receiver"));
        karmaAddSender = ChatColor.translateAlternateColorCodes('&', config.getCustomFile().getString("karmaadd.sender"));
        karmaAddReceiver = ChatColor.translateAlternateColorCodes('&', config.getCustomFile().getString("karmaadd.receiver"));
        karmaRemoveSender = ChatColor.translateAlternateColorCodes('&', config.getCustomFile().getString("karmaremove.sender"));
        karmaRemoveReceiver = ChatColor.translateAlternateColorCodes('&', config.getCustomFile().getString("karmaremove.receiver"));

        illegalKarmaAmmount = ChatColor.translateAlternateColorCodes('&', config.getCustomFile().getString("commanderrors.illegalkarmaammount"));

        karmaLoss = ChatColor.translateAlternateColorCodes('&', config.getCustomFile().getString("karmaloss"));
        karmaGain = ChatColor.translateAlternateColorCodes('&', config.getCustomFile().getString("karmagain"));
        karmaLevelChange = ChatColor.translateAlternateColorCodes('&', config.getCustomFile().getString("karmalevelchange"));
    }

    public static String getKarmaResetSender(String player, int karma){
        String message = replacePlayer(karmaResetSender, player);
        message = replaceKarma(message, karma);
        return message;
    }

    public static String getKarmaResetReceiver(String player, int karma){
        String message = replacePlayer(karmaResetReceiver, player);
        message = replaceKarma(message, karma);
        return message;
    }

    public static String getKarmaAddSender(String player, int karma){
        String message = replacePlayer(karmaAddSender, player);
        message = replaceKarma(message, karma);
        return message;
    }

    public static String getKarmaAddReceiver(String player, int karma){
        String message = replacePlayer(karmaAddReceiver, player);
        message = replaceKarma(message, karma);
        return message;
    }

    public static String getKarmaRemoveSender(String player, int karma){
        String message = replacePlayer(karmaRemoveSender, player);
        message = replaceKarma(message, karma);
        return message;
    }

    public static String getKarmaRemoveReceiver(String player, int karma) {
        String message = replacePlayer(karmaRemoveReceiver, player);
        message = replaceKarma(message, karma);
        return message;
    }

    public static String getIllegalKarmaAmmount(){
        return illegalKarmaAmmount;
    }

    public static String getKarmaLoss(int karma){
        return replaceKarma(karmaLoss, karma);
    }

    public static String getKarmaGain(int karma){
        return replaceKarma(karmaGain, karma);
    }

    public static String getKarmaLevelChange(KarmaLevel karmaLevel){
        switch (karmaLevel){
            case RED:
                return karmaLevelChange.replace("%karmalevel%", ChatColor.RED + "RED");
            case GREEN:
                return karmaLevelChange.replace("%karmalevel%", ChatColor.GREEN + "GREEN");
            case NEUTRAL:
                return karmaLevelChange.replace("%karmalevel%", ChatColor.AQUA + "NEUTRAL");
            case YELLOW:
                return karmaLevelChange.replace("%karmalevel%", ChatColor.YELLOW + "YELLOW");
        }
        return null;
    }

    private static String replacePlayer(String message, String player){
        if (!message.contains("%player%")){
            return message;
        }
        return message.replace("%player%", player);
    }

    private static String replaceKarma(String message, int karma){
        if (!message.contains("%karma%")){
            return message;
        }
        return message.replace("%karma%", String.valueOf(karma));
    }
}
