package ciallo.glasssky.dao;

import ciallo.glasssky.model.Result;
import ciallo.glasssky.utils.DbOperators;
import ciallo.glasssky.utils.LocalUser;

import java.util.ArrayList;

public class GetPersonalInfoDao {
    public static Result get(ArrayList<String> newList , ArrayList<String> types) {
        Object[] objects = new Object[newList.size()];
        for(int i = 0 ; i < objects.length ;i  ++)
        {
            try {
                objects[i] = DbOperators.executeQuery(
                        "select "+ newList.get(i)+" from UsersInformation where id = ?;",
                        new String[]{types.get(i)},
                        LocalUser.id
                ).get(0)[0];
            } catch (Exception e) {
                System.out.println(newList.get(i) + "查询失败");
            }
        }
        return Result.success(objects);
    }
}
