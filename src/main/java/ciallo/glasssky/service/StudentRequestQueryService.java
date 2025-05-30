package ciallo.glasssky.service;

import ciallo.glasssky.dao.StudentRequestQueryDao;
import ciallo.glasssky.model.Result;

import java.util.ArrayList;

public class StudentRequestQueryService {
    public static Result query() {
        Result result = StudentRequestQueryDao.query();
        if (result.code == 0)
            return result;

        for (Object[] objects : (ArrayList<Object[]>) result.content) {

            if (objects[6].equals(0)) {
                objects[6] = "未审核";
                for (int i = 3; i <= 5; i++) {
                    objects[i] = "";
                }
            }
            else
                objects[6] = "已审核";
        }


        return result;
    }
}
