package utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Utils {
    // 将请求体中的数据以字节流的格式加载出来，再转化为String
    public static String StreamToString(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));
        StringBuffer reqData = new StringBuffer("");
        String temp;
        while ((temp = br.readLine()) != null) {
            reqData.append(temp);
        }
        return reqData.toString();
    }
    // 提取String字符串中的键值对
    public static String GetDataFromString(String key, int keyLength, String reqData) {
        int valueIndex =  reqData.indexOf(key) + keyLength;
        int valueLength = reqData.indexOf("------", valueIndex) - valueIndex;
        return reqData.substring(valueIndex, valueIndex + valueLength);
    }

    public static String ParseToken(String token) {
        String key = "123456";
        if (token != null) {
            //验证token即是否过期或被篡改
            Algorithm algorithm = Algorithm.HMAC256(key);
            JWTVerifier verifier = JWT.require(algorithm).build();
            try {
                verifier.verify(token);
                //验证成功，提取数据
                DecodedJWT decode = JWT.decode(token);
                String username = decode.getClaim("username").asString();
                return username;
            }catch (JWTDecodeException e) {
                return null;
            }
        }
        return null;
    }
}
