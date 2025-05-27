package ciallo.glasssky.controller;

import ciallo.glasssky.model.Result;
import ciallo.glasssky.service.ResetPasswordService;

public class ResetPasswordController {

    public static Result reset(String oldPassword, String newPassword) {
        return ResetPasswordService.reset(oldPassword , newPassword);
    }
}
