package ciallo.glasssky.service;


import ciallo.glasssky.dao.AppLoginDao;
import ciallo.glasssky.model.Result;
import ciallo.glasssky.model.User;

public class AppLoginService {
    private AppLoginDao appLoginDao = new AppLoginDao();

    public Result login(User user) {
        if("学生".equals(user.role))
            user.role = 0;
        else if("教师".equals(user.role))
            user.role = 1;
        else if("管理员".equals(user.role))
            user.role = 2;
        else
            return Result.failure("请选择角色");

        if(user.username.isEmpty())
            return Result.failure("请输入用户名");
        if(user.password.isEmpty())
            return Result.failure("请输入密码");

        return appLoginDao.login(user);
    }
}
