package com.example.funddbservice.service;

import com.example.funddbservice.model.Fund;

import java.util.concurrent.CompletableFuture;

public interface FundDbService {

    CompletableFuture<Iterable<Fund>> getAllFunds();
    CompletableFuture<Fund> getFundInfo(Long fundId);
    CompletableFuture<Fund> addFundInfo(Fund fund);
    CompletableFuture<Fund> updateFundInfo(Long fundId, Fund fund);
}
