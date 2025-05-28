package ciallo.glasssky.dao;

import ciallo.glasssky.model.Result;
import ciallo.glasssky.utils.DbOperators;
import ciallo.glasssky.utils.LocalUser;

import java.util.ArrayList;

public class StudentRequestQueryDao {
    public static Result query() {
        ArrayList<Object[]> arr;

        try {
            arr = DbOperators.executeQuery("select requestId , dates , total , getTotal ,\n" +
                    "grade , auditDate , auditState from CreditRequestMain where id = ?;",
                    new Class[]{Integer.class , String.class , Double.class , Double.class ,
                    String.class , String.class , Integer.class} ,
                    LocalUser.id);
        } catch (Exception e) {
            return Result.failure();
        }
        return Result.success(arr);
    }
}
