package net.snailz.karma;

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
    }

    public void addKarma(int karma){
        this.karma = this.karma + karma;
    }

    public void setKarma(int karma){
        this.karma = karma;
    }
}
