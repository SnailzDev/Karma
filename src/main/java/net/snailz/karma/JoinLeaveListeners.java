package net.snailz.karma;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeaveListeners implements Listener{

    private KarmaUserManager karmaUserManager;

    public JoinLeaveListeners(KarmaUserManager karmaUserManager){
        this.karmaUserManager = karmaUserManager;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoin(PlayerJoinEvent e){
        if (!e.getPlayer().hasPlayedBefore()){
            karmaUserManager.addNewKarmaUser(e.getPlayer());
        } else {
            karmaUserManager.addKarmaUser(e.getPlayer());
        }
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerLeave(PlayerQuitEvent e){
        karmaUserManager.sterilizeKarmaUser(e.getPlayer());
        karmaUserManager.removeKarmaUser(e.getPlayer());
    }
}
