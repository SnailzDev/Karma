/*
This file is part of Karma.

Karma is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Karma is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Karma.  If not, see <http://www.gnu.org/licenses/>.
*/
package net.snailz.karma.config;


import net.snailz.karma.user.KarmaLevel;
import org.bukkit.ChatColor;

public class Messages {

    //MAKE SINGLETON

    private static Messages messages = new Messages();

    private Messages(){

    }

    public static Messages getInstance(){
        return messages;
    }


    private String karmaResetSender;
    private String karmaResetReceiver;
    private String karmaAddSender;
    private String karmaAddReceiver;
    private String karmaRemoveSender;
    private String karmaRemoveReceiver;
    
    private String illegalKarmaAmmount;
    
    private String karmaLoss;
    private String karmaGain;
    private String karmaLevelChange;

    public void initMessages(CustomConfig config){
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

    public String getKarmaResetSender(String player, int karma){
        String message = replacePlayer(karmaResetSender, player);
        message = replaceKarma(message, karma);
        return message;
    }

    public String getKarmaResetReceiver(String player, int karma){
        String message = replacePlayer(karmaResetReceiver, player);
        message = replaceKarma(message, karma);
        return message;
    }

    public String getKarmaAddSender(String player, int karma){
        String message = replacePlayer(karmaAddSender, player);
        message = replaceKarma(message, karma);
        return message;
    }

    public String getKarmaAddReceiver(String player, int karma){
        String message = replacePlayer(karmaAddReceiver, player);
        message = replaceKarma(message, karma);
        return message;
    }

    public String getKarmaRemoveSender(String player, int karma){
        String message = replacePlayer(karmaRemoveSender, player);
        message = replaceKarma(message, karma);
        return message;
    }

    public String getKarmaRemoveReceiver(String player, int karma) {
        String message = replacePlayer(karmaRemoveReceiver, player);
        message = replaceKarma(message, karma);
        return message;
    }

    public String getIllegalKarmaAmmount(){
        return illegalKarmaAmmount;
    }

    public String getKarmaLoss(int karma){
        return replaceKarma(karmaLoss, karma);
    }

    public String getKarmaGain(int karma){
        return replaceKarma(karmaGain, karma);
    }

    public String getKarmaLevelChange(KarmaLevel karmaLevel){
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

    private String replacePlayer(String message, String player){
        if (!message.contains("%player%")){
            return message;
        }
        return message.replace("%player%", player);
    }

    private String replaceKarma(String message, int karma){
        if (!message.contains("%karma%")){
            return message;
        }
        return message.replace("%karma%", String.valueOf(karma));
    }
}
