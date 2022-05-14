package com.example.funddbservice.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.*;
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fund {

    @Id
    @GeneratedValue
    private Long fundId;

    @NonNull
    private String symbol;

    @NonNull
    private String fundName;

    @NonNull
    private BigDecimal fundValue;

    @Override
    public boolean equals(Object o) {
        if(o == this) {
            return true;
        }

        if(!(o instanceof Fund castObject)) {
            return false;
        }

        return castObject.symbol.equals(this.symbol);
    }

}
