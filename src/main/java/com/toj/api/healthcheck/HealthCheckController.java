package com.toj.api.healthcheck;

import com.toj.global.model.ApiResponse;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Hidden
@Slf4j
@RestController
@RequestMapping("/api/healthcheck")
public class HealthCheckController {
    /**
     * {
     *     "httpStatus": "OK",
     *     "message": "OK",
     *     "data": "admin test success!"
     * }
     */
    @GetMapping("")
    public ApiResponse<String> healthCheck() {
        log.info("admin test success!");
        return ApiResponse.success("healthCheck success!");

    }

}


