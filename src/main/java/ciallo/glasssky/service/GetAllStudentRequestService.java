package ciallo.glasssky.service;

import ciallo.glasssky.dao.GetAllStudentRequestDao;
import ciallo.glasssky.model.Result;

public class GetAllStudentRequestService {
    public static Result get() {
        return GetAllStudentRequestDao.get();
    }
}
