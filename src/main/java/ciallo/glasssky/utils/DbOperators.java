package ciallo.glasssky.utils;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class DbOperators {


    public static Connection conn;

    public static void execute(String sql, Object... objects) throws Exception {
        PreparedStatement state = conn.prepareStatement(sql);
        set(state, objects);
        state.execute();

    }

    public static ArrayList<Object[]> executeQuery(String sql, String[] types, Object... objects) throws Exception {
        ArrayList<Object[]> arr = new ArrayList<>();
        try (PreparedStatement state = conn.prepareStatement(sql)) {
            set(state, objects);
            ResultSet result = state.executeQuery();
            while (result.next()) {
                Object[] obj = new Object[types.length];
                get(result, obj, types);
                arr.add(obj);
            }
        } catch (SQLException e) {
            throw new SQLException();
        }

        return arr;
    }

    public static int executeWithKey(String sql, Object... objects) {
        try (PreparedStatement state = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            set(state , objects);
            state.executeUpdate();
            ResultSet result = state.getGeneratedKeys();
            result.next();
            return result.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    //index从0开始
    private static void set(PreparedStatement state, Object[] obj) throws Exception {
        for (int index0 = 0; index0 < obj.length; index0++) {
            if (obj[index0] instanceof String) {
                state.setString(index0 + 1, (String) obj[index0]);
            } else if (obj[index0] instanceof Integer) {
                state.setInt(index0 + 1, (Integer) obj[index0]);
            } else if (obj[index0] instanceof Double) {
                state.setDouble(index0 + 1, (Double) obj[index0]);
            } else if (obj[index0] instanceof InputStream) {
                state.setBinaryStream(index0 + 1, (InputStream) obj[index0]);
            } else {
                throw new Exception("未知类型");
            }
        }
    }

    private static void get(ResultSet result, Object[] obj, String[] types) throws Exception {
        for (int i = 0; i < obj.length; i++) {
            if (String.class.toString().equals(types[i])) {
                obj[i] = result.getString(i + 1);
            } else if (Integer.class.toString().equals(types[i])) {
                obj[i] = result.getInt(i + 1);
            } else if (Double.class.toString().equals(types[i])) {
                obj[i] = result.getDouble(i + 1);
            } else if (InputStream.class.toString().equals(types[i])) {
                obj[i] = result.getBinaryStream(i + 1);
            } else {
                throw new Exception("未知类型");
            }
        }
    }

}
