package com.open.cloud.productserver.service;

import com.open.cloud.productserver.dto.ProductListDTO;

import java.util.List;

/**
 * @author chenkechao
 * @date 2019-07-14 14:28
 */
public interface ProductService {


    List<ProductListDTO> list();
}