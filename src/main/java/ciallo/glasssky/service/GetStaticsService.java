package ciallo.glasssky.service;

import ciallo.glasssky.dao.GetStaticsDao;
import ciallo.glasssky.model.Result;

import java.util.ArrayList;

public class GetStaticsService {
    public static Result get(String usernameCon, String nameCon, String stateCon,String gradeCon, String condition) {
        String sql = "select c.requestId , u.username ,  i.name , c.dates , c.total , c.getTotal , c.grade , c.auditDate , c.auditState\n" +
                "from creditrequestmain c natural join users u left join usersinformation i\n" +
                "on c.id = i.id\n" +
                "where u.username like '%" + usernameCon +"%' " +
                "and i.name like '%" + nameCon +  "%' " +
                stateCon + " " + gradeCon + " " +
                condition +";";
        Result result = GetStaticsDao.get(sql);
        if(result.code == 0)
            return result;
        ArrayList<Object[]> arr = (ArrayList<Object[]>) result.content;
        for(Object[] objects : arr) {
            if (objects[objects.length - 1].equals(0))
                objects[objects.length - 1] = "未审核";
            else
                objects[objects.length - 1] = "已审核";

        }
        return result;
    }
}
