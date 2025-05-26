package ciallo.glasssky.dao;

import ciallo.glasssky.model.Result;
import ciallo.glasssky.utils.Dbs;

public class DbLoginDao {
    public Result login(String user, String password) {
        if(Dbs.login(user , password))
            return Result.success();
        return Result.failure("账号或密码错误");

    }
}
