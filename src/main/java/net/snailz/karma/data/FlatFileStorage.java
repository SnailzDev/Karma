package net.snailz.karma.data;

import net.snailz.karma.Karma;
import net.snailz.karma.KarmaUser;

public class FlatFileStorage implements DataStorage{

    Karma plugin;

    public FlatFileStorage(Karma plugin){
        this.plugin = plugin;
    }

    @Override
    public void sterilize(KarmaUser user) {

    }

    @Override
    public KarmaUser deSterilize(String uuid) {
        return null;
    }

    @Override
    public String getStorageMethod() {
        return "FlatFile";
    }
}
