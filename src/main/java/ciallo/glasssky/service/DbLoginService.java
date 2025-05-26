package ciallo.glasssky.service;

import ciallo.glasssky.controller.DbLoginController;
import ciallo.glasssky.dao.DbLoginDao;
import ciallo.glasssky.model.Result;

public class DbLoginService {
    private DbLoginDao dbLoginDao = new DbLoginDao();

    public Result login(String user, String password) {
        user = user.replace("-" , "a").replace("'" , "a");
        password = password.replace("-" , "a").replace("'" , "a");

        return dbLoginDao.login(user , password);
    }
}
