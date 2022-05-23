package controller;

import dao.DB;
import models.Message;
import net.sf.json.JSONObject;
import utils.Utils;

import java.io.IOException;
import java.util.Date;

public class ReleaseServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);
    }
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String resStr = Utils.StreamToString(request.getInputStream());
        String token = Utils.GetDataFromString("token", 6, resStr);
        String username = Utils.ParseToken(token);
        String title = Utils.GetDataFromString("title", 6, resStr);
        String content = Utils.GetDataFromString("content", 8, resStr);

        Message message = new Message();
        message.setUsername(username);
        message.setTitle(title);
        message.setContent(content);
        message.setAddTime((int) (System.currentTimeMillis() / 1000));
        message.setStatus(1);

        JSONObject res = new JSONObject();
        if (DB.addMessage(message) > 0) {
            res.put("message", "success");
        } else {
            res.put("message", "fail");
        }
        response.getWriter().print(res.toString());
    }
}