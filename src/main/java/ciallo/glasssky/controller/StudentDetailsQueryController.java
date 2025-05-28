package ciallo.glasssky.controller;

import ciallo.glasssky.model.Result;
import ciallo.glasssky.service.StudentDetailsQueryService;

public class StudentDetailsQueryController {
    public static Result query(int requestId) {
        return StudentDetailsQueryService.query(requestId);
    }
}
