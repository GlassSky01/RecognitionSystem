package ciallo.glasssky.controller;

import ciallo.glasssky.model.Result;
import ciallo.glasssky.service.DbLoginService;
import ciallo.glasssky.view.login.DbLogin;

import javax.swing.*;

public class DbLoginController {
    private DbLoginService dbLoginService = new DbLoginService();
    public Result login(String user , String password){
        return dbLoginService.login(user , password);
    }
}
