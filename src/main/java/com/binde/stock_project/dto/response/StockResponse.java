package com.binde.stock_project.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockResponse {
    private String name;
    private BigDecimal currentPrice;
}