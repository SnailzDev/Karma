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

import net.snailz.karma.Karma;
import net.snailz.karma.api.KarmaKillChangeEvent;
import net.snailz.karma.api.KarmaLevelChangeEvent;
import net.snailz.karma.config.KarmaConfig;
import net.snailz.karma.config.Messages;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

//Wrapper for Player
public class KarmaUser{

    private KarmaLevel karmaLevel;
    private KarmaLevel oldKarmaLevel;
    private int karma;
    private Player player;

    private String prefix;

    public KarmaUser(Player player, int karma) {
        this.player = player;
        this.karma = karma;
        this.karmaLevel = KarmaConfig.getInstance().getKarmaLevel(karma);
        updateScoreBoard();
    }


    public Player getPlayer() {
        return player;
    }

    public KarmaLevel getKarmaLevel() {
        return (karmaLevel == KarmaLevel.YELLOW) ? oldKarmaLevel : karmaLevel;
    }

    public KarmaLevel getDisplayKarmaLevel() {
        return karmaLevel;
    }

    public int getKarma(){
        return karma;
    }

    public void removeKarma(int karma){
        this.karma = this.karma - karma;
        if (KarmaConfig.getInstance().maxMinEnabled) {
            if (this.karma >= KarmaConfig.getInstance().maxKarma) {
                this.karma = KarmaConfig.getInstance().maxKarma;
            } else if (this.karma <= KarmaConfig.getInstance().minKarma) {
                this.karma = KarmaConfig.getInstance().minKarma;
            }
        }
        this.updateKarmaLevel();

    }

    public void addKarma(int karma) {
        this.karma = this.karma + karma;
        if (KarmaConfig.getInstance().maxMinEnabled) {
            if (this.karma >= KarmaConfig.getInstance().maxKarma) {
                this.karma = KarmaConfig.getInstance().maxKarma;
            } else if (this.karma <= KarmaConfig.getInstance().minKarma) {
                this.karma = KarmaConfig.getInstance().minKarma;
            }
        }
        this.updateKarmaLevel();
    }

    public void setKarma(int karma){
        this.karma = karma;
        this.updateKarmaLevel();
    }

    public void changeKarma(int karma, KarmaUser killed){
        int oldKarma = karma;
        this.karma = this.karma + karma;
        KarmaKillChangeEvent karmaKillChangeEvent = new KarmaKillChangeEvent(killed, this, oldKarma);
        Bukkit.getPluginManager().callEvent(karmaKillChangeEvent);
        //Checking if it was canceled before send messages. I don't know much about the Event API so this may be wrong
        if (!karmaKillChangeEvent.isCancelled()){
            if (karma > oldKarma) {
                this.getPlayer().sendMessage(prefix + Messages.getInstance().getKarmaGain(karma));
            } else if (karma < oldKarma){
                this.getPlayer().sendMessage(prefix + Messages.getInstance().getKarmaLoss(karma));
            }
            updateKarmaLevel();
        }
    }

    public void setKarmaLevel(KarmaLevel karmaLevel){
        this.karmaLevel = karmaLevel;
        Bukkit.getPluginManager().callEvent(new KarmaLevelChangeEvent(this, karmaLevel));
        updateScoreBoard();
    }
    private void updateKarmaLevel(){
        boolean hasUpdated = false;
        KarmaLevel oldKarmaLevel = karmaLevel;
        switch (KarmaConfig.getInstance().getKarmaLevel(karma)){
            case GREEN:
                if (karmaLevel != KarmaLevel.GREEN){
                    hasUpdated = true;
                    karmaLevel = KarmaLevel.GREEN;
                }
                break;
            case NEUTRAL:
                if (karmaLevel != KarmaLevel.NEUTRAL){
                    hasUpdated = true;
                    karmaLevel = KarmaLevel.NEUTRAL;
                }
                break;
            case RED:
                if (karmaLevel != KarmaLevel.RED){
                    hasUpdated = true;
                    karmaLevel = KarmaLevel.RED;
                }
                break;
        }
        if (hasUpdated){
            updateScoreBoard();
            Bukkit.getPluginManager().callEvent(new KarmaLevelChangeEvent(this, oldKarmaLevel));
        }
    }

    private void updateScoreBoard(){
        Karma.karmaScoreboard.setScore(this);
    }

    public void setYellow(){
        if (karmaLevel != KarmaLevel.YELLOW) {
            oldKarmaLevel = karmaLevel;
            setKarmaLevel(KarmaLevel.YELLOW);
        }
    }

    public void removeYellow() {
        setKarmaLevel(oldKarmaLevel);
        updateScoreBoard();
    }

}
