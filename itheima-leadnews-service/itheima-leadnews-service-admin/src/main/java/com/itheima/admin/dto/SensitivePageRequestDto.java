package com.itheima.admin.dto;

import com.itheima.common.dto.PageRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("敏感词分页查询条件")
public class SensitivePageRequestDto extends PageRequestDto {
    @ApiModelProperty("查询条件 敏感词")
    private String name;
}
