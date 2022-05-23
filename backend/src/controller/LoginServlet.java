package controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import dao.DB;
import models.User;
import net.sf.json.JSONObject;
import utils.Utils;

import java.io.IOException;
import java.util.Date;

public class LoginServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String resStr = Utils.StreamToString(request.getInputStream());
        String username = Utils.GetDataFromString("username", 9, resStr);
        String password = Utils.GetDataFromString("password", 9, resStr);
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        JSONObject res = new JSONObject();
        User userFromDB = dao.DB.SearchUserByUsername(user);

        // 存在该用户
        if (user.getUsername().equals(userFromDB.getUsername()) && user.getPassword().equals(userFromDB.getPassword())) {
            // 设置jwt
            String key = "123456";
            JWTCreator.Builder builder = JWT.create();
            Algorithm algorithm = Algorithm.HMAC256(key);
            String token = builder
                    .withSubject("messageBoard") //设置主题
                    .withExpiresAt(new Date(new Date().getTime() + (1000 * 60 * 30)))//过期时间(半小时)
                    .withClaim("username", user.getUsername()) //负载数据（自定义）
                    .sign(algorithm);
            res.put("message", "success");
            res.put("token", token);
        } else {
            res.put("message", "fail");
        }
        response.getWriter().print(res.toString());
    }
}