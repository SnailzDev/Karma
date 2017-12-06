package net.snailz.karma.api;

import net.snailz.karma.KarmaUser;
import net.snailz.karma.data.DataStorage;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class KarmaChangeEvent extends Event implements Cancellable{

    private DataStorage dataStorage;

    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;

    private KarmaUser killed;
    private KarmaUser killer;

    //For Cancellable
    private int killedOldKarma;
    private int killerOldKarma;

    public KarmaChangeEvent(DataStorage dataStorage, KarmaUser killed, KarmaUser killer, int killedOldKarma, int killerOldKarma){
        this.dataStorage = dataStorage;
        this.killed = killed;
        this.killer = killer;
        this.killedOldKarma = killedOldKarma;
        this.killerOldKarma = killerOldKarma;
    }

    @Override
    public HandlerList getHandlers() {
        return null;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        cancelled = b;
        //Cancelling does not trigger a new KarmaChangeEvent
        killed.setKarma(killedOldKarma);
        killer.setKarma(killerOldKarma);
    }

    public KarmaUser getKilled() {
        return killed;
    }

    public KarmaUser getKiller() {
        return killer;
    }

    public int getKilledKarmaChange(){
        return killedOldKarma - killed.getKarma();
    }

    public int getKillerOldKarma(){
        return killerOldKarma - killer.getKarma();
    }
}
