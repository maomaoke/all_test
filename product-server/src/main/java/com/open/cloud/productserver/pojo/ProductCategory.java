package com.open.cloud.productserver.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品类别
 * </p>
 *
 * @author chen-ke-chao
 * @since 2019-07-14
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("product_category")
@ApiModel(value="ProductCategory对象", description="商品类别")
public class ProductCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "category_id", type = IdType.AUTO)
    private Long categoryId;

    @ApiModelProperty(value = "商品类别名称")
    @TableField("category_name")
    private String categoryName;

    @ApiModelProperty(value = "i")
    @TableField("category_type")
    private Integer categoryType;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;


}
