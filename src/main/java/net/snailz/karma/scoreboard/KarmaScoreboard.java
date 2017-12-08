package net.snailz.karma.scoreboard;

import net.snailz.karma.user.KarmaLevel;
import net.snailz.karma.user.KarmaUser;

public interface KarmaScoreboard {

    void setScore(KarmaUser karmaUser);

    void clearAllScores();

    void clearScore(KarmaUser karmaUser);

    String getScoreboardMethod();
}
