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
package net.snailz.karma.listeners;

import net.snailz.karma.config.KarmaConfig;
import net.snailz.karma.user.KarmaLevel;
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
        this.karmaUserManager = karmaUserManager;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerKill(PlayerDeathEvent e){
        if (!(e.getEntity().getKiller() instanceof Player) || e.getEntity().getKiller() == null) {
            return;
        }
        KarmaUser killed = karmaUserManager.getKarmaUser(e.getEntity().getUniqueId());
        KarmaUser killer = karmaUserManager.getKarmaUser(e.getEntity().getKiller().getUniqueId());
        if (killed.getDisplayKarmaLevel() == KarmaLevel.YELLOW) {
            return;
        }
        int karmaChange = KarmaConfig.getInstance().getKarmaChange(killer.getKarmaLevel(), killed.getKarmaLevel());
        killer.changeKarma(karmaChange, killed);
    }
}
