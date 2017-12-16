package net.snailz.karma.api;

import net.snailz.karma.user.KarmaLevel;
import net.snailz.karma.user.KarmaUser;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class KarmaLevelChangeEvent extends Event{

    private static final HandlerList handlers = new HandlerList();

    private KarmaUser karmaUser;

    //For Cancellable
    private KarmaLevel oldKarmaLevel;


    public KarmaLevelChangeEvent(KarmaUser karmaUser, KarmaLevel oldKarmaLevel){
        this.karmaUser = karmaUser;
        this.oldKarmaLevel = oldKarmaLevel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }


    public KarmaUser getKarmaUser(){
        return karmaUser;
    }

    public KarmaLevel getOldKarmaLevel(){
        return oldKarmaLevel;
    }
}
