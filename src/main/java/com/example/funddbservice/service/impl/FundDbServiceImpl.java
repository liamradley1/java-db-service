package com.example.funddbservice.service.impl;

import com.example.funddbservice.dao.FundRepository;
import com.example.funddbservice.model.Fund;
import com.example.funddbservice.service.FundDbService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class FundDbServiceImpl implements FundDbService {

    @Autowired
    FundRepository fundRepository;

    @Override
    @SneakyThrows
    public CompletableFuture<Iterable<Fund>> getAllFunds() {
        return CompletableFuture.supplyAsync(() -> fundRepository.findAll());
    }

    @Override
    @SneakyThrows
    public CompletableFuture<Fund> getFundInfo(String fundSymbol) {
        return CompletableFuture.supplyAsync(() -> fundRepository.findBySymbol(fundSymbol));
    }

    @Override
    @SneakyThrows
    public CompletableFuture<List<Fund>> addFundInfo(List<Fund> funds) {
        return CompletableFuture.supplyAsync(() -> fundRepository.addFunds(funds));
    }

    @Override
    @SneakyThrows
    public CompletableFuture<List<Fund>> updateFundInfo(List<Fund> funds) {
        return CompletableFuture.supplyAsync(() -> fundRepository.updateFunds(funds));
    }
}
