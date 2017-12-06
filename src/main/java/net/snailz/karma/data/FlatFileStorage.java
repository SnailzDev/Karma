package net.snailz.karma.data;

import net.snailz.karma.Karma;
import net.snailz.karma.KarmaUser;
import org.bukkit.Bukkit;

import java.util.UUID;

public class FlatFileStorage implements DataStorage{

    Karma plugin;
    CustomConfig storageFile;

    public FlatFileStorage(Karma plugin){
        this.plugin = plugin;
        this.storageFile = new CustomConfig("karma", plugin);
    }

    @Override
    public void sterilize(KarmaUser user) {
        storageFile.getCustomFile().set(user.getPlayer().getUniqueId().toString(), user.getKarma());
    }

    @Override
    public KarmaUser deSterilize(UUID uuid) {
        KarmaUser karmaUser;
        int karma = storageFile.getCustomFile().getInt(uuid.toString());
        karmaUser = new KarmaUser(Bukkit.getPlayer(uuid), karma);
        return karmaUser;
    }

    @Override
    public String getStorageMethod() {
        return "FlatFile";
    }
}
