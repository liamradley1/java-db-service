package com.example.funddbservice.dao;

import com.example.funddbservice.model.Fund;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundRepositoryImpl extends CrudRepository<Fund, Long> {
}
