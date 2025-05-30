package ciallo.glasssky.service;

import ciallo.glasssky.dao.DownloadFileDao;
import ciallo.glasssky.model.Result;

public class DownloadFileService {
    public static Result download(int requestId) {
        return DownloadFileDao.download(requestId);
    }
}
