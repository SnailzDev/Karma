package net.snailz.karma;

import net.snailz.karma.api.KarmaKillChangeEvent;
import net.snailz.karma.api.KarmaLevelChangeEvent;
import net.snailz.karma.config.KarmaConfig;
import net.snailz.karma.config.Messages;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

//Wrapper for Player
public class KarmaUser {

    private KarmaLevel karmaLevel;
    private int karma;
    private Player player;

    public static KarmaLevel karmaToKarmaLevel(int karma){
        //Logic Here
        throw new UnsupportedOperationException("Karma Level to Karma Not Added!");
    }

    public static int karmaLevelToKarma(KarmaLevel karmaLevel){
        if(karmaLevel == KarmaLevel.YELLOW){
            return 0;
        }
        //Logic Here
        throw new UnsupportedOperationException("Karma To Karma Level Not Added!");
    }


    public KarmaUser(Player player, int karma){
        this.player = player;
        this.karma = karma;
        this.karmaLevel = karmaToKarmaLevel(karma);
    }

    public Player getPlayer() {
        return player;
    }

    public KarmaLevel getKarmaLevel() {
        return karmaLevel;
    }

    public int getKarma(){
        return karma;
    }

    public void removeKarma(int karma){
        this.karma = this.karma - karma;
        this.updateKarmaLevel();
    }

    public void addKarma(int karma){
        this.karma = this.karma + karma;
        this.updateKarmaLevel();
    }

    public void setKarma(int karma){
        this.karma = karma;
        this.updateKarmaLevel();
    }

    public void changeKarma(int karma, KarmaUser killed){
        int oldKarma = karma;
        this.karma = this.karma + karma;
        KarmaKillChangeEvent karmaKillChangeEvent = new KarmaKillChangeEvent(killed, this, oldKarma);
        Bukkit.getPluginManager().callEvent(karmaKillChangeEvent);
        //Checking if it was canceled before send messages. I don't know much about the Event API so this may be wrong
        if (!karmaKillChangeEvent.isCancelled()){
            if (karma > oldKarma) {
                this.getPlayer().sendMessage(KarmaConfig.prefix + Messages.getKarmaGain(karma));
            } else if (karma < oldKarma){
                this.getPlayer().sendMessage(KarmaConfig.prefix + Messages.getKarmaLoss(karma));
            }
            updateKarmaLevel();
        }
    }

    public void setKarmaLevel(KarmaLevel karmaLevel){
        Bukkit.getPluginManager().callEvent(new KarmaLevelChangeEvent(this, karmaLevel));
        this.karmaLevel = karmaLevel;
    }

    private void updateKarmaLevel(){
        if (KarmaConfig.greenKarma.contains(karma) && karmaLevel != KarmaLevel.GREEN){
            Bukkit.getPluginManager().callEvent(new KarmaLevelChangeEvent(this, karmaLevel));
            karmaLevel = KarmaLevel.GREEN;
        } else if (KarmaConfig.neutralKarma.contains(karma) && karmaLevel != KarmaLevel.NEUTRAL){
            Bukkit.getPluginManager().callEvent(new KarmaLevelChangeEvent(this, karmaLevel));
            karmaLevel = KarmaLevel.NEUTRAL;
        } else if (KarmaConfig.redKarma.contains(karma) && karmaLevel != KarmaLevel.RED){
            Bukkit.getPluginManager().callEvent(new KarmaLevelChangeEvent(this, karmaLevel));
            karmaLevel = KarmaLevel.RED;
        }
    }
}
