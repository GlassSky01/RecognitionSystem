package ciallo.glasssky.controller;

import ciallo.glasssky.model.Result;
import ciallo.glasssky.service.InsertTeacherService;

public class InsertTeacherController {
    public static Result insert(String name , String phone, String email) {
        if(name.isEmpty())
            return Result.failure("请填写名字");

        return InsertTeacherService.insert(name , phone , email);
    }
}
