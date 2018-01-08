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
        return handlers;
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
