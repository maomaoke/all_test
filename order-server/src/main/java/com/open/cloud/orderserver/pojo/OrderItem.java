package com.open.cloud.orderserver.pojo;

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
 * 订单商品表
 * </p>
 *
 * @author chen-ke-chao
 * @since 2019-07-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("order_item")
@ApiModel(value = "OrderItem对象", description = "订单商品表")
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "订单id")
    @TableField("order_id")
    private Long orderId;

    @ApiModelProperty(value = "商品id")
    @TableField("product_id")
    private Long productId;

    @ApiModelProperty(value = "商品名称")
    @TableField("product_name")
    private String productName;

    @ApiModelProperty(value = "商品价格")
    @TableField("product_price")
    private BigDecimal productPrice;

    @ApiModelProperty(value = "购买数量")
    @TableField("product_quantity")
    private Integer productQuantity;

    @ApiModelProperty(value = "商品图片")
    @TableField("product_icon")
    private String productIcon;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;


}
