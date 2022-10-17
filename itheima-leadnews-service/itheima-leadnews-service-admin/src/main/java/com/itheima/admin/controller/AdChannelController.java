package com.itheima.admin.controller;

import com.itheima.admin.dto.ChannelPageRequestDto;
import com.itheima.admin.pojo.AdChannel;
import com.itheima.admin.service.AdChannelService;
import com.itheima.common.vo.PageResultVo;
import com.itheima.common.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @description <p>频道信息</p>
 *
 * @version 1.0
 * @package com.itheima.admin.controller
 */
@RestController
@RequestMapping("/channel")
@Api(tags = "频道管理")
public class AdChannelController {
    @Resource
    private AdChannelService adChannelService;
    /**
     * 分页查询
     * @param dto
     * @return
     */
    @PostMapping("/list")
    @ApiOperation("频道分页查询")
    public PageResultVo list(@RequestBody ChannelPageRequestDto dto){
        //调用业务层分页查询
        PageResultVo vo = adChannelService.findPage(dto);
        return vo;
    }

    /**
     * 新增频道
     * @param adChannel
     * @return
     */
    @PostMapping("/save")
    @ApiOperation("新增频道")
    public ResultVo save(@RequestBody @Validated AdChannel adChannel){
        adChannelService.add(adChannel);
        return ResultVo.ok("操作成功");
    }

    /**
     * 通过id查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("通过id查询")
    public ResultVo getById(@PathVariable(value = "id") Integer id){
        AdChannel adChannel = adChannelService.getById(id);
        return ResultVo.ok(adChannel);
    }

    /**
     * 更新频道
     * @param adChannel
     * @return
     */
    @PostMapping("/update")
    @ApiOperation("更新频道")
    public ResultVo update(@RequestBody AdChannel adChannel){
        adChannelService.updateById(adChannel);
        return ResultVo.ok("修改成功");
    }

    /**
     * 删除频道
     * @param id
     * @return
     */
    @DeleteMapping("/del/{id}")
    @ApiOperation("删除频道")
    public ResultVo deleteById(@PathVariable(value = "id") Integer id){
        adChannelService.removeById(id);
        return ResultVo.ok("删除成功");
    }
}
