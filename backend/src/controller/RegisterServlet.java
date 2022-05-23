package controller;

import dao.DB;
import models.User;
import net.sf.json.JSONObject;
import utils.Utils;

import java.io.IOException;

public class RegisterServlet extends javax.servlet.http.HttpServlet {
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

        // 查看是否已经注册过
        User userFromDB = DB.SearchUserByUsername(user);
        // 已经注册过
        if (user.getUsername().equals(userFromDB.getUsername()) && user.getPassword().equals(userFromDB.getPassword())) {
            res.put("message", "exist");
        } else {
            // 未注册过
            user.setRole(1);
            if (DB.AddUser(user) > 0) {
                res.put("message", "success");
                System.out.println("成功");
            } else {
                res.put("message", "fail");
                System.out.println("失败");
            }
        }
        response.getWriter().print(res.toString());
    }
}
