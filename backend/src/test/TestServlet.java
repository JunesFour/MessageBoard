package test;

import net.sf.json.JSONObject;
import utils.Utils;

import java.io.IOException;

public class TestServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);
    }
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String token = Utils.ParseToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtZXNzYWdlQm9hcmQiLCJleHAiOjE2NTMyMzAxNDgsInVzZXJuYW1lIjoibGlnaHQifQ.RAL-SXvg_v6322vZEtYxYbzCTPq1ybe9RvpGZy4mtvY");
        System.out.println("token: " + token);
        JSONObject res = new JSONObject();
        res.put("message", token);
        response.getWriter().print(res.toString());
    }
}
