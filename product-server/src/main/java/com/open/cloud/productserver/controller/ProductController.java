package com.open.cloud.productserver.controller;

import com.open.cloud.productserver.dto.ProductListDTO;
import com.open.cloud.productserver.service.ProductService;
import com.open.common.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chenkechao
 * @date 2019-07-14 14:26
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public ServerResponse list() {
        List<ProductListDTO> dtoList = productService.list();
        return ServerResponse.createBySuccess(dtoList);
    }
}
