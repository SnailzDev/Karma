package net.snailz.karma.api;

import net.snailz.karma.user.KarmaUser;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class KarmaKillChangeEvent extends Event implements Cancellable{

    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;

    private KarmaUser killed;
    private KarmaUser killer;

    //For Cancellable
    private int killerOldKarma;

    public KarmaKillChangeEvent(KarmaUser killed, KarmaUser killer, int killerOldKarma){
        this.killed = killed;
        this.killer = killer;
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
        //Cancelling does not trigger a new KarmaKillChangeEvent
        killer.setKarma(killerOldKarma);
    }

    public KarmaUser getKilled() {
        return killed;
    }

    public KarmaUser getKiller() {
        return killer;
    }

    public int getKillerKarmaChange(){
        return killerOldKarma - killer.getKarma();
    }

    public int getKillerOldKarma(){
        return killerOldKarma - killer.getKarma();
    }
}
