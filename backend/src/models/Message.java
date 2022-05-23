package models;

public class Message {
    int id;
    String username;
    String title;
    String content;
    String reply;
    int addTime;
    int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public int getAddTime() {
        return addTime;
    }

    public void setAddTime(int addTime) {
        this.addTime = addTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", reply='" + reply + '\'' +
                ", addTime=" + addTime +
                ", status=" + status +
                '}';
    }
}
