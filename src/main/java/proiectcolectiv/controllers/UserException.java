package proiectcolectiv.controllers;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class UserException extends ResponseStatusException {
    /**
     * Custom exception for Controller
     *
     * @param status
     * @param reason
     */
    public UserException(HttpStatusCode status, String reason) {
        super(status, reason);
    }
}