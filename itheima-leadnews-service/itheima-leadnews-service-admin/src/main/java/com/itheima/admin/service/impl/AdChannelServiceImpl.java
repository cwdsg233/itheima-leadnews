package com.itheima.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.admin.dto.ChannelPageRequestDto;
import com.itheima.admin.mapper.AdChannelMapper;
import com.itheima.admin.pojo.AdChannel;
import com.itheima.admin.service.AdChannelService;
import com.itheima.common.vo.PageResultVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * ServiceImpl<mapper,pojo></> 这个是mybatisplus封装好的业务层代码，帮我们实现了crud与分页，还有查询
 */
@Service
public class AdChannelServiceImpl extends ServiceImpl<AdChannelMapper, AdChannel> implements AdChannelService {
    /**
     * 分页查询
     *
     * @param dto
     * @return
     */
    @Override
    public PageResultVo findPage(ChannelPageRequestDto dto) {
        //1.配置分页拦截器
        //2.设置分页的测试IPage
        IPage<AdChannel> pageInfo=new Page<>(dto.getPage(),dto.getSize());
        //3.构建查询的条件LambdaQueryWrapper
        LambdaQueryWrapper<AdChannel> queryWrapper=new LambdaQueryWrapper<>();
        //频道使用模糊查询
        queryWrapper.like(!StringUtils.isEmpty(dto.getName()),AdChannel::getName,dto.getName());
        //状态使用精确查询
        queryWrapper.eq(null!=dto.getStatus(),AdChannel::getStatus,dto.getStatus());
        //4.执行查询
        page(pageInfo,queryWrapper);
        //5.构建分页结果集返回
        return PageResultVo.pageResult(dto.getPage(),dto.getSize(),pageInfo.getTotal(),pageInfo.getRecords());
    }

    /**
     * 新增频道
     *
     * @param adChannel
     */
    @Override
    public void add(AdChannel adChannel) {
        adChannel.setIsDefault(false);
        adChannel.setCreatedTime(new Date());
        //mybatisplus提供的service封装的方法
        save(adChannel);
    }
}
