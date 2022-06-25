package com.example.funddbservice.service.impl;

import com.example.funddbservice.dao.FundRepositoryImpl;
import com.example.funddbservice.model.Fund;
import com.example.funddbservice.service.FundDbService;
import com.example.funddbservice.utils.ListUtils;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;

public class FundDbServiceImpl implements FundDbService {

    @Autowired
    FundRepositoryImpl fundRepositoryImpl;

    @SneakyThrows
    @Async
    public CompletableFuture<Iterable<Fund>> getAllFunds() {
        return CompletableFuture.supplyAsync(() -> fundRepositoryImpl.findAll());
    }

    @SneakyThrows
    @Async
    public CompletableFuture<Fund> getFundInfo(Long fundId) {
        return CompletableFuture.supplyAsync(() -> fundRepositoryImpl.findById(fundId).orElse(null));
    }

    @SneakyThrows
    @Async
    public CompletableFuture<Fund> addFundInfo(Fund fund) {

        CompletableFuture<Boolean> containsFund = CompletableFuture
                .supplyAsync(this::getAllFunds).join()
                .thenApplyAsync(fundsIterable -> ListUtils.getListFromIterator(fundsIterable.iterator()))
                .thenApplyAsync(fundList -> fundList.contains(fund));

        return CompletableFuture.supplyAsync(() -> {
            try {
                return !containsFund.get() ? fundRepositoryImpl.save(fund) : null;
            } catch (Exception e) {
                return null;
            }
        });
    }

    @SneakyThrows
    @Async
    public CompletableFuture<Fund> updateFundInfo(Long fundId, Fund fundToSave) {
        return CompletableFuture
                .supplyAsync(() -> getFundInfo(fundId).join())
                .thenApplyAsync(result -> {
                    if(result != null) {
                        result.setFundValue(fundToSave.getFundValue());
                    }
                    return result;
                }).thenApplyAsync(alteredFund -> alteredFund != null? fundRepositoryImpl.save(fundToSave) : null);
    }
}
