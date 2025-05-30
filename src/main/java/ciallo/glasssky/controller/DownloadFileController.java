package ciallo.glasssky.controller;

import ciallo.glasssky.model.Result;
import ciallo.glasssky.service.DownloadFileService;

public class DownloadFileController {
    public static Result download(int requestId) {
        return DownloadFileService.download(requestId);
    }
}
