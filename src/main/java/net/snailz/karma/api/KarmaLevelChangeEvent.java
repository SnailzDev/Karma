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
