package com.dsc.springboot.jwt;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @description JWT 测试类
 * @date 2019/12/17 17:06
 * @author DSC
 */
public class JwtTest {
    private static Logger logger = LoggerFactory.getLogger(JwtTest.class);

    private final static String key = "CISID";

    public static void main(String[] args) {
        String jwtToken = createJWT("DSC_001");
        parseJWT(jwtToken);
    }

    /**
     * @description JWT签发
     * @param userId 用户ID
     * @return JWT消息
     * @date 2019/12/17 17:07
     * @author DSC
     */
    public static String createJWT(String userId) {
        long currentTimeMillis = System.currentTimeMillis();
        JwtBuilder builder = Jwts.builder();
        //存入主体
        builder.setSubject(userId);
        //存入其他信息
        builder.claim("role", "admin");
        builder.claim("userName", "DSC");
        //签发日期
        builder.setIssuedAt(new Date(currentTimeMillis));
        //到期日期
        builder.setExpiration(new Date(currentTimeMillis + (1000 * 30)));
        //加密方式、签名秘钥
        builder.signWith(SignatureAlgorithm.HS256, key);

        String jwtToken = builder.compact();

        System.out.println(jwtToken);

        return jwtToken;
    }

    /**
     * @description JWT验证
     * @param jwtMessage JWT消息
     * @return JWT消息JSON
     * @date 2019/12/17 17:08
     * @author DSC
     */
    public static Claims parseJWT(String jwtMessage) {
        JwtParser parser = Jwts.parser();
        parser.setSigningKey(key);

        Jws<Claims> claimsJws = parser.parseClaimsJws(jwtMessage);
        Claims claims = claimsJws.getBody();

        System.out.println(claims);
        return claims;
    }
}
