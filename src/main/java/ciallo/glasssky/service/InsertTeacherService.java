package ciallo.glasssky.service;

import ciallo.glasssky.dao.InsertTeacherDao;
import ciallo.glasssky.model.Result;

public class InsertTeacherService {
    public static Result insert(String name, String phone, String email) {
        return InsertTeacherDao.insert(name, phone , email);
    }
}
