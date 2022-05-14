package com.example.funddbservice.service;

import com.example.funddbservice.model.Fund;

public interface FundDbService {

    Iterable<Fund> getAllFunds();
    Fund getFundInfo(Long fundId);
    Fund addFundInfo(Fund fund);
    Fund updateFundInfo(Long fundId, Fund fund);
}
