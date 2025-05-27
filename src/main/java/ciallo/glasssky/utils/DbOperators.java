package ciallo.glasssky.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class DbOperators {


    public static Connection conn;

    public static void execute(String sql , Object... objects) throws Exception {
        try(PreparedStatement state = conn.prepareStatement(sql)) {
            for(int i = 0 ; i < objects.length ; i ++)
            {
                if(objects[i] instanceof String)
                {
                    state.setString(i + 1, (String) objects[i]);
                }
                else if(objects[i] instanceof Integer)
                {
                    state.setInt(i + 1, (Integer) objects[i]);
                }
                else if(objects[i] instanceof Double)
                {
                    state.setDouble(i + 1, (Double) objects[i]);
                }
                else{
                    throw new Exception("未知类型");
                }
            }
            state.execute();
        } catch (Exception e) {
            throw new Exception();
        }
    }
    public static ArrayList<Object[]> executeQuery(String sql , String[] types , Object... objects) throws Exception {
        ArrayList<Object[]> arr = new ArrayList<>();
        try(PreparedStatement state = conn.prepareStatement(sql)) {
            for (int i = 0; i < objects.length; i++) {
//                System.out.println(objects[i]);
//                System.out.println(objects[i].getClass());
                if (objects[i] instanceof String) {
                    state.setString(i + 1, (String) objects[i]);
                } else if (objects[i] instanceof Integer) {
                    state.setInt(i + 1, (Integer) objects[i]);
                } else if (objects[i] instanceof Double) {
                    state.setDouble(i + 1, (Double) objects[i]);
                } else {
                    throw new Exception("未知类型");
                }
            }
            ResultSet result = state.executeQuery();
            while (result.next()) {
                Object[] obj = new Object[types.length];
                for (int i = 0; i < obj.length; i++) {
//                    System.out.println(types[i].getClass());
                    if (String.class.toString().equals(types[i])) {
                        obj[i] = result.getString(i + 1);
                    } else if (Integer.class.toString().equals(types[i])) {
                        obj[i] = result.getInt(i + 1);
                    } else if (Double.class.toString().equals(types[i])) {
                        obj[i] = result.getDouble(i + 1);
                    } else {
                        throw new Exception("未知类型");
                    }
                }
//                System.out.println(obj[0]);
                arr.add(obj);
            }
        }catch (SQLException e)
        {
            throw new Exception();
        }

        return arr;
    }
}
