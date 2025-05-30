package ciallo.glasssky.controller;

import ciallo.glasssky.model.Result;
import ciallo.glasssky.service.GetTeachersService;

public class GetTeachersController {
    public static Result get() {
        return GetTeachersService.get();
    }
}
