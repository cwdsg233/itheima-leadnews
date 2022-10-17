package com.itheima.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.admin.dto.ChannelPageRequestDto;
import com.itheima.admin.dto.SensitivePageRequestDto;
import com.itheima.admin.pojo.AdChannel;
import com.itheima.admin.pojo.AdSensitive;
import com.itheima.common.vo.PageResultVo;

public interface AdSensitiveService extends IService<AdSensitive> {
    /**
     * 分页查询
     * @param dto
     * @return
     */
    PageResultVo findPage(SensitivePageRequestDto dto);
}
