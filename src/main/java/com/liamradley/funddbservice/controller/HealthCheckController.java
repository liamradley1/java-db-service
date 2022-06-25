package com.liamradley.funddbservice.controller;

import com.liamradley.funddbservice.common.constants.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
    @GetMapping(Constants.HEALTH_CHECK_ENDPOINT)
    public ResponseEntity<String> healthStatus() {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

}
