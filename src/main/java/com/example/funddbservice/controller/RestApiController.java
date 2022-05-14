package com.example.funddbservice.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

import com.example.funddbservice.dao.FundRepositoryImpl;
import com.example.funddbservice.model.Fund;
import com.example.funddbservice.service.FundDbService;
import lombok.SneakyThrows;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestApiController {

    @Autowired
    FundDbService fundDbService;

    @GetMapping("/get-funds")
    @SneakyThrows
    public ResponseEntity<Iterable<Fund>> getAllFunds() {
        return new ResponseEntity<>(fundDbService.getAllFunds(), HttpStatus.OK);
    }

    @GetMapping("/get-fund/{id}")
    @SneakyThrows
    public ResponseEntity<Fund> getFundInfo(@PathVariable("id") Long fundId) {
        Fund fund = fundDbService.getFundInfo(fundId);
        return new ResponseEntity<>(fund, fund != null? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping("/post-fund")
    public ResponseEntity<Void> addFundInfo(@RequestBody Fund fund) {
        return new ResponseEntity<>(fundDbService.addFundInfo(fund) != null? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/put-fund/{id}")
    @SneakyThrows
    public ResponseEntity<Fund> updateFundInfo(@PathVariable("id") Long fundId, @RequestBody Fund fundToSave) {
        Fund updatedFund = fundDbService.updateFundInfo(fundId, fundToSave);
        return new ResponseEntity<>(updatedFund, updatedFund == null ? HttpStatus.NOT_FOUND : HttpStatus.ACCEPTED);
    }
}
