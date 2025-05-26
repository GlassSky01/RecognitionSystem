package ciallo.glasssky.utils;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Dbs {
    private static String url = "jdbc:mysql://localhost:3306/";
    private static String user;
    private static String password;
    private static Connection conn;
    private static Statement state;

    public static boolean login(String user, String password) {
        try {

            conn = DriverManager.getConnection(url, user, password);
            state = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        Dbs.user = user;
        Dbs.password = password;
        return true;
    }
    public static Statement getState(){
        return state;
    }
}
