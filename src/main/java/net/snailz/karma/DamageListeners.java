package net.snailz.karma;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DamageListeners implements Listener{

    public void onPlayerKill(PlayerDeathEvent e){
        Player killed = e.getEntity().getPlayer();
        Player killer = e.getEntity();

    }
}
