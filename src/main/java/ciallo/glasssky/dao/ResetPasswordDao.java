package ciallo.glasssky.dao;

import ciallo.glasssky.model.Result;
import ciallo.glasssky.utils.DbOperators;
import ciallo.glasssky.utils.LocalUser;

public class ResetPasswordDao {
    public static Result reset(String oldPassword, String newPassword) {
        if (LocalUser.password.equals(oldPassword))
        {
            try {
                DbOperators.execute("update users set password = ? where id = ?;",
                        newPassword , LocalUser.id);
                return Result.success("修改成功");
            } catch (Exception e) {
                System.out.println("修改失败");
                return Result.failure("修改失败");
            }
        }
        return Result.failure("密码错误");
    }
}
