package controller;
import dao.DB;
import models.Message;
import net.sf.json.JSONObject;
import utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReplyServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String resStr = Utils.StreamToString(request.getInputStream());
        int id = Integer.parseInt(Utils.GetDataFromString("id", 3, resStr));
        String reply = Utils.GetDataFromString("reply", 6, resStr);
        Message message = DB.SearchMessageById(id);
        message.setReply(reply);
        message.setTitle(message.getTitle() + "（已解决）");
        JSONObject res = new JSONObject();
        if (DB.AlertMessage(message) > 0) {
            res.put("message", "success");
        } else {
            res.put("message", "fail");
        }
        response.getWriter().print(res.toString());
    }
}
