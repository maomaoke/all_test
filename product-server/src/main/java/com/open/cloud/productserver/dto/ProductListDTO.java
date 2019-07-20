package com.open.cloud.productserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-07-16-23:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductListDTO {

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    List<ProductInfoDTO> productInfoList;
}
