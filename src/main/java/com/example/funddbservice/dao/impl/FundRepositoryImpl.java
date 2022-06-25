package com.example.funddbservice.dao.impl;

import com.example.funddbservice.dao.FundRepository;
import com.example.funddbservice.model.Fund;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.SQL;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class FundRepositoryImpl implements FundRepository{

    @Autowired(required = false)
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Fund> findAll() {
        String sql = "SELECT * FROM fund";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Fund.class));
    }

    @Override
    public Fund findBySymbol(String fundSymbol) {
        String sql = "SELECT * FROM fund WHERE symbol = ?";
        return jdbcTemplate.queryForObject(sql, Fund.class, fundSymbol);
    }

    @Override
    public List<Fund> addFunds(List<Fund> funds) {
        String sql = "INSERT INTO fund(symbol, fund_name, fund_value) VALUES (?, ?, ?) ON CONFLICT DO NOTHING";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Fund fund = funds.get(i);
                ps.setString(1, fund.getSymbol());
                ps.setString(2, fund.getFundName());
                ps.setBigDecimal(3, fund.getFundValue());
            }

            @Override
            public int getBatchSize() {
                return funds.size();
            }
        });
        return funds;
    }

    @Override
    public List<Fund> updateFunds(List<Fund> funds) {
        String sql = "UPDATE fund SET fund_value = ? WHERE symbol = ? AND fund_name = ?";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Fund fund = funds.get(i);
                ps.setBigDecimal(1, fund.getFundValue());
                ps.setString(2, fund.getSymbol());
                ps.setString(3, fund.getFundName());
            }

            @Override
            public int getBatchSize() {
                return funds.size();
            }
        });
        return funds;
    }



}
