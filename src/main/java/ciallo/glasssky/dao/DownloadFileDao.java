package ciallo.glasssky.dao;

import ciallo.glasssky.model.Result;
import ciallo.glasssky.utils.DbOperators;

import java.io.InputStream;

public class DownloadFileDao {
    public static Result download(int requestId) {
        Object[] content = new Object[2];
        try {
            content = DbOperators.executeQuery("select fileName, file from creditrequestmain c  where requestId = ?;",
                    new Class[]{String.class ,InputStream.class } ,
                    requestId).get(0);
        } catch (Exception e) {
            return Result.failure("下载失败");
        }

        return Result.success(content);
    }
}
