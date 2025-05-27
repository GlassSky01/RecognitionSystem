package ciallo.glasssky.dao;

import ciallo.glasssky.model.Result;
import ciallo.glasssky.utils.DbOperators;
import ciallo.glasssky.utils.LocalUser;

public class SaveInfoDao {
    public Result save(String phoneNum , String email)
    {

        try {
            DbOperators.execute("update UsersInformation set phoneNumber = ? , email = ? where id = ?;",
                    phoneNum , email , LocalUser.id);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.failure();
    }
}
