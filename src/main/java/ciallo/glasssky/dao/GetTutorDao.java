package ciallo.glasssky.dao;

import ciallo.glasssky.model.Result;
import ciallo.glasssky.utils.DbOperators;

import java.util.ArrayList;

public class GetTutorDao {

    public static Result get() {
        ArrayList<Object[]> arr;
        try {
            arr = DbOperators.executeQuery("select b.name , a.username from users a natural join usersinformation b where role = 1;",
                    new Class[]{String.class , String.class});
        } catch (Exception e) {
            return Result.failure("查询失败");
        }
        return Result.success(arr);
    }
}
