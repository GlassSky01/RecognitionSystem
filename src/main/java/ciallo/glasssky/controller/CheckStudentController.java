package ciallo.glasssky.controller;

import ciallo.glasssky.model.Result;
import ciallo.glasssky.service.CheckStudentService;

public class CheckStudentController {

    public static Result check(int id) {
        return CheckStudentService.check(id);
    }
}
