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
package net.snailz.karma.user;

import net.snailz.karma.config.KarmaConfig;
import net.snailz.karma.data.DataStorage;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class KarmaUserManager {

    private DataStorage dataStorage;

    private HashMap<UUID, KarmaUser> karmaUsersMap = new HashMap<>();
    private ArrayList<KarmaUser> karmaUsers = new ArrayList<>();

    public KarmaUserManager(DataStorage dataStorage){
        this.dataStorage = dataStorage;
    }

    public void addKarmaUser(KarmaUser karmaUser){
        karmaUsers.add(karmaUser);
        karmaUsersMap.put(karmaUser.getPlayer().getUniqueId(), karmaUser);
    }

    public void addKarmaUser(Player player){
        addKarmaUser(dataStorage.deSterilize(player.getUniqueId()));
    }

    public void addNewKarmaUser(Player player){
        KarmaUser karmaUser = new KarmaUser(player, KarmaConfig.getInstance().defaultKarma);
        karmaUsers.add(karmaUser);
        karmaUsersMap.put(player.getUniqueId(), karmaUser);
        dataStorage.addNewKarmaUser(karmaUser);
    }

    public void removeKarmaUser(KarmaUser karmaUser){
        karmaUsers.remove(karmaUser);
        karmaUsersMap.remove(karmaUser);
    }

    public void removeKarmaUser(Player player){
        removeKarmaUser(karmaUsersMap.get(player.getUniqueId()));
    }

    public void sterilizeKarmaUser(KarmaUser karmaUser){
        dataStorage.sterilize(karmaUser);
    }

    public void sterilizeKarmaUser(Player player){
        sterilizeKarmaUser(karmaUsersMap.get(player.getUniqueId()));
    }

    public KarmaUser getKarmaUser(UUID uuid){
        return karmaUsersMap.get(uuid);
    }

    public KarmaUser getKarmaUser(Player player){
        return karmaUsersMap.get(player.getUniqueId());
    }

    public void saveAllKarma(){
        for (KarmaUser karmaUser : karmaUsers){
            dataStorage.sterilize(karmaUser);
        }
    }
}
