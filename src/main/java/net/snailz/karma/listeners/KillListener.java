package net.snailz.karma.listeners;

import net.snailz.karma.config.KarmaConfig;
import net.snailz.karma.user.KarmaUser;
import net.snailz.karma.user.KarmaUserManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class KillListener implements Listener{

    private KarmaUserManager karmaUserManager;

    public KillListener(KarmaUserManager karmaUserManager){
        System.out.println("(KL) Constructor running!");
        this.karmaUserManager = karmaUserManager;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerKill(PlayerDeathEvent e){
        System.out.println("(KL) PlayerDeathEvent triggered!");
        if (!(e.getEntity().getKiller() instanceof Player) || e.getEntity().getKiller() == null) {
            return;
        }
        KarmaUser killed = karmaUserManager.getKarmaUser(e.getEntity().getUniqueId());
        KarmaUser killer = karmaUserManager.getKarmaUser(e.getEntity().getKiller().getUniqueId());
        int karmaChange = KarmaConfig.getInstance().getKarmaChange(killer.getKarmaLevel(), killed.getKarmaLevel());
        System.out.println("(KL) karma change = " + karmaChange);
        killer.changeKarma(karmaChange, killed);
    }
}
