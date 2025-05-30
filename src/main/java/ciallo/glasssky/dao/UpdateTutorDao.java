package ciallo.glasssky.dao;

import ciallo.glasssky.utils.DbOperators;

import java.util.ArrayList;

public class UpdateTutorDao {
    public static void update(ArrayList<String[]> arr) {
        for (String[] strings : arr) {
            int studentId;
            try {
                studentId = (int) DbOperators.executeQuery("select id from users where username = ?;",
                        new Class[]{Integer.class}, strings[0]).get(0)[0];
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            int tutorId;
            try {
                tutorId = (int) DbOperators.executeQuery("select id from users where username = ?;",
                        new Class[]{Integer.class}, strings[1]).get(0)[0];
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            try {
                DbOperators.execute("update usersinformation set tutorId = ? \n" +
                        "where id = ?;", tutorId , studentId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
