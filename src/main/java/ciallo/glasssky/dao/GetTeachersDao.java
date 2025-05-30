package ciallo.glasssky.dao;

import ciallo.glasssky.model.Result;
import ciallo.glasssky.utils.DbOperators;

import java.util.ArrayList;

public class GetTeachersDao {
    public static Result get() {
        ArrayList<Object[]> arr;
        try {
            arr = DbOperators.executeQuery("select username , name , phoneNumber , email , password from UsersInformation natural join users  where role = 1;" ,
                    new Class[]{String.class , String.class , String.class , String.class , String.class});
        } catch (Exception e) {
            return Result.failure();
        }
        return Result.success(arr);
    }
}
