package com.example.funddbservice.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "fund")
@Builder
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class Fund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Fund fund = (Fund) o;
        return Objects.equals(fundId, fund.fundId) || symbol.equals(fund.getSymbol());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
