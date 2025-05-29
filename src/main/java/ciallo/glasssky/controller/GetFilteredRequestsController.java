package ciallo.glasssky.controller;

import ciallo.glasssky.model.Result;
import ciallo.glasssky.service.GetFilteredRequestsService;

public class GetFilteredRequestsController {
    public static Result get(String name, String grade, String academy, String username, String dates) {
        return GetFilteredRequestsService.get(name , grade , academy , username , dates);
    }
}
