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

import net.snailz.karma.Karma;
import net.snailz.karma.config.KarmaConfig;

public class DataStorageManager {

    private Karma plugin;
    private DataStorage dataStorage;

    public DataStorageManager(Karma plugin){
        this.plugin = plugin;
    }

    public DataStorage getDataStorage(){
        String storageMethod = plugin.getConfig().getString("storagemethod");
        if (storageMethod.equalsIgnoreCase("file")){
            dataStorage = new FlatFileStorage(plugin);
            return dataStorage;
        }
        if (storageMethod.equalsIgnoreCase("mysql")){
            dataStorage = new MySQLStorage(plugin);
            return dataStorage;
        }
        //There is no valid data storage type specified in config so change it to file and return FlatFileStorage
        plugin.getConfig().set("storagemethod", "file");
        dataStorage = new FlatFileStorage(plugin);
        return dataStorage;
    }
}
