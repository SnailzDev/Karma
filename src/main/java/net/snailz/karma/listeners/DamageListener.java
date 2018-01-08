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
*/package net.snailz.karma.listeners;

import net.snailz.karma.runnables.YellowTimer;
import net.snailz.karma.user.KarmaLevel;
import net.snailz.karma.user.KarmaUser;
import net.snailz.karma.user.KarmaUserManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamageListener implements Listener {

    private KarmaUserManager karmaUserManager;
    private YellowTimer yellowTimer;

    public DamageListener(KarmaUserManager karmaUserManager, YellowTimer yellowTimer){
        this.karmaUserManager = karmaUserManager;
        this.yellowTimer = yellowTimer;
    }


    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerDamage(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player) || !(e.getEntity() instanceof Player)) {
            //Not player killer. Does it work with arrows, I dont know
            return;
        }
        KarmaUser damaged = karmaUserManager.getKarmaUser((Player) e.getEntity());
        if (damaged.getKarmaLevel() == KarmaLevel.RED) {
            return;
        }
        KarmaUser damager = karmaUserManager.getKarmaUser((Player) e.getDamager());
        yellowTimer.addKarmaUser(damager);
    }
}
