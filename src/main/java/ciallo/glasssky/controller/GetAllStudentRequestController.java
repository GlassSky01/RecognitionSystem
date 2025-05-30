package ciallo.glasssky.controller;

import ciallo.glasssky.model.Result;
import ciallo.glasssky.service.GetAllStudentRequestService;

public class GetAllStudentRequestController {
    public static Result get() {
        return GetAllStudentRequestService.get();
    }
}
