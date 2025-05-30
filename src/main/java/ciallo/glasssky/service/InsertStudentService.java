package ciallo.glasssky.service;

import ciallo.glasssky.dao.InsertStudentDao;
import ciallo.glasssky.model.Result;

public class InsertStudentService {
    public static Result insert(String name, String academy, String professional, Integer grade, Integer clazz) {
        if(name.isEmpty())
            return Result.failure("请填写姓名");
        return InsertStudentDao.insert(name , academy , professional , grade , clazz);
    }
}
