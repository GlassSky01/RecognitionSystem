package ciallo.glasssky.service;

import ciallo.glasssky.dao.StudentDetailsQueryDao;
import ciallo.glasssky.model.Result;

public class StudentDetailsQueryService {
    public static Result query(int requestId) {
        return StudentDetailsQueryDao.query(requestId);

    }
}
