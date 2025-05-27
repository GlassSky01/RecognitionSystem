package ciallo.glasssky.utils;

public class LocalUser {
    public static String username;
    public static String password;

    public static void setInfo(String username , String password){
        LocalUser.username = username;
        LocalUser.password = password;
    }
}
