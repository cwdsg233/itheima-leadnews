package com.itheima.gatewayadmin.filters;

import com.itheima.common.constants.SystemConstants;
import com.itheima.common.util.AppJwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @version 1.0
 * @description 网关统一鉴权
 * @package com.itheima.gatewayadmin.filters
 */
@Configuration
@Slf4j
public class AdminAuthorizeFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //* 对登录请求 放行
        //* 非登录请求 判断是否登录过， 解析token是否成功即可
        //* 解析token成功则放行
        //* 失败则拦截, 返回状态为401
        //1.获取请求对象、响应对象
        ServerHttpRequest req = exchange.getRequest();
        ServerHttpResponse res = exchange.getResponse();
        //2.获取请求路径
        String path = req.getURI().getPath();
        log.info("用户请求了路径：{}", path);
        //3.判断路径是否为登录路径，是则放行  /admin/login/in  /admin/login/out
        if(path.startsWith("/admin/login")){
            // 是登录请求则放行 【此处不要忘了return】
            return chain.filter(exchange);
        }
        //4.获取请求头中的token
        String token = req.getHeaders().getFirst("token");
        if(!StringUtils.isEmpty(token)) {
            //5.解析token，验证token是否有效
            if (AppJwtUtil.verifyToken(token) == SystemConstants.JWT_OK) {
                //6.有效则放行【此处不要忘了return】
                return chain.filter(exchange);
            }
        }
        //7.拦截并返回401
        res.setStatusCode(HttpStatus.UNAUTHORIZED);
        return res.setComplete();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
