package com.itheima.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.admin.dto.ChannelPageRequestDto;
import com.itheima.admin.dto.SensitivePageRequestDto;
import com.itheima.admin.mapper.AdChannelMapper;
import com.itheima.admin.mapper.AdSensitiveMapper;
import com.itheima.admin.pojo.AdChannel;
import com.itheima.admin.pojo.AdSensitive;
import com.itheima.admin.service.AdChannelService;
import com.itheima.admin.service.AdSensitiveService;
import com.itheima.common.vo.PageResultVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * ServiceImpl<mapper,pojo></> 这个是mybatisplus封装好的业务层代码，帮我们实现了crud与分页，还有查询
 */
@Service
public class AdSensitiveServiceImpl extends ServiceImpl<AdSensitiveMapper, AdSensitive> implements AdSensitiveService {
    /**
     * 分页查询
     *
     * @param dto
     * @return
     */
    @Override
    public PageResultVo findPage(SensitivePageRequestDto dto) {
        //1.配置分页拦截器
        //2.设置分页的测试IPage
        IPage<AdSensitive> pageInfo=new Page<>(dto.getPage(),dto.getSize());
        //3.构建查询的条件LambdaQueryWrapper
        LambdaQueryWrapper<AdSensitive> queryWrapper=new LambdaQueryWrapper<>();
        //频道使用模糊查询
        queryWrapper.like(!StringUtils.isEmpty(dto.getName()),AdSensitive::getSensitives,dto.getName());
        //4.执行查询
        page(pageInfo,queryWrapper);
        //5.构建分页结果集返回
        return PageResultVo.pageResult(dto.getPage(),dto.getSize(),pageInfo.getTotal(),pageInfo.getRecords());
    }
}
