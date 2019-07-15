package com.open.cloud.productserver.dao;

import com.open.cloud.productserver.pojo.ProductInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chen-ke-chao
 * @since 2019-07-14
 */
@Repository
public interface ProductInfoMapper extends BaseMapper<ProductInfo> {
    List<ProductInfo> selectByStatus(@Param("status") Integer status);
}
