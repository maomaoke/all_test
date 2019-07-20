package com.open.cloud.productserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-07-16-23:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductInfoDTO {
    @JsonProperty("id")
    private Long productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("description")
    private String productDescription;

    @JsonProperty("icon")
    private String productIcon;
}
