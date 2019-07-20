package com.open.cloud.productserver.service.impl;

import com.google.common.collect.Lists;
import com.open.cloud.productserver.dao.ProductInfoMapper;
import com.open.cloud.productserver.dto.ProductInfoDTO;
import com.open.cloud.productserver.dto.ProductListDTO;
import com.open.cloud.productserver.enums.ProductStatusEnum;
import com.open.cloud.productserver.pojo.ProductCategory;
import com.open.cloud.productserver.pojo.ProductInfo;
import com.open.cloud.productserver.service.CategoryService;
import com.open.cloud.productserver.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author chenkechao
 * @date 2019-07-14 14:29
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Autowired
    private CategoryService categoryService;

    @Override
    public List<ProductListDTO> list() {
        List<ProductInfo> productInfoList = productInfoMapper.selectByStatus(ProductStatusEnum.UP.getValue());
        Set<Integer> categoryTypeSet = productInfoList.stream().map(ProductInfo::getCategoryType).collect(Collectors.toSet());

        List<ProductCategory> categoryList = categoryService.listByCategoryTypeIn(Lists.newArrayList(categoryTypeSet));

        List<ProductListDTO> dtoList = new ArrayList<>();
        for (ProductCategory productCategory: categoryList) {
            ProductListDTO productListDTO = new ProductListDTO();
            productListDTO.setCategoryName(productCategory.getCategoryName());
            productListDTO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoDTO> productInfoDTOList = new ArrayList<>();
            for (ProductInfo productInfo: productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoDTO productInfoDTO = new ProductInfoDTO();
                    BeanUtils.copyProperties(productInfo, productInfoDTO);
                    productInfoDTOList.add(productInfoDTO);
                }
            }
            productListDTO.setProductInfoList(productInfoDTOList);
            dtoList.add(productListDTO);
        }

        return dtoList;
    }
}
