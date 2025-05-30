package ciallo.glasssky.service;

import ciallo.glasssky.dao.DbLoginDao;
import ciallo.glasssky.model.Result;

public class DbLoginService {
    private DbLoginDao dbLoginDao = new DbLoginDao();

    public static Result login(String user, String password) {
        if(user.isEmpty())
            return Result.failure("请输入用户名");
        if(password.isEmpty())
            return Result.failure("请输入密码");
        for(int i = 0 ; i < user.length() ; i ++){
            if(user.charAt(i) == '-' || user.charAt(i) == '\'')
                return Result.failure("不能有特殊字符");
        }
        for(int i = 0 ; i < password.length() ; i ++){
            if(password.charAt(i) == '-' || password.charAt(i) == '\'')
                return Result.failure("不能有特殊字符");
        }

        return DbLoginDao.login(user , password);
    }
}
