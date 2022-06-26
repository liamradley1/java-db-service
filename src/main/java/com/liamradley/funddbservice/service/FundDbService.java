package com.liamradley.funddbservice.service;


import com.liamradley.commonutils.model.Fund;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface FundDbService {

    CompletableFuture<List<Fund>> getAllFunds();
    CompletableFuture<Fund> getFundInfo(String fundSymbol);
    CompletableFuture<List<Fund>> addFundInfo(List<Fund> fund);
    CompletableFuture<List<Fund>> updateFundInfo(List<Fund> fund);
}
