package com.quickstudy.api.admin.common.util;

import io.jsonwebtoken.*;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.Map;

/**
 * jwt
 * @author Jason
 */
public class JwtUtils {

    /**
     * 生成 token
     *
     * @param claims    自定义的 map
     * @param ttl 过期时间
     * @return String
     */
    public static String createToken(Map<String,Object> claims, Long ttl) {
        Key key = generateKey();
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //生成JWT的时间
        long nowMillis = System.currentTimeMillis();
        //设置header
        JwtBuilder builder = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                //设置payload的键值对
                .setClaims(claims)
                // .setIssuedAt(now) //设置iat
                // .setIssuer("vue-api")
                //签名，需要算法和key
                .signWith(signatureAlgorithm, key);
        if (ttl != null && ttl >= 0) {
            long expMillis = nowMillis + ttl * 1000;
            Date exp = new Date(expMillis);
            //设置过期时间
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    /**
     * 生成 token ，没有过期时间
     *
     * @param claims 自定义的 map
     * @return Map<String,Object>
     */
    public static String createToken(Map<String,Object> claims) {
        return createToken(claims, null);
    }

    /**
     * 解密 jwt
     * @param jwt 创建的 jwt 字符串
     * @return String
     */
    public static Claims parse(String jwt) {

        if (jwt == null) {
            return null;
        }

        try {
            return Jwts.parser()
                    //此处的key要与之前创建的key一致
                    .setSigningKey(generateKey())
                    .parseClaimsJws(jwt)
                    .getBody();
        }catch (ExpiredJwtException e){
            return null;
        }
    }

    /**
     * 获取 key
     *
     * @return SecretKey
     */
    private static SecretKey generateKey() {
        String stringKey = "veu-api";
        byte[] encodedKey = Base64.decodeBase64(stringKey);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }
}
