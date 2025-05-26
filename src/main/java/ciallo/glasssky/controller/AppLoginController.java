package ciallo.glasssky.controller;

import ciallo.glasssky.model.Result;
import ciallo.glasssky.model.User;
import ciallo.glasssky.service.AppLoginService;

public class AppLoginController {
    private AppLoginService appLoginService = new AppLoginService();

    public Result login(User user) {
        return appLoginService.login(user);
    }
}
