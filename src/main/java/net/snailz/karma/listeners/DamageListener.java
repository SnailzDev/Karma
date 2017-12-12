package net.snailz.karma.listeners;

import net.snailz.karma.runnables.YellowTimer;
import net.snailz.karma.user.KarmaUser;
import net.snailz.karma.user.KarmaUserManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener {

    KarmaUserManager karmaUserManager;
    YellowTimer yellowTimer;

    public DamageListener(KarmaUserManager karmaUserManager, YellowTimer yellowTimer){
        this.karmaUserManager = karmaUserManager;
        this.yellowTimer = yellowTimer;
    }


    //Yet To Be Implemented
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerDamage(EntityDamageEvent e){
        KarmaUser killer = karmaUserManager.getKarmaUser(((Player) e.getEntity()).getUniqueId());
        yellowTimer.addKarmaUser(killer);
    }
}
