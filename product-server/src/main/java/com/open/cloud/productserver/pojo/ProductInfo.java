package com.open.cloud.productserver.pojo;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author chen-ke-chao
 * @since 2019-07-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("product_info")
@ApiModel(value="ProductInfo对象", description="")
public class ProductInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "product_id", type = IdType.AUTO)
    private Long productId;

    @ApiModelProperty(value = "商品名称")
    @TableField("product_name")
    private String productName;

    @ApiModelProperty(value = "单价")
    @TableField("product_price")
    private BigDecimal productPrice;

    @ApiModelProperty(value = "库存")
    @TableField("product_stock")
    private Integer productStock;

    @ApiModelProperty(value = "描述")
    @TableField("product_description")
    private String productDescription;

    @ApiModelProperty(value = "image url")
    @TableField("product_icon")
    private String productIcon;

    @ApiModelProperty(value = "0:上架,1:下架")
    @TableField("product_status")
    private Integer productStatus;

    @ApiModelProperty(value = "商品类别编号")
    @TableField("category_type")
    private Integer categoryType;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;


}
