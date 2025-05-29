package ciallo.glasssky.dao;

import ciallo.glasssky.model.Result;
import ciallo.glasssky.utils.DbOperators;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GetFilteredRequestsDao {
    public static Result get(String sql, String name, String username, String dates) {
        ArrayList<Object[]> arr ;
        try {
            arr = DbOperators.executeQuery(sql , new Class[]{Integer.class , String.class , Integer.class , String.class , String.class , String.class} ,
                    name , username , dates);
        } catch (Exception e) {
            System.out.println("查询失败");
            return Result.failure();
        }
        return Result.success(arr);
    }
}
