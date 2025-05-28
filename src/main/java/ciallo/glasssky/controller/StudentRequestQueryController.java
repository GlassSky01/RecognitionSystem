package ciallo.glasssky.controller;

import ciallo.glasssky.model.Result;
import ciallo.glasssky.service.StudentRequestQueryService;

public class StudentRequestQueryController {
    public static Result query() {
        return StudentRequestQueryService.query();
    }
}
