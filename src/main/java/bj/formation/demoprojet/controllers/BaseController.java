package bj.formation.demoprojet.controllers;

import bj.formation.demoprojet.dtos.responses.HttpResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class BaseController {

    @Autowired
    protected HttpServletRequest request;

    protected <T> ResponseEntity<?> response(T data, String message) {
        HttpResponse<T> response = HttpResponse.<T>builder()
                .statusCode(200)
                .success(true).data(data)
                .message(message)
                .path(request.getRequestURI()).build();

        return ResponseEntity.ok(response);
    }

    protected <T> ResponseEntity<?> response(T data) {
        return response(data, "Operation effectué avec succès");
    }

    protected <T> ResponseEntity<?> error(String message) {
        HttpResponse<T> response = HttpResponse.<T>builder()
                .statusCode(500)
                .success(false)
                .message(message)
                .path(request.getRequestURI()).build();

        return ResponseEntity.badRequest().body(response);
    }
}