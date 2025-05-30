package ciallo.glasssky.service;

import ciallo.glasssky.dao.CheckStudentDao;
import ciallo.glasssky.model.Result;

import java.util.ArrayList;

public class CheckStudentService {
    public static Result check(int id) {
        Result result =  CheckStudentDao.check(id);
        if(result.code == 0)
            return result;
        ArrayList<Object[]> content = (ArrayList<Object[]>) result.content;
        for(Object[] objects : content)
        {
            if(objects[objects.length - 1].equals(0)){
                for(int i = 3 ; i < 6 ; i ++)
                    objects[i] = "";
                objects[objects.length - 1] = "未审核";
            }
            else
                objects[objects.length - 1] = "已审核";
        }
        return result;
    }
}
