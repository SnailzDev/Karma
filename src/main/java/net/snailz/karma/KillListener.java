package net.snailz.karma;

import net.snailz.karma.config.KarmaConfig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class KillListener implements Listener{

    KarmaUserManager karmaUserManager;

    public KillListener(KarmaUserManager karmaUserManager){
        this.karmaUserManager = karmaUserManager;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerKill(PlayerDeathEvent e){
        KarmaUser killed = karmaUserManager.getKarmaUser(e.getEntity().getUniqueId());
        KarmaUser killer = karmaUserManager.getKarmaUser(e.getEntity().getKiller().getUniqueId());
        int karmaChange = KarmaConfig.getKarmaChange(killer.getKarmaLevel(), killed.getKarmaLevel());
        killer.changeKarma(karmaChange, killed);
    }
}
