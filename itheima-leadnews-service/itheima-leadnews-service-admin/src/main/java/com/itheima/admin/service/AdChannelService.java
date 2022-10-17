package com.itheima.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.admin.dto.ChannelPageRequestDto;
import com.itheima.admin.pojo.AdChannel;
import com.itheima.common.vo.PageResultVo;

public interface AdChannelService extends IService<AdChannel> {
    /**
     * 分页查询
     * @param dto
     * @return
     */
    PageResultVo findPage(ChannelPageRequestDto dto);

    /**
     * 新增频道
     * @param adChannel
     */
    void add(AdChannel adChannel);
}
