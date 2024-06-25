package com.example.btth_java.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/error")
public class CustomErrorController implements ErrorController {

    @GetMapping
    public String handleError(HttpServletRequest request) {
        Optional<Object> status = Optional.ofNullable(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
        return status.filter(s -> Integer.parseInt(s.toString()) == 404)
                .map(s -> "error/404") // Xử lý lỗi 404 bằng view error/404
                .orElse("error/general"); // Xử lý các lỗi khác bằng view error/general
    }

    @Override
    public String getErrorPath() {
        return "/error"; // Đường dẫn xử lý lỗi
    }
}
