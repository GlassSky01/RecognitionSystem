package ciallo.glasssky.utils;

public class LocalUser {
    public static int id;
    public static String username;
    public static String password;
    public static Object role;
    public static void setInfo(String username , String password , Object role , int id){
        LocalUser.username = username;
        LocalUser.password = password;
        LocalUser.role = role;
        LocalUser.id = id;
    }
}
