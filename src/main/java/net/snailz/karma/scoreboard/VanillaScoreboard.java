package net.snailz.karma.scoreboard;

import net.snailz.karma.user.KarmaLevel;
import net.snailz.karma.user.KarmaUser;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class VanillaScoreboard implements KarmaScoreboard{

    private Scoreboard scoreboard;

    private Team greenTeam;
    private Team neutralTeam;
    private Team redTeam;
    private Team yellowTeam;

    private FileConfiguration config;

    public VanillaScoreboard(FileConfiguration config){
        scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        this.config = config;
        initTeams();
    }

    private void initTeams(){
        try {
            System.out.println("DEBUG: Creating New Teams");
            greenTeam = scoreboard.registerNewTeam("karmaGreen");
            neutralTeam = scoreboard.registerNewTeam("karmaNeutral");
            redTeam = scoreboard.registerNewTeam("karmaRed");
            yellowTeam = scoreboard.registerNewTeam("karmaYellow");

            greenTeam.setPrefix(ChatColor.GREEN.toString());
            //Neutral Team Does Not Have Prefix
            redTeam.setPrefix(ChatColor.RED.toString());
            yellowTeam.setPrefix(ChatColor.YELLOW.toString());
        } catch (IllegalArgumentException e) {
            System.out.println("DEBUG: Applying Already Created Teams.");
            greenTeam = scoreboard.getTeam("karmaGreen");
            neutralTeam = scoreboard.getTeam("karmaNeutral");
            redTeam = scoreboard.getTeam("karmaRed");
            yellowTeam = scoreboard.getTeam("karmaYellow");
        }
    }

    @Override
    public void removeTeams(){
        greenTeam.unregister();
        neutralTeam.unregister();
        redTeam.unregister();
        yellowTeam.unregister();
    }

    @Override
    public void setScore(KarmaUser karmaUser) {
        KarmaLevel karmaLevel = karmaUser.getKarmaLevel();
        System.out.println("DEBUG: Karma Level = " + karmaLevel);
        clearScore(karmaUser);
        switch (karmaLevel){
            case GREEN:
                greenTeam.addPlayer(karmaUser.getPlayer());
                System.out.println("(SB) DEBUG: " + karmaUser.getPlayer().getDisplayName() + " Green");
                break;
            case NEUTRAL:
                neutralTeam.addPlayer(karmaUser.getPlayer());
                System.out.println("(SB) DEBUG: " + karmaUser.getPlayer().getDisplayName() + " Neutral");
                break;
            case RED:
                redTeam.addPlayer(karmaUser.getPlayer());
                System.out.println("(SB) DEBUG: " + karmaUser.getPlayer().getDisplayName() + " Red");
                break;
            case YELLOW:
                yellowTeam.addPlayer(karmaUser.getPlayer());
                System.out.println("(SB) DEBUG: " + karmaUser.getPlayer().getDisplayName() + " Yellow");
                break;
        }
    }

    @Override
    public void clearAllScores() {
        greenTeam.unregister();
        neutralTeam.unregister();
        redTeam.unregister();
        yellowTeam.unregister();
    }

    //Using Depreciated API because frankly, I don't know any other way to do it and I don't have WiFi right now
    @Override
    public void clearScore(KarmaUser karmaUser) {
        if (greenTeam.hasPlayer(karmaUser.getPlayer())){
            System.out.println("(SB) DEBUG: Clearing " + karmaUser.getPlayer().getDisplayName() + " Green");
            greenTeam.removePlayer(karmaUser.getPlayer());
        } else if (neutralTeam.hasPlayer(karmaUser.getPlayer())){
            System.out.println("(SB) DEBUG: Clearing " + karmaUser.getPlayer().getDisplayName() + " Neutral");
            neutralTeam.removePlayer(karmaUser.getPlayer());
        } else if (redTeam.hasPlayer(karmaUser.getPlayer())){
            System.out.println("(SB) DEBUG: Clearing" + karmaUser.getPlayer().getDisplayName() + " Red");
            redTeam.removePlayer(karmaUser.getPlayer());
        } else if (yellowTeam.hasPlayer(karmaUser.getPlayer())){
            yellowTeam.hasPlayer(karmaUser.getPlayer());
            System.out.println("(SB) DEBUG: Clearing " + karmaUser.getPlayer().getDisplayName() + " Yellow");
        }
    }

    @Override
    public String getScoreboardMethod() {
        return "VanillaScoreboard";
    }
}
