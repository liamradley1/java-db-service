package com.example.funddbservice.controller;

import com.example.funddbservice.model.Fund;
import com.example.funddbservice.service.FundDbService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.example.funddbservice.common.constants.Constants.*;

@RestController
public class FundManagementApiController {

    @Autowired
    FundDbService fundDbService;

    @GetMapping(GET_ALL_FUNDS_ENDPOINT)
    @SneakyThrows
    public ResponseEntity<Iterable<Fund>> getAllFunds() {
        Iterable<Fund> funds = fundDbService.getAllFunds().get();
        return new ResponseEntity<>(funds, HttpStatus.OK);
    }

    @GetMapping(GET_FUND_ENDPOINT)
    @SneakyThrows
    public ResponseEntity<Fund> getFundInfo(@PathVariable("symbol") String fundSymbol) {
        Fund fund = fundDbService.getFundInfo(fundSymbol).get();
        return new ResponseEntity<>(fund, fund != null? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping(ADD_FUND_ENDPOINT)
    @SneakyThrows
    public ResponseEntity<Void> addFundInfo(@RequestBody List<Fund> funds) {
        return new ResponseEntity<>(fundDbService.addFundInfo(funds).get() != null? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }

    @PutMapping(UPDATE_FUND_ENDPOINT)
    @SneakyThrows
    public ResponseEntity<List<Fund>> updateFundInfo(@RequestBody List<Fund> fundsToSave) {
        return new ResponseEntity<>(fundDbService.updateFundInfo(fundsToSave).get() == null ? HttpStatus.NOT_FOUND : HttpStatus.ACCEPTED);
    }
}
