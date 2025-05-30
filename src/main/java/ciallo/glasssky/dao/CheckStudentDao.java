package ciallo.glasssky.dao;

import ciallo.glasssky.model.Result;
import ciallo.glasssky.utils.DbOperators;

import java.util.ArrayList;

public class CheckStudentDao {
    public static Result check(int id) {
        ArrayList<Object[]> arr;
        try {
            arr = DbOperators.executeQuery("select requestId , dates , total , getTotal , grade , auditDate , auditState from CreditRequestMain where id = ?;",
                    new Class[]{Integer.class , String.class , Double.class , Double.class , String.class , String.class , Integer.class } ,
                    id);
        } catch (Exception e) {
            return Result.failure("学生信息查询失败");
        }
        return Result.success(arr);
    }
}
