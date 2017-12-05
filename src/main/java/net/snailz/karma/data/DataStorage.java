package net.snailz.karma.data;

import net.snailz.karma.KarmaUser;

public interface DataStorage {

    void sterilize(KarmaUser user);

    KarmaUser deSterilize(String uuid);

    String getStorageMethod();
}
