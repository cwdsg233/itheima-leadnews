package com.itheima.admin.service.impl;

import com.itheima.admin.pojo.AdUser;
import com.itheima.admin.mapper.AdUserMapper;
import com.itheima.admin.service.AdUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.common.exception.LeadNewsException;
import com.itheima.common.util.AppJwtUtil;
import com.itheima.common.vo.LoginResultVO;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @description <p>管理员用户信息 业务实现</p>
 *
 * @version 1.0
 * @package com.itheima.admin.service.impl
 */
@Service
public class AdUserServiceImpl extends ServiceImpl<AdUserMapper, AdUser> implements AdUserService {

    @Override
    public LoginResultVO loginCheck(Map<String, String> paramMap) {
        //1.参数校验（非空）
        String username = paramMap.get("name");
        String password = paramMap.get("password");
        if (StringUtils.isEmpty(username)||StringUtils.isEmpty(paramMap)){
            throw new LeadNewsException("用户名或密码不能为空");
        }
        //2. 通过用户名查询用户信息, query()生成QueryWrapper 构建 查询条件, .one() =  return getBaseMapper().selectOne(getWrapper());
        // AdUserMapper adUserMapper = getBaseMapper();
        //adUserMapper.selectOne(QueryWrapper);
        // MybatisPlus封装好了
        //QueryWrapper<AdUser> queryWrapper = new QueryWrapper<>();
        //queryWrapper.eq("name", username);
        //AdUser adUser = getBaseMapper().selectOne(queryWrapper);

        AdUser loginUser = query().eq("name", username).one();
        //3. 如果用户信息为空：报错（用户名或密码错误)
        if(null == loginUser){
            throw new LeadNewsException("用户名或密码错误");
        }
        //4.用户存在
        //4.1.密码校验（数据库的密码是加密的，前端过来的密码是明文）
        //4.2.把前端过来的密码进行    加盐、加密处理
        //加盐
        password+=loginUser.getSalt();
        //加密处理
        String ecryptedPwd = DigestUtils.md5DigestAsHex(password.getBytes());
        //4.3与数据库进行比较，如果不一样代表密码不一样  报错
        if(!ecryptedPwd.equals(loginUser.getPassword())){
            throw new LeadNewsException("用户名或密码错误");
        }
        //5.密码一样，验证通过，生成  token
        String token = AppJwtUtil.createToken(loginUser.getId());
        //6.构建返回的对象，用户信息中敏感数据要脱敏
        LoginResultVO vo = new LoginResultVO();
        vo.setToken(token);
        //用户信息中敏感数据要脱敏
        loginUser.setSalt(null);
        loginUser.setPassword(null);

        vo.setUser(loginUser);
        return vo;
    }
}
