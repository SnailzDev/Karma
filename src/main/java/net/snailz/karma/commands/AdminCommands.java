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
package net.snailz.karma.commands;

import net.snailz.karma.config.KarmaConfig;
import net.snailz.karma.config.Messages;
import net.snailz.karma.user.KarmaUser;
import net.snailz.karma.user.KarmaUserManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminCommands implements CommandExecutor{

    KarmaUserManager karmaUserManager;
    KarmaConfig karmaConfig;
    Messages messages;

    public AdminCommands(KarmaUserManager karmaUserManager){
        this.karmaUserManager = karmaUserManager;
        this.karmaConfig = KarmaConfig.getInstance();
        this.messages = Messages.getInstance();
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("karma")){
            if (args.length <= 2 || args[0].equalsIgnoreCase("help")){
                showHelpMenu(commandSender);
                return true;
            }
            if (args[0].equalsIgnoreCase("reset")){
                String playerName = args[1];
                Player player = Bukkit.getPlayer(playerName);
                if (player == null){
                    showHelpMenu(commandSender);
                    return true;
                }
                karmaUserManager.getKarmaUser(player).setKarma(karmaConfig.defaultKarma);
                commandSender.sendMessage(karmaConfig.prefix + messages.getKarmaResetSender(player.getDisplayName(), karmaConfig.defaultKarma));
                player.sendMessage(karmaConfig.prefix + messages.getKarmaResetReceiver(player.getDisplayName(), karmaConfig.defaultKarma));
                return true;
            } else if (args[0].equalsIgnoreCase("add")){
                String playerName = args[1];
                Player player = Bukkit.getPlayer(playerName);
                if (player == null){
                    showHelpMenu(commandSender);
                    return true;
                }
                KarmaUser karmaUser = karmaUserManager.getKarmaUser(player);
                int karma = 0;
                try {
                    karma = Integer.parseInt(args[2]);
                } catch (IllegalArgumentException e){
                    showIllegalKarma(commandSender);
                    return true;
                }
                commandSender.sendMessage(karmaConfig.prefix + messages.getKarmaAddSender(player.getDisplayName(), karma));
                player.sendMessage(karmaConfig.prefix + messages.getKarmaAddReceiver(player.getDisplayName(), karma));
                karmaUser.addKarma(karma);
                return true;
            } else if (args[0].equalsIgnoreCase("remove")){
                String playerName = args[1];
                Player player = Bukkit.getPlayer(playerName);
                if (player == null){
                    showHelpMenu(commandSender);
                    return true;
                }
                KarmaUser karmaUser = karmaUserManager.getKarmaUser(player);
                int karma = 0;
                try {
                    karma = Integer.parseInt(args[2]);
                } catch (IllegalArgumentException e){
                    showIllegalKarma(commandSender);
                    return true;
                }
                karmaUser.removeKarma(karma);
                commandSender.sendMessage(karmaConfig.prefix + messages.getKarmaRemoveSender(player.getDisplayName(), karma));
                player.sendMessage(karmaConfig.prefix + messages.getKarmaRemoveReceiver(player.getDisplayName(), karma));
                return true;
            }
        }
        return false;
    }

    private void showIllegalKarma(CommandSender sender){
        sender.sendMessage(karmaConfig.prefix + messages.getIllegalKarmaAmmount());
    }
    private void showHelpMenu(CommandSender sender){
        sender.sendMessage(KarmaConfig.getInstance().prefix + ChatColor.GOLD + "/karma <add/remove> <name> <number>");
    }
}
