package ciallo.glasssky.dao;

import ciallo.glasssky.model.Result;
import ciallo.glasssky.utils.DbOperators;

import java.util.ArrayList;

public class GetStaticsDao {
    public static Result get(String sql) {
        ArrayList<Object[]> arr;
//        System.out.println(sql);
        try {
            arr = DbOperators.executeQuery(sql ,
                    new Class[]{Integer.class , String.class , String.class , String.class,
                    Double.class , Double.class , String.class , String.class , Integer.class});
        } catch (Exception e) {
            return Result.failure();
        }
        return Result.success(arr);
    }
}
