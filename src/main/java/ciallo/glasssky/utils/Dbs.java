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
            return false;
        }
        Dbs.user = user;
        Dbs.password = password;
        DbOperators.conn = conn;
        init();
        return true;
    }
    public static void init(){
        try {
            DbOperators.execute("create database if not exists GlassSkyRecognitionSystem;");
            DbOperators.execute("use GlassSkyRecognitionSystem;");

            //用户表
            DbOperators.execute("create table Users(\n" +
                    "    id int auto_increment primary key,\n" +
                    "    username varchar(20),\n" +
                    "    password varchar(20),\n" +
                    "    role varchar(5)\n" +
                    "\n" +
                    ")ENGINE=InnoDB , auto_increment= 100000;");
            DbOperators.execute("insert into users(id , username , password , role) value(100000 , 'root' , '123456' , 2);");
        }
        catch (Exception e)
        {
        }

    }
}
