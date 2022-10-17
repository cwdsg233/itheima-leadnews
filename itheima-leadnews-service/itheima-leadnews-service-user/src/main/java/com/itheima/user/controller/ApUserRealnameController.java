package com.itheima.user.controller;


import com.itheima.common.vo.PageResultVo;
import com.itheima.user.dto.ApUserRealnamePageRequestDto;
import com.itheima.user.pojo.ApUserRealname;
import com.itheima.user.service.ApUserRealnameService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;
import com.itheima.core.controller.AbstractCoreController;

/**
 * @description <p>APP实名认证信息</p>
 *
 * @version 1.0
 * @package com.itheima.user.controller
 */
@Api(value="ApUserRealnameController",tags = "APP实名认证信息")
@RestController
@RequestMapping("/apUserRealname")
public class ApUserRealnameController extends AbstractCoreController<ApUserRealname> {

    private ApUserRealnameService apUserRealnameService;

    @Autowired
    public ApUserRealnameController(ApUserRealnameService apUserRealnameService) {
        super(apUserRealnameService);
        this.apUserRealnameService=apUserRealnameService;
    }

    //@PostMapping("/list")
    //public PageResultVo findPage(@RequestBody ApUserRealnamePageRequestDto apUserRealnamePageRequestDto){
    //    PageResultVo vo=apUserRealnameService.findPage(apUserRealnamePageRequestDto);
    //    return PageResultVo.ok(vo);
    //}测试1

}

