package com.open.cloud.productserver.service.impl;

import com.open.cloud.productserver.dao.ProductInfoMapper;
import com.open.cloud.productserver.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chenkechao
 * @date 2019-07-14 14:29
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoMapper productInfoMapper;
}
