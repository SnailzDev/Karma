package net.snailz.karma.data;

import net.snailz.karma.Karma;
import net.snailz.karma.KarmaUser;
import net.snailz.karma.KarmaUserManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.*;
import java.util.UUID;
import java.util.logging.Level;

public class MySQLStorage implements DataStorage{

    private Connection connection;
    private ResultSet resultSet;
    private Karma plugin;

    public MySQLStorage(Karma plugin){
        this.plugin = plugin;
        this.connection = null;
        this.resultSet = null;

        connect();
        String database = this.plugin.getConfig().getString("mysql.database");
        executeUpdate("CREATE DATABASE IF NOT EXISTS " + database);
        executeUpdate("USE " + database);
        executeUpdate("CREATE TABLE IF NOT EXISTS karma (" +
                "uuid varchar(32)," +
                "karma int," +
                "PRIMARY KEY(uuid));");
    }

    private void printSQLErr(){
        plugin.getLogger().log(Level.SEVERE, "MySQL Error! Please report this with server log!");
    }

    @Override
    public void sterilize(KarmaUser user) {
        //ERROR HERE. MOST LIKELY NOT THE WAY TO SET IN UPDATE STATEMENT
        executeUpdate("UPDATE karma SET karma = " + String.valueOf(user.getKarma()) + " WHERE uuid = "
                + user.getPlayer().getUniqueId().toString());
    }

    @Override
    public KarmaUser deSterilize(UUID uuid) {
        KarmaUser karmaUser;
        ResultSet results = executeQuery("SELECT * FROM karma WHERE uuid = " + uuid.toString());
        try {
            results.next();
            int karma = results.getInt("karma");
            karmaUser = new KarmaUser(Bukkit.getPlayer(uuid), karma);
            return karmaUser;
        } catch (SQLException e){
            printSQLErr();
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addNewKarmaUser(KarmaUser karmaUser) {
        executeUpdate("INSERT INTO karma (uuid, karma) VALUES (" + karmaUser.getPlayer().getUniqueId().toString() + ", "
                + String.valueOf(karmaUser.getKarma()) + ";");
    }

    @Override
    public String getStorageMethod() {
        return "MySQL";
    }

    private void connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String address = plugin.getConfig().getString("mysql.address");
            String username = plugin.getConfig().getString("mysql.username");
            String password = plugin.getConfig().getString("mysql.password");
            connection = DriverManager.getConnection("jdbc:mysql://" + address + "/feedback?user=" + username + "&password=" + password);
        } catch (SQLException e){
            printSQLErr();
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            plugin.getLogger().log(Level.SEVERE, "MySQL Driver Not Found! Please report this!");
            e.printStackTrace();
        }
    }

    private void executeUpdate(String statement){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.executeUpdate();

        } catch (SQLException e){
            printSQLErr();
            e.printStackTrace();
        }
    }

    private ResultSet executeQuery(String statement){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            return preparedStatement.executeQuery();

        } catch (SQLException e){
            printSQLErr();
            e.printStackTrace();
            return null;
        }
    }
}
