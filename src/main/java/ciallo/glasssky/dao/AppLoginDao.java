package ciallo.glasssky.dao;

import ciallo.glasssky.model.Result;
import ciallo.glasssky.model.User;
import ciallo.glasssky.utils.DbOperators;

import java.sql.Statement;

public class AppLoginDao {
    public Result login(User user) {
        Object[] obj;
        try {
            obj = DbOperators.executeQuery(
                    "select role, id from Users where username = ? and password = ? ;"
                    , new String[]{Integer.class.toString() , Integer.class.toString()}, user.username, user.password
            ).get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure("账号或密码错误");
        }
        return Result.success(obj);

    }
}
