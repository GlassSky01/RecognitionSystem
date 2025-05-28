package ciallo.glasssky.dao;

import ciallo.glasssky.model.Result;
import ciallo.glasssky.utils.DbOperators;

import java.util.ArrayList;

public class StudentDetailsQueryDao {
    public static Result query(int requestId) {
        ArrayList<Object[]> arr ;
        try {
            arr = DbOperators.executeQuery("select detailsId, types , project , content ,score , getScore from CreditRequestDetails where requestId = ?;",
                    new Class[]{Integer.class , String.class , String.class , String.class , Double.class, Double.class},
                    requestId);
        } catch (Exception e) {
            return Result.failure();
        }
        return Result.success(arr);
    }
}
