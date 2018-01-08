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
import net.snailz.karma.user.KarmaUserManager;
import org.bukkit.Bukkit;

import java.util.UUID;

public class KarmaAPI {

    private static KarmaAPI instance = new KarmaAPI();

    private KarmaUserManager karmaUserManager;
    private KarmaAPI(){

    }

    public static KarmaAPI getInstance(){
        return instance;
    }

    public void initAPI(KarmaUserManager karmaUserManager){
        this.karmaUserManager = karmaUserManager;
    }

    public KarmaUser getKarmaUser(UUID uuid){
        return karmaUserManager.getKarmaUser(uuid);
    }

    public KarmaUser getKarmaUser(String player){
        return karmaUserManager.getKarmaUser(Bukkit.getPlayer(player).getUniqueId());
    }

}
