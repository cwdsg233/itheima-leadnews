package com.itheima.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @version 1.0
 * @description 频道实体类
 * @package com.itheima.admin.pojo
 */
@Data
@TableName("ad_channel")
@ApiModel(description = "频道实体")
public class AdChannel {

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(notes = "主键id")
    private Integer id;

    @TableField("name")
    //校验规则
    //@NotBlank//不能为空
    //@NonNull//不能为null值
    @Size(min = 2,max = 10,message = "频道名称不能为空，长度在2-10之间")
    @ApiModelProperty(notes = "名称")
    private String name;

    @TableField("description")
    @ApiModelProperty(notes = "描述")
    private String description;

    @TableField("is_default")
    @ApiModelProperty(notes = "是否为默认")
    private Boolean isDefault;

    @TableField("status")
    @ApiModelProperty(notes = "是否启用")
    private Boolean status;

    @TableField("ord")
    @ApiModelProperty(notes = "排序")
    private Integer ord;

    @TableField("created_time")
    @ApiModelProperty(notes = "创建时间")
    private Date createdTime;
}
