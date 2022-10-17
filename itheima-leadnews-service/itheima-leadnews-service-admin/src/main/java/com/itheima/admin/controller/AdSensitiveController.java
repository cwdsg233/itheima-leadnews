package com.itheima.admin.controller;

import com.itheima.admin.dto.ChannelPageRequestDto;
import com.itheima.admin.dto.SensitivePageRequestDto;
import com.itheima.admin.pojo.AdSensitive;
import com.itheima.admin.service.AdChannelService;
import com.itheima.admin.service.AdSensitiveService;
import com.itheima.common.vo.PageResultVo;
import com.itheima.core.controller.AbstractCoreController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/sensitives")
@Api(tags = "敏感词管理")
public class AdSensitiveController extends AbstractCoreController<AdSensitive> {
    private AdSensitiveService adSensitiveService;
    //构造方法注入,可以省略@Autowired注解
    public AdSensitiveController(AdSensitiveService adSensitiveService) {
        super(adSensitiveService);
        //构造方法是优先执行的，这就给 this.adSensitiveService赋值了，此时不需要在属性上添加@Autowired注解
        this.adSensitiveService=adSensitiveService;
        //自己需要实现方法时，需要调用adSensitiveService，这样获取
    }

    /**
     * 分页查询
     * @param dto
     * @return
     */
    @PostMapping("/list")
    @ApiOperation("频道分页查询")
    public PageResultVo list(@RequestBody SensitivePageRequestDto dto){
        //调用业务层分页查询
        PageResultVo vo = adSensitiveService.findPage(dto);
        return vo;
    }
}
