package controller;
import dao.DB;
import models.Message;
import net.sf.json.JSONObject;
import utils.Utils;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Comparator;

public class MessageListServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);
    }
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String resStr = Utils.StreamToString(request.getInputStream());
        String page = Utils.GetDataFromString("page", 5, resStr);
        // 获取所有留言
        ArrayList<Message> messages = DB.GetMessages();
        messages.sort(new Comparator<Message>() {
            @Override
            public int compare(Message o1, Message o2) {
                if (o1.getAddTime() < o2.getAddTime()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
        ArrayList<Message> currentMessages = new ArrayList<>();
        int preCount = (Integer.parseInt(page) - 1) * 10;
        for (int i = preCount; i < preCount + 10; i++) {
            if (currentMessages.size() + preCount >= messages.size()) {
                break;
            }
            currentMessages.add(messages.get(i));
        }

        JSONObject res = new JSONObject();
        res.put("totalPages", Math.ceil(messages.size() * 1.0 / 10));
        res.put("messageList", currentMessages);
        response.getWriter().print(res.toString());
    }
}
