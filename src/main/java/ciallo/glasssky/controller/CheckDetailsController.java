package ciallo.glasssky.controller;

import ciallo.glasssky.model.Result;
import ciallo.glasssky.service.CheckDetailsService;

public class CheckDetailsController {
    public static Result check(int requestId, String state) {
        return CheckDetailsService.check(requestId , state);
    }
}
