package com.itheima.common.util;

import com.itheima.common.constants.SystemConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @version 1.0
 * @description 标题
 * @package com.itheima.common.util
 */
public class AppJwtUtil {
    // TOKEN的有效期一秒（S）
    private static final int TOKEN_TIME_OUT = 3600*24*100;
    // 加密KEY
    private static final String TOKEN_ENCRY_KEY = "MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY";
    // 最小刷新间隔(S)
    private static final int REFRESH_TIME = 300;

    // 生产ID
    public static String createToken(Long id) {
        Map<String, Object> claimMaps = new HashMap<>();
        claimMaps.put("id", id);
        long currentTime = System.currentTimeMillis();
        return Jwts.builder()
            .setId(UUID.randomUUID().toString())
            .setIssuedAt(new Date(currentTime))  //签发时间
            .setSubject("system")  //说明
            .setIssuer("heima") //签发者信息
            .setAudience("app")  //接收用户
            .compressWith(CompressionCodecs.GZIP)  //数据压缩方式
            .signWith(SignatureAlgorithm.HS512, generalKey()) //加密方式
            //过期一个小时
            .setExpiration(new Date(currentTime + TOKEN_TIME_OUT * 1000))  //过期时间戳
            .addClaims(claimMaps) //cla信息   载荷中 用户自定义的内容存入登录用户令牌的地方
            .compact();
    }

    /**
     * 获取token中的claims信息
     *
     * @param token
     * @return
     */
    private static Jws<Claims> getJws(String token) {
        return Jwts.parser()
            .setSigningKey(generalKey())
            .parseClaimsJws(token);
    }

    /**
     * 获取payload body信息
     *
     * @param token
     * @return
     */
    public static Claims getClaimsBody(String token) {
        return getJws(token).getBody();
    }

    /**
     * 获取hearder body信息
     *
     * @param token
     * @return
     */
    public static JwsHeader getHeaderBody(String token) {
        return getJws(token).getHeader();
    }

    /**
     * 是否过期
     *
     * @param token
     * @return 1 有效  0 无效  2 已过期
     */
    public static int verifyToken(String token) {
        try {
            Claims claims = AppJwtUtil.getClaimsBody(token);
            return SystemConstants.JWT_OK;
        } catch (ExpiredJwtException ex) {
            return SystemConstants.JWT_EXPIRE;
        } catch (Exception e) {
            return SystemConstants.JWT_FAIL;
        }
    }

    /**
     * 由字符串生成加密key
     *
     * @return
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getEncoder().encode(TOKEN_ENCRY_KEY.getBytes());
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    public static void main(String[] args) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("userId",9527);
        map.put("username","华安");
        //生成token,  模拟登录成功
        String token = Jwts.builder().setIssuedAt(new Date()).setIssuer("laoye").setClaims(map)
                .compressWith(CompressionCodecs.GZIP)  //数据压缩方式
                .signWith(SignatureAlgorithm.HS512, "itcast").compact();

        System.out.println("token："+token);

        //解析，  用户每次解析带上token，服务端就要验证token的有效性，有效代表登录过，无效报错。
        Claims claims = Jwts.parser()
                .setSigningKey("itcast")//密钥
                .parseClaimsJws(token).getBody();
        Integer userId = claims.get("userId", Integer.class);
        String username = claims.get("username", String.class);
        System.out.println("userId="+userId+",username="+username);


       /* Map map = new HashMap();
        map.put("id","11");*/
       /* String token = AppJwtUtil.createToken(1102L);
        System.out.println(token);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int i = AppJwtUtil.verifyToken(token);
        System.out.println(i);
        Claims claims = AppJwtUtil.getClaimsBody(token);
        Integer integer = AppJwtUtil.verifyToken("dsafafsa");
        System.out.println(integer);
        System.out.println(claims);*/

    }
}
