package ciallo.glasssky.service;

import ciallo.glasssky.dao.VerificationConfirmDao;
import ciallo.glasssky.model.Result;

import javax.swing.*;

public class VerificationConfirmService {
    public static Result commit(JTable table, String area, int requestId) {
        Double[] auditScore = new Double[table.getRowCount()];
        for(int i = 0 ; i < table.getRowCount() ; i ++)
        {
            String content = (String)table.getValueAt(i, table.getColumnCount() - 1);
            System.out.println(i );
            System.out.println(table.getColumnCount() - 1);
            content.strip();
            if (content.isEmpty())
                return Result.failure("项目" + (i + 1) + "未评分");
            Double sc;
            try {
                 sc = Double.parseDouble(content);
            }catch (Exception e)
            {
                return Result.failure("项目"+ (i + 1) + "类型错误, 分数必须是数字");
            }
            if(sc < 0 )
                return Result.failure("项目" + (i + 1) + "分数不能小于0");
            if(sc > (Double)table.getValueAt(i , table.getColumnCount() - 2))
                return Result.failure("项目" + ( i + 1) + "评分不能大于申请分数");

            auditScore[i] = sc;
        }

        return VerificationConfirmDao.commit(auditScore , area , requestId);
    }
}
