package com.itheima.admin.dto;

import com.itheima.common.dto.PageRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @version 1.0
 * @description 说明
 * @package com.itheima.admin.dto
 */
@Data
@ApiModel(value="ChannelPageRequestDto", description="频道分页查询参数")
public class ChannelPageRequestDto extends PageRequestDto {
    @ApiModelProperty(notes = "频道名称",dataType = "String")
    private String name;
    @ApiModelProperty(notes = "频道状态",dataType = "Integer")
    private Integer status;
}
