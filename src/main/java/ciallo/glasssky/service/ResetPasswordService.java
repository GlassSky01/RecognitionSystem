package ciallo.glasssky.service;

import ciallo.glasssky.dao.ResetPasswordDao;
import ciallo.glasssky.model.Result;

public class ResetPasswordService {
    public static Result reset(String oldPassword, String newPassword) {
        if(oldPassword.isEmpty())
            return Result.failure("请输入密码");

        if(!oldPassword.equals(oldPassword.replace("'" , "a" ).replace("-" , "a")))
            return Result.failure("不能有特殊字符");
        if(!newPassword.equals(newPassword.replace("'" , "a" ).replace("-" , "a")))
            return Result.failure("不能有特殊字符");

        Result result = ResetPasswordDao.reset(oldPassword, newPassword);

        if(result.code == 0)
            return result;

        if(newPassword.length() > 20)
            return Result.failure("新密码长度不能大于20");
        if(newPassword.isEmpty())
            return Result.failure("新密码不能为空");
        return result;
    }
}
