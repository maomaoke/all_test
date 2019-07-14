package com.open.cloud.productserver.service;

import com.open.cloud.productserver.pojo.ProductCategory;

import java.util.List;

/**
 * @author chenkechao
 * @date 2019-07-14 14:42
 */
public interface CategoryService {

    List<ProductCategory> listByCategoryTypeIn(List<Integer> list);
}
