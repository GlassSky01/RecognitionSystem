package ciallo.glasssky.controller;

import ciallo.glasssky.model.Result;
import ciallo.glasssky.service.GetDetailsService;

public class GetDetailsController {
    public static Result get(int requestId) {
        return GetDetailsService.get(requestId);
    }
}
