package controller;
import dao.DB;
import models.Message;
import net.sf.json.JSONObject;
import utils.Utils;

import java.io.IOException;
import java.util.ArrayList;

public class UpdateServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);
    }
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String resStr = Utils.StreamToString(request.getInputStream());
        int id = Integer.parseInt(Utils.GetDataFromString("id", 3, resStr));
        String content = Utils.GetDataFromString("content", 8, resStr);
        Message message = DB.SearchMessageById(id);
        message.setId(id);
        message.setContent(content);
        JSONObject res = new JSONObject();
        if (DB.AlertMessage(message) > 0) {
            res.put("message", "success");
        } else {
            res.put("message", "fail");
        }
        response.getWriter().print(res.toString());
    }
}
