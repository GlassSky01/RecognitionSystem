package ciallo.glasssky.dao;

import ciallo.glasssky.model.Result;
import ciallo.glasssky.utils.DbOperators;

public class InsertTeacherDao {
    public static Result insert(String name, String phone, String email) {
        String id = String.valueOf(DbOperators.executeWithKey("insert into users(role) value(1);"));
        try {
            DbOperators.execute("update users set username = ? , password = ? where id = ?;" ,
                    id, id , Integer.parseInt(id));
        } catch (Exception e) {
            return Result.failure("users更新失败");
        }
        try {
            DbOperators.execute("insert into UsersInformation(id , name , phoneNumber , email )\n" +
                    "value(? , ? , ? , ?);" ,
                    id , name , phone , email);
        } catch (Exception e) {
            return Result.failure("教师信息插入失败");
        }
        return Result.success();
    }
}
