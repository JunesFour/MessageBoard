package dao;

import models.Message;
import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB {
    static Connection conn = null;
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://116.62.177.68:3306/messageBoard?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC","root","501124524");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static int AddUser(User user) {
        PreparedStatement stmt = null;
        int line = 0;
        try {
            String sql = "insert into user values(null,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setInt(3, user.getRole());

            line = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return line;
    }
    public static User SearchUserByUsername(User user) {
        PreparedStatement stmt = null;
        User userFromDB = new User();
        try {
            String sql = "select *from user where username = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getUsername());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                userFromDB.setUsername(rs.getString("username"));
                userFromDB.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return userFromDB;
    }
    public static User SearchUserById(int id) {
        PreparedStatement stmt = null;
        User userFromDB = new User();
        try {
            String sql = "select *from user where id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                userFromDB.setId(rs.getInt("id"));
                userFromDB.setUsername(rs.getString("username"));
                userFromDB.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return userFromDB;
    }
    public static ArrayList<Message> GetMessages() {
        Statement stmt = null;
        ArrayList<Message> messages = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            String sql = "select *from message";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Message message = new Message();
                message.setId(rs.getInt("id"));
                message.setUsername(rs.getString("username"));
                message.setTitle(rs.getString("title"));
                message.setContent(rs.getString("content"));
                message.setReply(rs.getString("reply"));
                message.setAddTime(rs.getInt("add_time"));
                message.setStatus(rs.getInt("status"));
                messages.add(message);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return messages;
    }

    public static int addMessage(Message message) {
        PreparedStatement stmt = null;
        int line = 0;
        try {
            String sql = "insert into message values(null, ?,?,?,null,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, message.getUsername());
            stmt.setString(2, message.getTitle());
            stmt.setString(3, message.getContent());
            stmt.setInt(4, message.getAddTime());
            stmt.setInt(5, message.getStatus());
            line = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return line;
    }

    public static Message SearchMessageById(int id) {
        PreparedStatement stmt = null;
        Message message = new Message();
        try {
            String sql = "select *from message where id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                message.setId(rs.getInt("id"));
                message.setUsername(rs.getString("username"));
                message.setTitle(rs.getString("title"));
                message.setContent(rs.getString("content"));
                message.setReply(rs.getString("reply"));
                message.setAddTime(rs.getInt("add_time"));
                message.setStatus(rs.getInt("status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return message;
    }

    public static int AlertMessage(Message message) {
        PreparedStatement stmt = null;
        int line = 0;
        try {
            String sql = "update message set content = ?, reply = ?, title = ? where id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, message.getContent());
            stmt.setString(2, message.getReply());
            stmt.setString(3, message.getTitle());
            stmt.setInt(4, message.getId());
            line = stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return line;
    }
}
