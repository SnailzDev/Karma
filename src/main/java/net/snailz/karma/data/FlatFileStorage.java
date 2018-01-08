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
package net.snailz.karma.data;

import net.snailz.karma.config.CustomConfig;
import net.snailz.karma.Karma;
import net.snailz.karma.user.KarmaUser;
import net.snailz.karma.config.KarmaConfig;
import org.bukkit.Bukkit;

import java.util.UUID;

public class FlatFileStorage implements DataStorage{

    Karma plugin;
    CustomConfig storageFile;

    public FlatFileStorage(Karma plugin){
        this.plugin = plugin;
        this.storageFile = new CustomConfig("karma", plugin);
        storageFile.saveDefaultCustomFile();
    }

    @Override
    public void sterilize(KarmaUser user) {
        storageFile.getCustomFile().set(user.getPlayer().getUniqueId().toString(), user.getKarma());
        storageFile.saveCustomFile();
    }

    @Override
    public KarmaUser deSterilize(UUID uuid) {
        KarmaUser karmaUser;
        int karma = storageFile.getCustomFile().getInt(uuid.toString());
        karmaUser = new KarmaUser(Bukkit.getPlayer(uuid), karma);
        return karmaUser;
    }

    @Override
    public void addNewKarmaUser(KarmaUser karmaUser) {
        storageFile.getCustomFile().set(karmaUser.getPlayer().getUniqueId().toString(), KarmaConfig.getInstance().defaultKarma);
    }

    @Override
    public String getStorageMethod() {
        return "FlatFile";
    }
}
