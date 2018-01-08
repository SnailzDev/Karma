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
package net.snailz.karma.runnables;

import net.snailz.karma.config.KarmaConfig;
import net.snailz.karma.user.KarmaLevel;
import net.snailz.karma.user.KarmaUser;
import net.snailz.karma.user.KarmaUserManager;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.TreeSet;
import java.util.UUID;

public class YellowTimer extends BukkitRunnable{

    private KarmaUserManager karmaUserManager;

    private HashMap<UUID, Integer> playerTimes;
    private TreeSet<UUID> players;
    private TreeSet<UUID> playersToRemove;
    private int yellowTime;

    public YellowTimer(KarmaUserManager karmaUserManager){
        this.karmaUserManager = karmaUserManager;
        playerTimes = new HashMap<>();
        players = new TreeSet<>();
        playersToRemove = new TreeSet<>();
        yellowTime = KarmaConfig.getInstance().yellowTime;
    }

    public void addKarmaUser(KarmaUser karmaUser){
        playerTimes.put(karmaUser.getPlayer().getUniqueId(), yellowTime);
        if (karmaUser == null) {
        }
        players.add(karmaUser.getPlayer().getUniqueId());
        karmaUser.setYellow();
    }
    @Override
    public void run() {
        for (UUID player : players){
            playerTimes.put(player, playerTimes.get(player) - 1);
            if (playerTimes.get(player) == 0){
                playersToRemove.add(player);
            }
        }
        //Use another loop to remove because removing in the first loop throws ConcurrentModificationException
        for (UUID player : playersToRemove) {
            //Checks if player is still yellow because when they kill a player they are no longer yellow and might have a new karma level
            if (karmaUserManager.getKarmaUser(player).getDisplayKarmaLevel() == KarmaLevel.YELLOW) {
                karmaUserManager.getKarmaUser(player).removeYellow();
            }
            players.remove(player);
            playerTimes.remove(player);
        }
        playersToRemove.clear();

    }
}
