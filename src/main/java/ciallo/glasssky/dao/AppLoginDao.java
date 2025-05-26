package ciallo.glasssky.dao;

import ciallo.glasssky.model.Result;
import ciallo.glasssky.model.User;
import ciallo.glasssky.utils.DbOperators;

import java.sql.Statement;

public class AppLoginDao {
    public Result login(User user) {
        int cnt= 0;
        try {
            cnt = (int) DbOperators.executeQuery(
                    "select count(username) from Users where username = ? and password = ? and role = ?;"
                    ,new Object[]{0} , user.username , user.password , user.role
            ).get(0)[0];
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(cnt == 1)
            return Result.success();
        return Result.failure("角色或账号或密码错误");
    }
}
