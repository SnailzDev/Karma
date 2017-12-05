package net.snailz.karma.data;

import net.snailz.karma.Karma;
import net.snailz.karma.KarmaUser;

import java.util.UUID;

public class FlatFileStorage implements DataStorage{

    Karma plugin;
    CustomConfig storageFile;

    public FlatFileStorage(Karma plugin){
        this.plugin = plugin;
        this.storageFile = new CustomConfig("players", plugin);
    }

    @Override
    public void sterilize(KarmaUser user) {

    }

    @Override
    public KarmaUser deSterilize(UUID uuid) {
        return null;
    }

    @Override
    public String getStorageMethod() {
        return "FlatFile";
    }
}
