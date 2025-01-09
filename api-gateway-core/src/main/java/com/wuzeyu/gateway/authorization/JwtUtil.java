package com.wuzeyu.gateway.authorization;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wuzeyu
 * @description JSON Web Tokens
 * @github github.com/kkkkendei
 */
public class JwtUtil {

    private static final String signingKey = "B*B^5Fe";

    public static String encode(String issuer, long ttMills, Map<String, Object> claims) {

        if (claims == null) {
            claims = new HashMap<>();
        }

        //签发时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //签发操作
        JwtBuilder builder = Jwts.builder()
                // 荷载部分
                .setClaims(claims)
                // 签发时间
                .setIssuedAt(now)
                // 签发人(类似userId,userName)
                .setSubject(issuer)
                // 设置生成签名的算法和密钥
                .signWith(SignatureAlgorithm.HS256, signingKey);

        return builder.compact();

    }

    public static Claims decode(String token) {
        return Jwts.parser()
                // 设置签名的密钥
                .setSigningKey(signingKey)
                // 设置需要解析的 Jwt
                .parseClaimsJws(token)
                .getBody();
    }

}
