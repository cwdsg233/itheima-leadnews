package com.itheima.admin.controller;

import com.itheima.admin.service.AdUserService;
import com.itheima.common.vo.LoginResultVO;
import com.itheima.common.vo.ResultVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Resource
    private AdUserService adUserService;

    /**
     * 登录验证
     * @param paramMap
     * @return
     */
    @PostMapping("/in")
    public ResultVo loginCheck(@RequestBody Map<String,String> paramMap){
        LoginResultVO vo=adUserService.loginCheck(paramMap);
        return ResultVo.ok(vo);
    }
}
