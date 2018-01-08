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
package net.snailz.karma.scoreboard;

import net.snailz.karma.Karma;

public class KarmaScoreboardManager {

    private Karma plugin;
    private KarmaScoreboard karmaScoreboard;

    public KarmaScoreboardManager(Karma plugin){
        this.plugin = plugin;
    }

    public KarmaScoreboard getScoreboard(){
        if (plugin.getConfig().getString("scoreboardmethod").equalsIgnoreCase("vanilla")){
            karmaScoreboard = new VanillaScoreboard(plugin.getConfig());
            return karmaScoreboard;
        } else if (plugin.getConfig().getString("scoreboardmethod").equalsIgnoreCase("vault")){
            karmaScoreboard = new VaultScoreboard();
            return karmaScoreboard;
        }
        //No Valid Scoreboard Set
        plugin.getConfig().set("scoreboardmethod", "vanilla");
        karmaScoreboard = new VanillaScoreboard(plugin.getConfig());
        return karmaScoreboard;
    }


}
