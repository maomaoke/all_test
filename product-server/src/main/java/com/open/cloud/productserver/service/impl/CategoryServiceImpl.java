package com.open.cloud.productserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.open.cloud.productserver.dao.ProductCategoryMapper;
import com.open.cloud.productserver.pojo.ProductCategory;
import com.open.cloud.productserver.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author chenkechao
 * @date 2019-07-14 14:43
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public List<ProductCategory> listByCategoryTypeIn(List<Integer> list) {
        QueryWrapper<ProductCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("category_type", list);

        return productCategoryMapper.selectList(queryWrapper);
    }
}
