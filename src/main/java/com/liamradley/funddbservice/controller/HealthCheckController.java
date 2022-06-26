package com.liamradley.funddbservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.liamradley.commonutils.constants.Constants.HEALTH_CHECK_ENDPOINT;

@RestController
public class HealthCheckController {
    @GetMapping(HEALTH_CHECK_ENDPOINT)
    public ResponseEntity<String> healthStatus() {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

}
