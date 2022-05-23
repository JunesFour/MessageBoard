package controller;
import net.sf.json.JSONObject;
import utils.Utils;

import java.io.IOException;

public class TokenServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);
    }
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String resStr = Utils.StreamToString(request.getInputStream());
        String token = Utils.GetDataFromString("token", 6, resStr);
        String username = Utils.ParseToken(token);
        JSONObject res = new JSONObject();
        if (username == null) {
            res.put("message", "fail");
        } else {
            res.put("message", "success");
            res.put("username", username);
        }
        response.getWriter().print(res.toString());
    }
}
