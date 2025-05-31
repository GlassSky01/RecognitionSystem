package ciallo.glasssky.dao;

import ciallo.glasssky.model.Result;
import ciallo.glasssky.utils.DbOperators;

import java.util.ArrayList;

public class GetNoTutorStudentDao {

    public static Result get(String condition) {
        ArrayList<Object[]> arr;
        try {
            arr = DbOperators.executeQuery("select u1.username , a.name , b.name , u2.username from (usersinformation a  natural join users u1 )\n" +
                            "left join (usersinformation b natural join users u2)\n" +
                            "on a.tutorId = b.id where u1.role = 0 " + condition + ";" ,
                    new Class[]{String.class , String.class ,String.class , String.class});
        } catch (Exception e) {
            return Result.failure("查询失败");
        }
        return Result.success(arr);
    }
}
