package net.snailz.karma.config;


import org.bukkit.ChatColor;

public class Messages {
    private static String karmaResetSender;
    private static String karmaResetReceiver;

    public static void initMessages(CustomConfig config){
        karmaResetSender = ChatColor.translateAlternateColorCodes('&', config.getCustomFile().getString("karmareset.sender"));
        karmaResetReceiver = ChatColor.translateAlternateColorCodes('&', config.getCustomFile().getString("karmareset.receiver"));
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

    private static String replacePlayer(String message, String player){
        return message.replace("%player%", player);
    }

    private static String replaceKarma(String message, int karma){
        return message.replace("%karma%", String.valueOf(karma));
    }
}
