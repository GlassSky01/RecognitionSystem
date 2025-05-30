package ciallo.glasssky.dao;

import ciallo.glasssky.model.Result;
import ciallo.glasssky.utils.DbOperators;

import java.util.ArrayList;

public class InsertStudentDao {
    public static Result insert(String name, String academy, String professional, Integer grade, Integer clazz) {
        int id;

        id = DbOperators.executeWithKey("insert into users(role) value(0);");
        String idStr = Integer.toString(id);

        try {
            DbOperators.execute("update users set username = ? , password = ? where id = ?;",
                    idStr , idStr , id);
        } catch (Exception e) {
            return Result.failure("更新失败");
        }

        try {
            DbOperators.execute("insert into usersinformation(id, name, academy ,professional,  grade,class )\n" +
                    "value( ? , ? , ? , ? , ? , ?);",
                    id , name  , academy , professional , grade , clazz);
        } catch (Exception e) {
            return Result.failure("插入失败");
        }
        return Result.success();
    }
}
