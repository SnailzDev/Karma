package net.snailz.karma;

import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeaveListeners implements Listener{

    private KarmaUserManager karmaUserManager;

    public JoinLeaveListeners(KarmaUserManager karmaUserManager){
        this.karmaUserManager = karmaUserManager;
    }

    public void onPlayerJoin(PlayerJoinEvent e){
        karmaUserManager.addNewKarmaUser(e.getPlayer());
    }

    public void onPlayerLeave(PlayerQuitEvent e){
        karmaUserManager.sterilizeNewKarmaUser(e.getPlayer());
    }
}
