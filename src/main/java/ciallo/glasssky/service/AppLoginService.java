package ciallo.glasssky.service;


import ciallo.glasssky.dao.AppLoginDao;
import ciallo.glasssky.model.Result;
import ciallo.glasssky.model.User;

public class AppLoginService {

    public static Result login(User user) {

        if(user.username.isEmpty())
            return Result.failure("请输入用户名");
        if(user.password.isEmpty())
            return Result.failure("请输入密码");

        Result result = AppLoginDao.login(user);
        if(result.code == 1)
        {
            ((Object[]) result.content)[0] = new String[]{"Student" , "Teacher" , "Administrator"}[(int) ((Object[]) result.content)[0]];
        }
        return result;
    }
}
