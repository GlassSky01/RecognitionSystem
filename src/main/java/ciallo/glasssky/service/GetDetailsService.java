package ciallo.glasssky.service;

import ciallo.glasssky.dao.GetDetailsDao;
import ciallo.glasssky.model.Result;

public class GetDetailsService {

    public static Result get(int requestId) {
        return GetDetailsDao.get(requestId);
    }
}
