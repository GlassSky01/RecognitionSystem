package ciallo.glasssky.dao;

import ciallo.glasssky.model.Result;
import ciallo.glasssky.utils.DbOperators;

import java.util.ArrayList;

public class GetStudentInfoDao {
    public static Result get() {
        ArrayList<Object[]> arr;
        try {
            arr = DbOperators.executeQuery("select username , a.name , a.academy , a.professional , a.grade , a.class , b.name\n" +
                    "from users u natural join usersinformation a left join usersinformation b\n" +
                    "on a.tutorId = b.id where u.role = 0;" ,
                    new Class[]{String.class , String.class , String.class , String.class , Integer.class , Integer.class , String.class}  );
        } catch (Exception e) {
            return Result.failure("查询失败");
        }
        return Result.success(arr);

    }
}
