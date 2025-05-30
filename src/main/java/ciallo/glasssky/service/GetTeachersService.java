package ciallo.glasssky.service;

import ciallo.glasssky.dao.GetTeachersDao;
import ciallo.glasssky.model.Result;

public class GetTeachersService {
    public static Result get() {
        return GetTeachersDao.get();
    }
}
