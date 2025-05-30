package ciallo.glasssky.dao;

import ciallo.glasssky.model.Result;
import ciallo.glasssky.utils.DbOperators;
import ciallo.glasssky.utils.LocalUser;

import java.util.ArrayList;

public class GetAllStudentRequestDao {
    public static Result get() {
        ArrayList<Object[]> arr ;
        try {
            arr = DbOperators.executeQuery("select u.id,name , u.grade , u.academy , us.username  , sum(getTotal) , sum(total) , count(name) , sum(auditState = 0) \n" +
                            "from users us natural join usersinformation u join creditrequestmain c where u.id = c.id and u.tutorId = ?\n" +
                            "group by u.id ,name, u.grade , u.academy , us.username;",
                    new Class[]{Integer.class , String.class ,Integer.class , String.class, String.class ,  Double.class , Double.class , Integer.class , Integer.class} ,
                    LocalUser.id);
        } catch (Exception e) {
            return Result.failure("查询失败");
        }
        return Result.success(arr);
    }
}
