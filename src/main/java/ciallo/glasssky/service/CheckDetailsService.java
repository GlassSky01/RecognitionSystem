package ciallo.glasssky.service;

import ciallo.glasssky.dao.CheckDetailsDao;
import ciallo.glasssky.model.Result;

import java.util.ArrayList;

public class CheckDetailsService {
    public static Result check(int requestId, String state) {
        Result result = CheckDetailsDao.check(requestId);
        if(state.equals("未审核"))
        {
            ArrayList<Object[]> arr = (ArrayList<Object[]>) result.content;
            for(Object[] objects : arr)
                objects[objects.length - 1] = "";
        }
        return result;
    }
}
