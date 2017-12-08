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
