package net.snailz.karma.data;

import net.snailz.karma.Karma;
import net.snailz.karma.KarmaUser;
import org.bukkit.entity.Player;

import java.sql.*;
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
        executeUpdate("CREATE TABLE IF NOT EXISTS players (" +
                "uuid varchar(32)," +
                "karma int," +
                "PRIMARY KEY(uuid));");
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
        return "MySQL";
    }

    private void connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String address = plugin.getConfig().getString("mysql.address");
            String username = plugin.getConfig().getString("mysql.username");
            String password = plugin.getConfig().getString("mysql.password");
            connection = DriverManager.getConnection("jdbc:mysql://" + address + "/feedback?user=" + username + "&password=" + password);
        } catch (SQLException e) {
            plugin.getLogger().log(Level.SEVERE, "MySQL Error! Please report this with server log!");
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
            plugin.getLogger().log(Level.SEVERE, "MySQL Error! Please report this to Snailz with server log!!");
            e.printStackTrace();
        }
    }

    private ResultSet executeQuery(String statement){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            return preparedStatement.executeQuery();

        } catch (SQLException e){
            plugin.getLogger().log(Level.SEVERE, "MySQL Error! Please report this to Snailz with server log!!");
            e.printStackTrace();
            return null;
        }
    }
}
