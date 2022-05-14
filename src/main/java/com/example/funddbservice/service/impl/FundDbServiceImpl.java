package com.example.funddbservice.service.impl;

import com.example.funddbservice.dao.FundRepositoryImpl;
import com.example.funddbservice.model.Fund;
import com.example.funddbservice.service.FundDbService;
import com.example.funddbservice.utils.ListUtils;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.concurrent.CompletableFuture;

public class FundDbServiceImpl implements FundDbService {

    @Autowired
    FundRepositoryImpl fundRepositoryImpl;

    @SneakyThrows
    public Iterable<Fund> getAllFunds() {
        CompletableFuture<Iterable<Fund>> funds = CompletableFuture.supplyAsync(() -> fundRepositoryImpl.findAll());
        return funds.get();
    }

    @SneakyThrows
    public Fund getFundInfo(Long fundId) {
        CompletableFuture<Fund> getFund = CompletableFuture.supplyAsync(() -> fundRepositoryImpl.findById(fundId).orElse(null));
        return getFund.get();
    }

    @SneakyThrows
    public Fund addFundInfo(Fund fund) {

        CompletableFuture<Boolean> containsFund = CompletableFuture
                .supplyAsync(this::getAllFunds)
                .thenApplyAsync(fundsIterable -> ListUtils.getListFromIterator(fundsIterable.iterator()))
                .thenApplyAsync(fundList -> fundList.contains(fund));

        return CompletableFuture.supplyAsync(() -> {
            try {
                return !containsFund.get() ? fundRepositoryImpl.save(fund) : null;
            } catch (Exception e) {
                return null;
            }
        }).get();
    }

    @SneakyThrows
    public Fund updateFundInfo(Long fundId, Fund fundToSave) {
        CompletableFuture<Fund> fund = CompletableFuture
                .supplyAsync(() -> getFundInfo(fundId))
                .thenApplyAsync(result -> {
                    if(result != null) {
                        fundToSave.setFundId(fundId);
                        result = fundToSave;
                    }
                    return result;
                }).thenApplyAsync(alteredFund -> alteredFund != null? fundRepositoryImpl.save(fundToSave) : null);
        return fund.get();
    }
}
