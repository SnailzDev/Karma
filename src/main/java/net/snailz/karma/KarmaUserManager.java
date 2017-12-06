package net.snailz.karma;

import net.snailz.karma.data.DataStorage;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.TreeSet;
import java.util.UUID;

public class KarmaUserManager {

    private DataStorage dataStorage;

    private HashMap<UUID, KarmaUser> karmaUsersMap = new HashMap<>();
    private TreeSet<KarmaUser> karmaUsers = new TreeSet<>();

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

    public void saveAllKarma(){
        for (KarmaUser karmaUser : karmaUsers){
            dataStorage.sterilize(karmaUser);
        }
    }
}
