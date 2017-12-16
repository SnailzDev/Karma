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
    private HashMap<UUID, KarmaLevel> oldKarmaLevel;
    private TreeSet<UUID> players;

    public YellowTimer(KarmaUserManager karmaUserManager){
        this.karmaUserManager = karmaUserManager;
        playerTimes = new HashMap<>();
        players = new TreeSet<>();
    }

    public void addKarmaUser(KarmaUser karmaUser){
        playerTimes.put(karmaUser.getPlayer().getUniqueId(), KarmaConfig.yellowTime);
        oldKarmaLevel.put(karmaUser.getPlayer().getUniqueId(), karmaUser.getKarmaLevel());
        karmaUser.setYellow();
    }
    @Override
    public void run() {
        if (players.isEmpty()){
            return;
        }
        for (UUID player : players){
            playerTimes.put(player, playerTimes.get(player) - 1);
            if (playerTimes.get(player) == 0){
                karmaUserManager.getKarmaUser(player).setKarmaLevel(oldKarmaLevel.get(player));
                oldKarmaLevel.remove(player);
                players.remove(player);
                playerTimes.remove(player);
            }
        }

    }
}
