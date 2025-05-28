package ciallo.glasssky.controller;

import ciallo.glasssky.model.Result;
import ciallo.glasssky.service.GetPersonalInfoService;

import java.util.ArrayList;

public class GetPersonalInfoController {
    public static Result get(ArrayList<String> fieldsName ,ArrayList<Class<?>> types) {
        return GetPersonalInfoService.get(fieldsName , types);
    }
}
