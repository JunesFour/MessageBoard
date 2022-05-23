package controller;

import dao.DB;
import models.Message;
import net.sf.json.JSONObject;
import utils.Utils;

import java.io.IOException;
import java.util.ArrayList;

public class ShowServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);
    }
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String resStr = Utils.StreamToString(request.getInputStream());
        int id = Integer.parseInt(Utils.GetDataFromString("id", 3, resStr));
        if (id == 0) {
            ArrayList<Message> messages = DB.GetMessages();
            id = messages.size();
        }
        Message message = DB.SearchMessageById(id);
        JSONObject res = new JSONObject();
        res.put("messageItem", message);
        response.getWriter().print(res.toString());
    }
}
