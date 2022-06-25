package com.example.funddbservice.service;

import com.example.funddbservice.model.Fund;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface FundDbService {

    CompletableFuture<Iterable<Fund>> getAllFunds();
    CompletableFuture<Fund> getFundInfo(String fundSymbol);
    CompletableFuture<List<Fund>> addFundInfo(List<Fund> fund);
    CompletableFuture<List<Fund>> updateFundInfo(List<Fund> fund);
}
