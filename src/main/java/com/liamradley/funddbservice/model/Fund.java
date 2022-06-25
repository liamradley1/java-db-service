package com.liamradley.funddbservice.model;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fund {
    private Long fundId;

    @NonNull
    private String symbol;

    @NonNull
    private String fundName;

    @NonNull
    private BigDecimal fundValue;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Fund fund = (Fund) o;
        return fundName.equalsIgnoreCase(fund.fundName) || symbol.equals(fund.getSymbol()) || fundId.equals(fund.fundId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
