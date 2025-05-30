package ciallo.glasssky.dao;

import ciallo.glasssky.model.Result;
import ciallo.glasssky.utils.DbOperators;

import java.time.LocalDate;

public class VerificationConfirmDao {
    public static Result commit(Double[] auditScore, String area, int requestId) {
        double sum = 0;
        int upTo3 = 0;
        int upTo4 = 0;
        for(int i = 0 ; i < auditScore.length ; i ++)
        {
            try {
                DbOperators.execute("update creditrequestdetails set getScore = ? where detailsId = ? and requestId = ?;" ,
                        auditScore[i] , i + 1, requestId);
            } catch (Exception e) {
                return Result.failure("详情更新失败");
            }
            sum += auditScore[i];
            if(auditScore[i] >= 3)
                upTo3 ++;
            if(auditScore[i] >= 4)
                upTo4 ++;
        }

        String grade ;
        if(upTo4 >= 1 || upTo3 >= 2|| sum >= 7)
            grade = "优秀";
        else if(upTo3 >= 1 || sum >= 6)
            grade = "良好";
        else if(sum >= 5)
            grade = "中等";
        else if(sum >= 4)
            grade = "及格";
        else
            grade ="不及格";


        try {
            DbOperators.execute("update creditrequestmain set getTotal = ? , grade = ? , auditDate = ? , auditState = ? , auditAdvice = ?\n" +
                    "where requestId = ?;",
                    sum , grade , LocalDate.now().toString() , 1 , area , requestId);
        } catch (Exception e) {
            return Result.failure("主信息更新失败");
        }

        return Result.success();

    }
}
