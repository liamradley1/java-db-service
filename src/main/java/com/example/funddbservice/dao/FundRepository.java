package com.example.funddbservice.dao;

import com.example.funddbservice.model.Fund;

import java.util.List;

public interface FundRepository {

    List<Fund> findAll();

    Fund findBySymbol(String symbol);

    List<Fund> addFunds(List<Fund> funds);

    List<Fund> updateFunds(List<Fund> funds);
}
