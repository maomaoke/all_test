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
 * 订单表
 * </p>
 *
 * @author chen-ke-chao
 * @since 2019-07-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("order")
@ApiModel(value = "Order对象", description = "订单表")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "order_id", type = IdType.AUTO)
    private Long orderId;

    @ApiModelProperty(value = "买家姓名")
    @TableField("buyer_name")
    private String buyerName;

    @ApiModelProperty(value = "买家电话")
    @TableField("buyer_phone")
    private String buyerPhone;

    @ApiModelProperty(value = "买家微信 openid")
    @TableField("buyer_openid")
    private String buyerOpenid;

    @ApiModelProperty(value = "买家地址")
    @TableField("buyer_address")
    private String buyerAddress;

    @ApiModelProperty(value = "订单总金额")
    @TableField("order_amount")
    private BigDecimal orderAmount;

    @ApiModelProperty(value = "订单状态, 默认新下订单")
    @TableField("order_status")
    private Integer orderStatus;

    @ApiModelProperty(value = "支付状态, 默认未支付")
    @TableField("pay_status")
    private Integer payStatus;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;


}
