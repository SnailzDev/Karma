package net.snailz.karma;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener {

    KarmaUserManager karmaUserManager;

    public DamageListener(KarmaUserManager karmaUserManager){
        this.karmaUserManager = karmaUserManager;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerDamage(EntityDamageEvent e){
        KarmaUser killer = karmaUserManager.getKarmaUser(((Player) e.getEntity()).getUniqueId());

    }
}
