package ciallo.glasssky.controller;

import ciallo.glasssky.model.Result;
import ciallo.glasssky.service.VerificationConfirmService;

import javax.swing.*;

public class VerificationConfirmController {
    public static Result commit(JTable table, String area, int requestId) {
        return VerificationConfirmService.commit(table , area ,requestId);
    }
}
