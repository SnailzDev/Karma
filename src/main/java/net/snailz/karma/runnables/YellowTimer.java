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
        System.out.println("(YT) YellowTime = " + yellowTime);
        if (karmaUser == null) {
            System.out.println("(YT) KarmaUser == null");
        }
        players.add(karmaUser.getPlayer().getUniqueId());
        karmaUser.setYellow();
    }
    @Override
    public void run() {
        for (UUID player : players){
            System.out.println("(YT) [RUN] " + player);
            System.out.println("(YT) [RUN] player time = " + playerTimes.get(player));
            playerTimes.put(player, playerTimes.get(player) - 1);
            System.out.println("(YT) [RUN] new player time = " + playerTimes.get(player));
            if (playerTimes.get(player) == 0){
                System.out.println("(YT) [RUN] player time is 0. Adding player to playersToRemove");
                playersToRemove.add(player);
            }
        }
        //Use another loop to remove because removing in the first loop throws ConcurrentModificationException
        for (UUID player : playersToRemove) {
            System.out.println("(YT) Removing " + player);
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
