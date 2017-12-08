package net.snailz.karma.data;

import net.snailz.karma.user.KarmaUser;

import java.util.UUID;

public interface DataStorage {

    void sterilize(KarmaUser user);

    KarmaUser deSterilize(UUID uuid);

    void addNewKarmaUser(KarmaUser karmaUser);

    String getStorageMethod();
}
