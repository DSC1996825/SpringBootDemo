package com.dsc.springboot.jwt;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * @author DSC
 * @description JWT 测试类
 * @date 2019/12/17 17:06
 */
public class JwtTest {
    private static Logger logger = LoggerFactory.getLogger(JwtTest.class);

    private final static String key = "CISID";

    public static void main(String[] args) {
        String jwtToken = createJWT("DSC_001");
        parseJWT(jwtToken);
//        parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJEU0NfMDAxIiwic3ViIjoiRFNDXzAwMSIsInJvbGUiOiJhZG1pbiIsInVzZXJOYW1lIjoiRFNDIiwiaWF0IjoxNTc4MTIxNzA4LCJleHAiOjE1NzgxMjE3Mzh9.WswGULBK549E28VPOcAUPRqlesaaZLQAR16Yo1URd1A");
    }

    /**
     * @param userId 用户ID
     * @return JWT消息
     * @description JWT签发
     * @date 2019/12/17 17:07
     * @author DSC
     */
    public static String createJWT(String userId) {
        //当前时间
        long currentTimeMillis = System.currentTimeMillis();
        JwtBuilder builder = Jwts.builder();
        builder.setId(userId);
        //存入主体
        builder.setSubject(userId);
        //存入其他信息
        builder.claim("role", "admin");
        builder.claim("userName", "DSC");
        //签发日期
        builder.setIssuedAt(new Date(currentTimeMillis));
        //到期日期（60秒）
        builder.setExpiration(new Date(currentTimeMillis + (1000 * 30)));
        //加密方式、签名秘钥
        builder.signWith(SignatureAlgorithm.HS256, key);

        String jwtToken = builder.compact();

        System.out.println(jwtToken);

        return jwtToken;
    }

    /**
     * @param jwtMessage JWT消息
     * @return JWT消息JSON
     * @description JWT验证
     * @date 2019/12/17 17:08
     * @author DSC
     */
    public static Claims parseJWT(String jwtMessage) {
        JwtParser parser = Jwts.parser();
        parser.setSigningKey(key);

        Jws<Claims> claimsJws = parser.parseClaimsJws(jwtMessage);
        Claims claims = claimsJws.getBody();

        for (Map.Entry<String, Object> entry : claims.entrySet()) {
            System.out.println(entry.getKey() + "：" + claims.get(entry.getKey()));
        }
        System.out.println(claims.entrySet());
        return claims;
    }
}
