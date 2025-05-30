package ciallo.glasssky.service;

import ciallo.glasssky.dao.InsertTeacherDao;
import ciallo.glasssky.model.Result;

public class InsertTeacherService {
    public static Result insert(String name, String phone, String email) {
        if(name.isEmpty())
            return Result.failure("请填写名字");
        return InsertTeacherDao.insert(name, phone , email);
    }
}
